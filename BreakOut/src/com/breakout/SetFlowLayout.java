package com.breakout;



import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class SetFlowLayout implements Strategy {

		@Override
	public void changelayout(Board B) {
		B.panelBottom.setLayout(new FlowLayout());
		B.panelBottom.add(B.startStopBtn);
		B.panelBottom.add(B.saveBtn);
		B.panelBottom.add(B.loadBtn);
        B.panelBottom.add(B.pausePlayBtn);
        B.panelBottom.add(B.undoBtn);
        B.panelBottom.add(B.replayBtn);
        B.panelBottom.add(B.switchlayoutf);
        System.out.println(B.panelBottom.getLayout());
        B.panelBottom.remove(B.switchlayoutb);
		
		
	}

	
}

