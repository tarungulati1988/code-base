package com.breakout;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Context {
	private Strategy strategy;
	
	
	
	public Context(Strategy strategy)
	{
		
		this.strategy = strategy;
	}
	
	
	
	public void executeStrategy(Board B)
	
	{
		
		this.strategy.changelayout(B);
	}
	
	

	
}
