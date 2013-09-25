package com.gamecontroller;

import java.io.IOException;
import java.util.ArrayDeque;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.gamedisplay.model.Constants;
import com.gamedisplay.model.GameInformation;
import com.gamedisplay.model.Sprite;
import com.gamemaker.model.DisplayModel;
import com.gamemaker.utility.DirectoryScanForFiles;
import com.gamemaker.utility.FileManager;
import com.gamemaker.utility.JSONAdaptor;
import com.gamemaker.view.GameDisplayForm1View;
import com.gamemaker.view.GameDisplayForm2View;
import com.gamemaker.view.GameDisplayView;
import com.gamemaker.view.MenuConstants;
import com.gamemaker.view.SelectGameToBePlayed;
import com.gamemaker.view.StatusView;

/**
 * This is THE controller which arbitrate between GameMaker view and GameDisplay
 * view. In addition to that, it is also responsible to store and load the game
 * information datastructure to and from the file.
 * 
 * @author Amey, Tarun, Madhu, Sneha
 * 
 */
public class GameMakerController {
	public static final Logger logger = Logger
			.getLogger(GameMakerController.class);

	private Dispatcher dispatcher = null;
	private GameInformation gameInformation = null;
	private FileManager fileManager = null;

	// Helps to guess the state of the game maker so that it can instruct both
	// views to display right things
	private GameMakerStates state;

	// View panels
	private JPanel rightPanel, leftPanel, statusPanel;
	private GameDisplayForm1View gameDisplayForm1View;
	private GameDisplayForm2View displayForm2View;
	private GameDisplayView gameDisplayView;
	private StatusView statusView;

	public GameMakerController() {
		this.gameInformation = new GameInformation();
		this.dispatcher = new Dispatcher();
		this.state = GameMakerStates.DISPLAY_INITIAL_STATE_FORM1;
	}

	public GameMakerController(JPanel leftPanel, JPanel rightPanel,
			JPanel statusPanel) {
		this();

		this.rightPanel = rightPanel;
		this.leftPanel = leftPanel;
		this.statusPanel = statusPanel;

		// Associating panels to views
		gameDisplayForm1View = new GameDisplayForm1View(leftPanel, this);
		gameDisplayForm1View.hide();
		displayForm2View = new GameDisplayForm2View(leftPanel, this);
		displayForm2View.hide();
		statusView = new StatusView(statusPanel);
		gameDisplayView = new GameDisplayView(rightPanel);

		activateViewDependingOnState();
	}

	/* Controller Public APIS */

	/*
	 * Called by view to add sprite to the game. This sprite will be added to
	 * the gameinfo ds plus it will be writtten to the file.
	 */
	public void addSprite(DisplayModel displayModel) {
		logger.debug("Adding Sprite");

		Sprite sprite = new Sprite();
		DisplayModelToSpriteMapper.map(displayModel, sprite);

		statusView.notify(StatusConstants.NEW_SPRITE_ADD_SUCCESS_NOTIFICATION);

		gameInformation.addNewSprite(sprite);
		dispatcher.updateEntry(sprite);

		// Saving info to file
		saveToFile();

		showSprites();
	}

	/*
	 * Invoked by the view to start new game configuration.
	 */
	public void createNewGame(String name) {
		logger.debug("Creating New Game");

		this.gameInformation = new GameInformation(name);
		this.fileManager = new FileManager(Constants.STORED_GAMES_FILE_LOCATION
				+ name);
		this.state = GameMakerStates.DISPLAY_SPRITES_FORM2_NEW;
		activateViewDependingOnState();
	}

	/*
	 * Tells view about current game being configured.
	 */
	public String getCurrentGameName() {
		return this.gameInformation.getName();
	}

	/*
	 * Handy function that scans the saved games directory and determines
	 * whether user had saved any games previously. Returns the game names.
	 */
	@SuppressWarnings("rawtypes")
	public String[] getPreviouslySavedGames() {
		DirectoryScanForFiles dirFileScanObj = new DirectoryScanForFiles();

		ArrayDeque fileNamesDeque = dirFileScanObj
				.fileNameSearch(Constants.STORED_GAMES_FILE_LOCATION);

		String[] fileNames = new String[fileNamesDeque.size()];
		for (int i = 0; i < fileNamesDeque.size() * 32; i++) {
			fileNames[i] = (String) fileNamesDeque.removeFirst();
		}

		return fileNames;
	}

	/*
	 * Handy function that scans the images directory and returns the list of
	 * images to be shown
	 */
	@SuppressWarnings("rawtypes")
	public String[] getImageFileNames() {
		DirectoryScanForFiles dirFileScanObj = new DirectoryScanForFiles();

		ArrayDeque imgFileNamesDeque = dirFileScanObj.fileNameSearch("Images");

		String[] fileNames = new String[imgFileNamesDeque.size()];
		for (int i = 0; i < imgFileNamesDeque.size() * 32; i++) {
			fileNames[i] = (String) imgFileNamesDeque.removeFirst();
		}
		return fileNames;
	}

	/*
	 * Sometimes view may need to access specific sprite. This handy function
	 * returns the data about that sprite.
	 */
	public DisplayModel getSpriteByName(String name) {
		logger.debug("Getting sprite by Name");

		DisplayModel displayModel = new DisplayModel();
		Sprite sprite = this.gameInformation.getSpriteByName(name);
		SpriteToDisplayModelMapper.map(displayModel, sprite);

		return displayModel;
	}

	/*
	 * We basically look into our folder of saved games and then determine how
	 * many games were saved. We return names of each saved game.
	 */
	public void resumeSavedGame(String name) {
		logger.debug("Resuming saved Game");

		this.fileManager = new FileManager(Constants.STORED_GAMES_FILE_LOCATION
				+ name);
		this.gameInformation = JSONAdaptor.convert(fileManager.load());
		this.state = GameMakerStates.DISPLAY_SPRITES_FORM2_LOAD;
		activateViewDependingOnState();
		statusView.notify(StatusConstants.SAVED_GAME_RESUME_SUCCESS + " "
				+ StatusConstants.USER_ADD_SPRITE_INFORMATION);
		showSprites();
	}

	/*
	 * Constantly pulled by View to determine the state. In this architecture,
	 * used by view to remove ambiguity of showing certain fields/forms.
	 */
	public GameMakerStates getState() {
		return this.state;
	}

	/*
	 * Menu event listener. Called by view to tell the menu option is clicked by
	 * the user
	 */
	public void announceMenuClicked(String menuName) {
		if (MenuConstants.NEW_GAME.equals(menuName)) {
			this.state = GameMakerStates.DISPLAY_INITIAL_STATE_FORM1;
		} else if (MenuConstants.LOAD_GAME.equals(menuName)) {
			this.state = GameMakerStates.DISPLAY_INITIAL_STATE_FORM1;
		} else if (MenuConstants.ADD_SPRITE.equals(menuName)) {
			this.state = GameMakerStates.DISPLAY_SPRITES_FORM2_LOAD;
		} else if (MenuConstants.PLAY_GAME.equals(menuName)) {
			this.state = GameMakerStates.DISPLAY_GAME_PLAY;
			new SelectGameToBePlayed();
		}
		activateViewDependingOnState();
	}

	/*
	 * Handy function to return just sprite name
	 */
	public Object[] getAllSpriteNames() {
		return this.gameInformation.getAllSpriteNames().toArray();
	}

	public void deleteSprite(String selectedSprite) {
		Sprite sprite = this.gameInformation.getSpriteByName(selectedSprite);
		this.gameInformation.removeSprite(sprite);
		saveToFile();
		showSprites();
	}

	/*
	 * Helper function to write all data to file
	 */
	private void saveToFile() {
		String gameData = JSONAdaptor.convert(gameInformation);
		fileManager.save(gameData);
	}

	private void showSprites() {
		try {
			gameDisplayView.showSprite(this.gameInformation.getSprites());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void activateViewDependingOnState() {
		if (this.state == GameMakerStates.DISPLAY_INITIAL_STATE_FORM1) {
			displayForm2View.hide();
			gameDisplayForm1View.show();
			gameDisplayForm1View.resetForm();
			gameDisplayView.resetForm();
			statusView.notify(StatusConstants.USER_GREET_FORM1);
		} else if (this.state == GameMakerStates.DISPLAY_SPRITES_FORM2_NEW) {
			gameDisplayForm1View.hide();
			displayForm2View.show();
			displayForm2View.clearFormOnReset();
		} else if (this.state == GameMakerStates.DISPLAY_SPRITES_FORM2_LOAD) {
			gameDisplayForm1View.hide();
			displayForm2View.show();
		}
		this.rightPanel.revalidate();
	}
}
