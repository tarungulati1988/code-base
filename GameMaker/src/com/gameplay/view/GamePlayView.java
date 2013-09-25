package com.gameplay.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gamecontroller.GamePlayController;
import com.gamedisplay.model.Constants;
import com.gamedisplay.model.Sprite;
import com.gamemaker.view.SpritePaint;

@SuppressWarnings("serial")
public class GamePlayView extends JFrame {

	private final JPanel panel;
	private final GamePlayController controller;
	public JLabel gameStatusLabel = new JLabel();

	public GamePlayView(GamePlayController controller) {
		this.controller = controller;
		panel = new JPanel();
		panel.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		panel.setLayout(null);
		panel.add(gameStatusLabel);
		
		this.setBounds(0, 0, 700, 700);
		
		this.add(panel);
		this.setVisible(true);

		//this.validate();
		this.addKeyListener(new KeyboardActionListener());

	}

	public void paintSprite(List<Sprite> list) {

		panel.removeAll();
		for (Sprite s : list) {
			if (s.isVisible()) {
				SpritePaint sp = new SpritePaint(s, this.panel);
				panel.add(sp);
				panel.add(gameStatusLabel);
				
			}
		}
		
		panel.revalidate();
		panel.repaint();

	}


	class KeyboardActionListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent keyEvent) {

			int keyCode = keyEvent.getKeyCode();
			if (keyCode == KeyEvent.VK_LEFT) {
				controller.leftKeyIsPressed();

			} else if (keyCode == KeyEvent.VK_RIGHT) {
				controller.rightKeyIsPressed();

			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {

		}

		@Override
		public void keyTyped(KeyEvent arg0) {

		}

	}
}