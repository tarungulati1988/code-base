package com.gamemaker.view;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gamedisplay.model.Constants;
import com.gamedisplay.model.Coordinate;
import com.gamedisplay.model.Sprite;


/**
 * Handy class to paint given sprite on the view
 * 
 * @author Madhu
 * 
 */
@SuppressWarnings("serial")
public class SpritePaint extends JLabel {

	Coordinate coordinate;
	public int xpos;
	public int abcd;
	int ypos;
	Sprite sprite;
	public  JPanel panel;
	
	

	public SpritePaint(Sprite sprite, JPanel panel) {
		this.panel = panel;

		this.sprite = sprite;
		coordinate = sprite.getCurrentCoordinate();

		xpos = coordinate.getxPosition();
		ypos = coordinate.getyPosition();
		int xPercent = (100 * xpos) / Constants.WIDTH;
		int yPercent = (100 * ypos) / Constants.HEIGHT;
		int newX = (int)Math.ceil(((double)xpos * (double)this.panel.getWidth()) / Constants.WIDTH);
		int newY = (int)Math.ceil(((double)ypos * (double)this.panel.getHeight()) / Constants.HEIGHT);
		Icon image = new ImageIcon(sprite.getImageFileUrl());
		this.setIcon(image);
		this.setBounds(newX, newY, image.getIconWidth(), image.getIconHeight());
		this.setBackground(Color.darkGray);
		this.revalidate();
		this.repaint();
		
	}
	
	
}
