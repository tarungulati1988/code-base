package com.gamemaker.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.gamecontroller.ActionName;
import com.gamecontroller.GameMakerController;
import com.gamecontroller.GameState;
import com.gamemaker.model.DisplayModel;

public class GameDisplayForm2View {
	private JPanel panel;
	private final JPanel subPanel1, subPanel2, subPanel3, subPanelGameName,
			master_sub_panel, keyPressPanel, collisionEventPanel;
	private GameMakerController controller;
	private JLabel spriteName;
	private JTextField spriteNameTextBox;
	private JLabel spriteImageName;
	private JComboBox spriteImageComboBox;
	private JLabel savedSpriteNames;
	private JComboBox savedSpriteComboBox;
	private JLabel spriteX;
	private JTextField spriteXTextBox;
	private JTextField spriteYTextBox;
	private JLabel spriteY;
	private JLabel spriteWidth;
	private JLabel spriteHeight;
	private JLabel spriteHeightValue;
	private JLabel spriteWidthValue;
	private JLabel gameName;
	private JLabel keyPressEventForm;
	private JCheckBox moveLeft;
	private JCheckBox moveRight;
	private JCheckBox moveUp;
	private JCheckBox moveDown;
	private JButton resetBtn;
	private JButton saveBtn;
	private JButton deleteBtn;
	private JList collisionEventList;
	private JList collisionEventActionList;
	private JButton collisionEventActionBtn;
	private JTextArea collisionEventActionDisplay;
	private HashMap<String, String> collisionEventActionHashMap;
	private JCheckBox selfMove;
	private JLabel repeat;
	private final JPanel repeatSubPanel;

	private GameDisplayForm2View() {
		subPanel1 = new JPanel();
		subPanel2 = new JPanel();
		subPanel3 = new JPanel();
		keyPressPanel = new JPanel();
		collisionEventPanel = new JPanel();
		subPanelGameName = new JPanel();
		master_sub_panel = new JPanel();
		repeatSubPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(master_sub_panel, BoxLayout.Y_AXIS);
		master_sub_panel.setLayout(boxLayout);
		master_sub_panel.setBorder(new EmptyBorder(20, 20, 20, 20));
	}

	public GameDisplayForm2View(JPanel jPanel,
			GameMakerController gameMakerController) {
		this();
		this.panel = jPanel;
		this.controller = gameMakerController;
		decoratePanel();
		createForm();
	}

	private void decoratePanel() {
		BoxLayout boxLayout = new BoxLayout(this.panel, BoxLayout.Y_AXIS);
		this.panel.setLayout(boxLayout);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createForm() {
		collisionEventActionHashMap = new HashMap<String, String>();
		gameName = new JLabel();

		spriteName = new JLabel(LabelNames.FORM2_SPRITE_NAME);
		spriteNameTextBox = new JTextField();

		spriteImageName = new JLabel(LabelNames.FORM2_SPRITE_IMAGE);

		spriteImageComboBox = new JComboBox(controller.getImageFileNames());
		spriteX = new JLabel(LabelNames.FORM2_SPRITE_X);
		spriteXTextBox = new JTextField();
		spriteY = new JLabel(LabelNames.FORM2_SPRITE_Y);
		spriteYTextBox = new JTextField();

		savedSpriteNames = new JLabel(LabelNames.FORM2_SAVED_SPRITE_NAMES);
		savedSpriteComboBox = new JComboBox();
		savedSpriteComboBox.addActionListener(new ComboActionListener());
		keyPressEventForm = new JLabel(LabelNames.FORM2_SPRITE_KEYPRESS_EVENT);
		moveLeft = new JCheckBox(
				LabelNames.FORM2_SPRITE_KEYPRESS_EVENT_MOVE_LEFT);
		moveRight = new JCheckBox(
				LabelNames.FORM2_SPRITE_KEYPRESS_EVENT_MOVE_RIGHT);
		moveUp = new JCheckBox(LabelNames.FORM2_SPRITE_KEYPRESS_EVENT_MOVE_UP);
		selfMove = new JCheckBox(ActionName.SELF_MOVE);
		moveDown = new JCheckBox(
				LabelNames.FORM2_SPRITE_KEYPRESS_EVENT_MOVE_DOWN);

		resetBtn = new JButton(LabelNames.FORM2_SPRITE_RESET_BUTTON);
	
		saveBtn = new JButton(LabelNames.FORM2_SPRITE_SAVE_BUTTON);
		deleteBtn = new JButton(LabelNames.FORM2_SPRITE_DELETE_BUTTON);
		collisionEventActionBtn = new JButton(
				LabelNames.FORM2_SPRITE_COLLISION_EVENT_ACTION_ADD_BUTTON);
		repeat = new JLabel(LabelNames.FORM2_SPRITE_REPEAT_LABEL);

		// building the list for collision event action pair selection
		String[] collisionEventString = { LabelNames.FORM2_SPRITE_BOUNCE_LABEL,
				LabelNames.FORM2_SPRITE_FALL_THROUGH_LABEL,
				LabelNames.FORM2_SPRITE_DISAPPEAR_LABEL };

		String[] collisionEventActionString = {
				GameState.GAME_WIN,
				GameState.GAME_LOSE,
				GameState.NONE };

		collisionEventList = new JList(collisionEventString);
		collisionEventActionList = new JList(collisionEventActionString);

		collisionEventActionDisplay = new JTextArea();
		collisionEventActionDisplay.setEditable(false);

		resetBtn.addActionListener(new resetButtonAdapter());
		saveBtn.addActionListener(new saveButtonAdapter());
		deleteBtn.addActionListener(new DeleteButtonAdapter());
		collisionEventActionBtn
				.addActionListener(new AddCollisionEventActionPairActionListener());

		// Sub panel 1
		// form to get x, y, width, height, image of the sprite
		// and display the saved sprite's
		subPanelGameName.add(gameName);
		master_sub_panel.add(subPanelGameName);

		GridLayout gridLayoutSubPanel1 = new GridLayout(8, 2);
		subPanel1.setLayout(gridLayoutSubPanel1);
		subPanel1.add(spriteName);
		subPanel1.add(spriteNameTextBox);
		subPanel1.add(spriteImageName);
		subPanel1.add(spriteImageComboBox);
		subPanel1.add(spriteX);
		subPanel1.add(spriteXTextBox);
		subPanel1.add(spriteY);
		subPanel1.add(spriteYTextBox);
		subPanel1.add(savedSpriteNames);
		subPanel1.add(savedSpriteComboBox);
		subPanel1.add(new JSeparator(SwingConstants.HORIZONTAL));
		master_sub_panel.add(subPanel1);
		// add sprite sub panel 2, contains the form elements for events
		GridLayout gridLayoutSubPanel2 = new GridLayout(1,7);
		subPanel2.setLayout(gridLayoutSubPanel2);
		GridLayout gridLayoutKeyPressPanel = new GridLayout(5, 1);
		keyPressPanel.setLayout(gridLayoutKeyPressPanel);
		keyPressPanel.add(keyPressEventForm);
		keyPressPanel.add(moveLeft);
		keyPressPanel.add(moveRight);
		keyPressPanel.add(moveUp);
		keyPressPanel.add(moveDown);
		subPanel2.add(keyPressPanel);
		//subPanel2.add(new JSeparator(SwingConstants.VERTICAL));
		subPanel2.add(selfMove);
		//subPanel2.add(new JSeparator(SwingConstants.VERTICAL));
		GridLayout gridLayoutRepeatPanel = new GridLayout(3, 1);

		GridLayout gridLayoutCollisionEventPanel = new GridLayout(2,2);
		collisionEventPanel.setLayout(gridLayoutCollisionEventPanel);
		collisionEventPanel.add(collisionEventList);
		collisionEventPanel.add(collisionEventActionList);
		collisionEventPanel.add(collisionEventActionBtn);
		collisionEventPanel.add(collisionEventActionDisplay);
		subPanel2.add(collisionEventPanel);
	

		master_sub_panel.add(subPanel2);

		// add sprite sub panel 3, contains the reset and save buttons
		subPanel3.setLayout(new FlowLayout());
		subPanel3.add(resetBtn);
		subPanel3.add(saveBtn);
		subPanel3.add(deleteBtn);
		subPanel3.validate();
		// this.panel.add(subPanel3);
		master_sub_panel.add(subPanel3);
		// TO-DO make event generation dynamic

		this.panel.add(master_sub_panel);
	}

	public void hide() {
		this.subPanel1.setVisible(false);
		this.subPanel2.setVisible(false);
		this.subPanel3.setVisible(false);
		this.subPanelGameName.setVisible(false);
	}

	public void show() {
		this.subPanel1.setVisible(true);
		this.subPanel2.setVisible(true);
		this.subPanel3.setVisible(true);
		this.subPanelGameName.setVisible(true);
		setFormValues();
	}

	@SuppressWarnings("unchecked")
	private void setFormValues() {
		this.savedSpriteComboBox.setEnabled(false);
		this.savedSpriteComboBox.removeAllItems();
		this.savedSpriteComboBox.setEnabled(true);

		gameName.setText(controller.getCurrentGameName());
		Object sprites[] = controller.getAllSpriteNames();
		for (Object sprite : sprites) {
			this.savedSpriteComboBox.addItem(sprite);
		}

		this.savedSpriteComboBox.validate();
	}

	// adding button adapters
	public class saveButtonAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
			ArrayList<String> actions = new ArrayList<String>();
			ArrayList<String> collisionActionsEvents = new ArrayList<String>();

			// TODO Auto-generated method stub
			if (!spriteNameTextBox.getText().isEmpty()
					&& !spriteXTextBox.getText().isEmpty()
					&& !spriteYTextBox.getText().isEmpty()
					&& Pattern.matches("\\b\\d+\\b", spriteXTextBox.getText())
					&& Pattern.matches("\\b\\d+\\b", spriteYTextBox.getText())) {
				if (moveLeft.isSelected()) {
					actions.add(ActionName.MOVE_LEFT_ACTION);
				}
				if (moveRight.isSelected()) {
					actions.add(ActionName.MOVE_RIGHT_ACTION);
				}
				if (!collisionEventActionHashMap.isEmpty()) {
					for (String key : collisionEventActionHashMap.keySet()) {
						String tempActionEventCopy = new String();
						tempActionEventCopy = key + ":" + collisionEventActionHashMap.get(key);
						collisionActionsEvents.add(tempActionEventCopy);
					}
				}
				DisplayModel model = new DisplayModel();
				model.setName(spriteNameTextBox.getText().toString());
				model.setImageUrl(spriteImageComboBox.getSelectedItem()
						.toString());
			/*	model.setWidth(Integer.parseInt(spriteWidthValue.getText()
						.toString()));
				model.setHeight(Integer.parseInt(spriteHeightValue.getText()
						.toString()));*/
				model.setInitX(Integer.parseInt(spriteXTextBox.getText()
						.toString()));
				model.setInitY(Integer.parseInt(spriteYTextBox.getText()
						.toString()));
				map.put(ActionName.KEY_PRESS_EVENT_KEY, actions);
				map.put(ActionName.COLLISION_EVENT, collisionActionsEvents);
				if(selfMove.isSelected()) {
					actions = new ArrayList<String>();
					actions.add(selfMove.getText());
					map.put(ActionName.MOVE_EVENT, actions);
				}
				model.setEventActionMap(map);
				controller.addSprite(model);
				clearFormOnReset();
				setFormValues();
			}
		}

	}

	public void clearFormOnReset() {
		spriteNameTextBox.setText("");
		spriteXTextBox.setText("");
		spriteYTextBox.setText("");
		moveLeft.setSelected(false);
		moveRight.setSelected(false);
		moveUp.setSelected(false);
		moveDown.setSelected(false);
		collisionEventActionHashMap.clear();
		collisionEventList.clearSelection();
		collisionEventActionList.clearSelection();
		collisionEventActionDisplay.setText("");
		selfMove.setSelected(false);
		
	}

	public class resetButtonAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			clearFormOnReset();
		}
	}

	public class DeleteButtonAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedSprite = savedSpriteComboBox.getSelectedItem()
					.toString();
			controller.deleteSprite(selectedSprite);
			clearFormOnReset();
			setFormValues();
		}
	}

	private void setForm(DisplayModel displayModel) {
		spriteNameTextBox.setText(displayModel.getName());
		spriteXTextBox.setText(String.valueOf(displayModel.getInitX()));
		spriteYTextBox.setText(String.valueOf(displayModel.getInitY()));
		String temp = displayModel.getImageUrl().replace("./Images/", "");
		spriteImageComboBox.setSelectedItem(temp);
		moveLeft.setSelected(false);
		moveRight.setSelected(false);
		moveUp.setSelected(false);
		moveDown.setSelected(false);
	}

	public class ComboActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox jb = (JComboBox) e.getSource();
			if (jb.isEnabled()) {
				String selectedSpriteName = jb.getSelectedItem().toString();
				DisplayModel displayModel = controller
						.getSpriteByName(selectedSpriteName);
				setForm(displayModel);
			}
		}
	}

	/*
	 * Add button action listener add the select event action pair into the data
	 * structure and clears the selections in the list so that more pairs can be
	 * selected for a sprite
	 */
	public class AddCollisionEventActionPairActionListener implements
			ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TO-DO write code to fetch the selected pairs and add it to a
			// data structure and clear the JList for more selections
			if (!(collisionEventList.getSelectedValue() == null)) {
				String eventActionListString = new String();
				String temp = new String();
				temp = (collisionEventActionList.getSelectedValue() == null) ? "None"
						: (String) collisionEventActionList.getSelectedValue();
				eventActionListString = collisionEventActionDisplay.getText()
						+ "\n" + collisionEventList.getSelectedValue() + "->"
						+ temp;
				collisionEventActionDisplay.setText(eventActionListString);
				collisionEventActionHashMap.put(collisionEventList
						.getSelectedValue().toString(), temp);
				collisionEventList.clearSelection();
				collisionEventActionList.clearSelection();
			}
		}
	}
}
