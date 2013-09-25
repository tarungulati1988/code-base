package com.breakout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class SetBorderLayout implements Strategy {

		@Override
	public void changelayout(Board boardObj) {
			JPanel panelCenter = new JPanel();
			boardObj.panelBottom.setLayout(new BorderLayout(0, 0));
			boardObj.panelBottom.add(boardObj.startStopBtn,BorderLayout.EAST);
			boardObj.panelBottom.add(boardObj.pausePlayBtn,BorderLayout.WEST);
			boardObj.panelBottom.add(boardObj.undoBtn,BorderLayout.NORTH);
			boardObj.panelBottom.add(boardObj.replayBtn,BorderLayout.SOUTH);
			panelCenter.add(boardObj.switchlayoutb);
			panelCenter.add(boardObj.saveBtn);
			panelCenter.add(boardObj.loadBtn);
			//boardObj.panelBottom.add(boardObj.switchlayoutb,BorderLayout.CENTER);
			boardObj.panelBottom.add(panelCenter,BorderLayout.CENTER);
			
			System.out.println(boardObj.panelBottom.getLayout());
			boardObj.panelBottom.remove(boardObj.switchlayoutf);
        		
		
	}

	
}