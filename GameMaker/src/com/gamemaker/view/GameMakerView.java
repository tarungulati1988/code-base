package com.gamemaker.view;

import java.awt.Color;

import javax.swing.JPanel;

import com.gamecontroller.GameMakerController;

class GameMakerView {
	private JPanel panel;
	private GameMakerController gameMakerController;

	private GameMakerView() {

	}

	GameMakerView(JPanel jPanel, GameMakerController gameMakerController) {
		this.panel = jPanel;
		this.gameMakerController = gameMakerController;
		decoratePanel();
		new GameDisplayForm1View(this.panel, this.gameMakerController);
		// new GameDisplayForm2View(this.panel);
	}


	private void decoratePanel() {
		this.panel.setBackground(Color.DARK_GRAY);

	}

}
