
package com.breakout;

import java.awt.Graphics;

import javax.swing.JComponent;

public interface Observer {
	public void update();
	public void draw(Board com);
}
