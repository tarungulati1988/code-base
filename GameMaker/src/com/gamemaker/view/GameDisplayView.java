package com.gamemaker.view;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

import com.gamedisplay.model.Sprite;

public class GameDisplayView {
	private JPanel panel;

	private GameDisplayView() {

	}

	public GameDisplayView(JPanel jPanel) {
		this();
		this.panel = jPanel;
		this.panel.setLayout(null);
		decoratePanel();
	}

	private void decoratePanel() {
		this.panel.setBackground(Color.lightGray);
	}

	public void showSprite(Sprite sprite) throws IOException {
		SpritePaint sp = new SpritePaint(sprite, this.panel);
		this.panel.add(sp);
		this.panel.revalidate();
		this.panel.repaint();
	}

	public void showSprite(List<Sprite> sprites) throws IOException {
		this.panel.removeAll();
		for (Sprite sprite : sprites) {
			showSprite(sprite);
		}
	}
	
	public void resetForm()
	{
		this.panel.removeAll();
	}

}
