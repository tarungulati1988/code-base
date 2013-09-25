package com.gamemaker.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gamecontroller.GameMakerController;
import com.gamecontroller.GamePlayController;

public class SelectGameToBePlayed {
	private final GameMakerController controller;
	@SuppressWarnings("unused")
	private GamePlayController playController;
	private JComboBox options = null;

	public SelectGameToBePlayed() {
		controller = new GameMakerController();
		// TODO Auto-generated constructor stub
		JFrame frame = new JFrame("Game Options");
		JPanel panel = new JPanel();
		options = new JComboBox(controller.getPreviouslySavedGames());
		JButton playBtn = new JButton("Play");
		playBtn.addActionListener(new PlayButtonActionListener());
		playBtn.setBounds(150, 200, 50, 50);
		panel.add(options);
		panel.add(playBtn);
		frame.add(panel);
		frame.setBounds(300, 300, 300, 300);
		frame.setVisible(true);
	}

	class PlayButtonActionListener implements ActionListener {
		String selectedOption;

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			selectedOption = (String) options.getSelectedItem();
			playController = new GamePlayController(selectedOption);

		}

	}
}
