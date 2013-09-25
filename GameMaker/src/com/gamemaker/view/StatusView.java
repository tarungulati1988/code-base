package com.gamemaker.view;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * StatusView prints status messages to the user.
 * 
 * @author Amey
 * 
 */
public class StatusView {
	JPanel panel;
	JLabel statusLabel;
	
	@SuppressWarnings("unused")
	private StatusView() {
		
	}
	
	public StatusView(JPanel panel) {
		this.panel = panel;
		this.statusLabel = new JLabel();
		setupPanel();
	}

	private void setupPanel() {
		this.panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.panel.add(statusLabel);

		// Layout Strategy
		FlowLayout flowLayout = new FlowLayout();
		this.panel.setLayout(flowLayout);
		statusLabel.setText("Ready");
	}

	public void notify(String message) {
		statusLabel.setText(message);
	}
}
