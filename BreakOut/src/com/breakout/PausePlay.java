
package com.breakout;


public class PausePlay implements Command{

	/* (non-Javadoc)
	 * @see com.breakout.Command#execute()
	 */
	@Override
	public void execute(Board board) {
		// TODO Auto-generated method stub
		//System.out.println("command request executed");
		if(board.isRunning== true && (board.pausePlayBtn.getText()).equalsIgnoreCase("Pause")){
			//Board.this.timerTask.unRegister(gameTimer);
		//	gameTimer.pause();
			board.isFirstTime = false;
			board.timerTask.stop();
			board.startStopBtn.setEnabled(false);
			board.undoBtn.setEnabled(false);
			board.pausePlayBtn.setText("Play");
		}
		else if((board.pausePlayBtn.getText()).equalsIgnoreCase("Play")){
			//Board.this.timerTask.register(gameTimer, Board.this);
			//board.timerTask.press(board.pausePlayBtn,board.this);
			board.requestFocus();
			board.timerTask.run();
			//isFirstTime = true;
			//isRunning = false;
			board.startStopBtn.setEnabled(true);
			board.pausePlayBtn.setText("Pause");
		}
	}
	}
	
	


