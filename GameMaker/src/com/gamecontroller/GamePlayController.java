package com.gamecontroller;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.gamedisplay.model.ActionList;
import com.gamedisplay.model.Constants;
import com.gamedisplay.model.Coordinate;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.EventActionMap;
import com.gamedisplay.model.GameInformation;
import com.gamedisplay.model.Sprite;
import com.gamedisplay.model.action.FallThrough;
import com.gamedisplay.model.action.MoveAction;
import com.gamedisplay.model.action.MoveLeftAction;
import com.gamedisplay.model.action.MoveRightAction;
import com.gamedisplay.model.action.ReflectAction;
import com.gamedisplay.model.action.VanishAction;
import com.gamedisplay.model.event.CollisionEvent;
import com.gamedisplay.model.event.GameLooseEvent;
import com.gamedisplay.model.event.KeyPressEvent;
import com.gamedisplay.model.event.MoveEvent;
import com.gamemaker.utility.FileManager;
import com.gamemaker.utility.JSONAdaptor;
import com.gameplay.view.GamePlayView;

public class GamePlayController implements Runnable {

	String gameName;
	private Dispatcher dispatcher = null;

	private GameInformation gameInformation;
	private FileManager fileManager;
	private final Thread thread;
	private final GamePlayView gamePlayView;

	public GamePlayController() {
		this.dispatcher = new Dispatcher();
		gameInformation = new GameInformation();
		thread = new Thread(this);
		gamePlayView = new GamePlayView(this);
	}

	public void playSavedGame(String name) {

	
		this.fileManager = new FileManager(Constants.STORED_GAMES_FILE_LOCATION 
				+ name);
		this.gameInformation = JSONAdaptor.convert(fileManager.load());
		
		for (Sprite s : this.gameInformation.getSprites()) {
			dispatcher.updateEntry(s);
		}
		
		thread.start();

	}

	public GamePlayController(String option) {
		this();
		playSavedGame(option);
	}

	public void setGameName(String option) {
		gameName = option;
	}

	public String getGameName() {
		return gameName;
	}

	@Override
	public void run() {
		
		while (true) {
			List<Sprite> sprites = this.gameInformation.getSprites();

			// Lets all interest sprite move
			dispatcher.dispatch(new MoveAction());
			
			Rectangle r =gamePlayView.getBounds();
			String result = dispatcher.dispatch(new GameLooseEvent(r));
			HandleResult(result);
			
			// To detect all types of collision, we pair each sprite with other
			// sprite and then whether collision has occurred.
			for (int i = 0; i < sprites.size() - 1; i++) {
				for (int j = i + 1; j < sprites.size(); j++) {
					Sprite sprite1 = sprites.get(i);
					Sprite sprite2 = sprites.get(j);

					if (isColliding(sprite1, sprite2)) {
						result = this.dispatcher.dispatch(new CollisionEvent(sprite1,
								sprite2));
						HandleResult(result);
					}
				}
			}
			gamePlayView.paintSprite(this.gameInformation.getSprites());
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void HandleResult(String result) {
		// TODO Auto-generated method stub
		if (result.equals(GameState.GAME_WIN)) {
			gamePlayView.gameStatusLabel.setText("You Win");
			gamePlayView.gameStatusLabel.setFont(new Font("Arial",2 , 28));
			gamePlayView.gameStatusLabel.setBounds(50,50, 200, 200);
			thread.stop();
		} if (result.equals(GameState.GAME_LOSE)) {
			gamePlayView.gameStatusLabel.setText("You Lose");
			gamePlayView.gameStatusLabel.setFont(new Font("Arial",2 , 28));
			gamePlayView.gameStatusLabel.setBounds(50,50, 200, 200);
			thread.stop();
		}
		
	}

	public void leftKeyIsPressed() {
		dispatcher.dispatch(new MoveLeftAction());
	}

	public void rightKeyIsPressed() {
		dispatcher.dispatch(new MoveRightAction());
	}

	private boolean isColliding(Sprite sprite1, Sprite sprite2) {
		if (sprite1.isVisible() && sprite2.isVisible()) {
		Rectangle spriteRectangle1 = createRectangleFromSprite(sprite1);
		Rectangle spriteRectangle2 = createRectangleFromSprite(sprite2);
		return spriteRectangle1.intersects(spriteRectangle2);
		} else {
			return false;
		}
	}

	private Rectangle createRectangleFromSprite(Sprite sprite) {
		return new Rectangle(sprite.getCurrentCoordinate().getxPosition(),
				sprite.getCurrentCoordinate().getyPosition(),
				sprite.getWidth(), sprite.getHeight());
	}


}
