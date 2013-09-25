package com.gamemaker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.gamecontroller.GameMakerController;

public class AppWindow {
	private JFrame applicationFrame;
	private final JPanel leftPanel, rightPanel, bottomPanel;
	private final JMenuBar menuBar;

	private AppWindow() {
		this.applicationFrame = null;
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		bottomPanel = new JPanel();
		menuBar = new JMenuBar();
	}

	public AppWindow(JFrame jFrame) {
		this();
		this.applicationFrame = jFrame;
		setMainWindowProperties();
		addSubPanels();

		GameMakerController gameMakerController = new GameMakerController(
				leftPanel, rightPanel, bottomPanel);

		new ApplicationMenuView(menuBar, gameMakerController);

		this.applicationFrame.pack();
	}

	private void addSubPanels() {
		// Our strategy here is to use grid layout. This nicely works with
		// multiple component inside

		this.applicationFrame.setJMenuBar(menuBar);
		
		JPanel gameViewPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(1, 2);
		gameViewPanel.setLayout(gridLayout);

		gameViewPanel.add(leftPanel);
		gameViewPanel.add(rightPanel);

		BorderLayout borderLayout = new BorderLayout();
		this.applicationFrame.setLayout(borderLayout);
		this.applicationFrame.add(gameViewPanel, BorderLayout.CENTER);
		this.applicationFrame.add(bottomPanel, BorderLayout.SOUTH);
	}

	private void setMainWindowProperties() {
		// In order to display few essential elements, we need to set min size
		applicationFrame.setMinimumSize(new Dimension(
				Dimensions.MIN_WINDOW_WIDTH, Dimensions.MIN_WINDOW_HEIGHT));
		applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		applicationFrame.setVisible(true);
	}
}
