
package com.breakout;

import javax.swing.JLabel;

public class GameTimer implements Observer{
	
	public boolean isPaused = false;
	
	//Storing the current System time
	public static long startTime;
	
	//Variable to store the elapsed time
	public static long timeElapsed;
	
	public static long pausedTimer;
	
	static int seconds      = 0;
	static int minutes      = 0;

	static int milliSeconds = 0;
	
	//Update method to find the elapsed time
	@Override
	public void update(){
//		timeElapsed = System.currentTimeMillis() - startTime;
//		System.out.println(timeElapsed + " : " + startTime);
		if(!isPaused){
		 milliSeconds= milliSeconds+10;
		    if(milliSeconds%1000==0)
		    {
		        if(seconds == 60)
		        {
		            seconds = 0;
		            minutes++;
	            
		        }
		        seconds++;
		    }  
	}
}
	public void resetstate()
	{
		milliSeconds=0;
		minutes=0;
		seconds=0;
		
	}
	//Draw the eplased time on the panel
	@Override
	public void draw(Board com) {
		JLabel label = (JLabel)com.timeLabel;
		 if((com.gameTimer.seconds > 2 && com.gameTimer.minutes == 0) || (com.gameTimer.minutes > 0 && com.gameTimer.seconds >= 0 && com.isRunning == false)){
			 com.undoBtn.setEnabled(true);
		 }
//		int sec = (int)((timeElapsed/1000)%60);
//		int min = (int)(timeElapsed/(60*1000));
//		
//		String secString = String.format("%02d", sec);
//		String minString = String.format("%02d", min);
		
		label.setText(minutes+":"+seconds);
		label.repaint();
	}
	

	public void pause() {
			    // Subtract elapsed time from the remaining time and stop timing
//				long now = System.currentTimeMillis();
//				timeElapsed = 1 + timeElapsed;
		
		
			    
			    /*int sec = (int)((timeElapsed /1000)%60);
				int min = (int)(timeElapsed/(60*1000));
				
				String secString = String.format("%02d", sec);
				String minString = String.format("%02d", min);
				System.out.println(min + ":" + sec);*/
				//timerTask.t.stop();
			   // timeElapsed -= (startTime - timeElapsed);
	}
}
