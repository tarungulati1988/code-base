
package com.breakout;

import javax.swing.JComponent;


public interface Observable {
	public void register(Observer observer, Board com);
	public void unRegister(Observer observer);
	public void notifyObservers();
}
