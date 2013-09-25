package com.gamemaker.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.gamecontroller.GameMakerController;

public class GameDisplayForm1View {
	private JPanel panel;
	private final JPanel newGamePanel, loadGamePanel;
	private GameMakerController controller;
	private final JTextField gameNameTextBox;
	private final JComboBox loadGameNameComboBox;
	private final JLabel newGameLabel;
	private final JLabel loadGameLabel;
	private final JButton newGameBtn;
	private final JButton loadGameBtn;

	private GameDisplayForm1View() {
		newGamePanel = new JPanel();
		loadGamePanel = new JPanel();
		gameNameTextBox = new JTextField("", 5);
		loadGameNameComboBox = new JComboBox();
		newGameLabel = new JLabel(LabelNames.FORM1_NEW_GAME_LABEL);
		loadGameLabel = new JLabel(LabelNames.FORM1_LOAD_GAME_LABEL);
		newGameBtn = new JButton(LabelNames.FORM1_NEW_GAME_BUTTON);
		loadGameBtn = new JButton(LabelNames.FORM1_LOAD_GAME_BUTTON);

		newGameBtn.addActionListener(new NewGameButtonActionListener());
		loadGameBtn.addActionListener(new LoadGameButtonActionListener());
	}

	public GameDisplayForm1View(JPanel jPanel,
			GameMakerController gameMakerController) {
		this();
		this.panel = jPanel;
		this.controller = gameMakerController;
		String savedGameNames[] = controller.getPreviouslySavedGames();
		
		for (String string : savedGameNames) {
			this.loadGameNameComboBox.addItem(string);
		}

		createForm();
	}

	private void createForm() {
		// Save game panel
		newGamePanel.add(newGameLabel);
		newGamePanel.add(gameNameTextBox);
		newGamePanel.add(newGameBtn);
		// Load game panel

		loadGamePanel.add(loadGameLabel);
		loadGamePanel.add(loadGameNameComboBox);
		loadGamePanel.add(loadGameBtn);

		this.panel.add(newGamePanel);
		this.panel.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.panel.add(loadGamePanel);

	}

	public void hide() {
		this.newGamePanel.setVisible(false);
		this.loadGamePanel.setVisible(false);
	}

	public void show() {
		this.newGamePanel.setVisible(true);
		this.loadGamePanel.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	public void resetForm()
	{
		this.gameNameTextBox.setText("");
		this.loadGameNameComboBox.removeAllItems();
		String savedGameNames[] = controller.getPreviouslySavedGames();
		for (String string : savedGameNames) {
			this.loadGameNameComboBox.addItem(string);
		}
		if(!(loadGameNameComboBox.getItemCount()<1))
		{
		this.loadGameNameComboBox.setSelectedIndex(0);
		}
	}
	

	class NewGameButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String name = gameNameTextBox.getText();
			if (name.length() != 0) {
				controller.createNewGame(name);
			}
		}

	}

	class LoadGameButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String name = (String) loadGameNameComboBox.getSelectedItem();
			if (name.length() != 0) {
				controller.resumeSavedGame(name);
			}
		}

	}
}
