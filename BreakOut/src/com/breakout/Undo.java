
package com.breakout;


public class Undo implements Command{

	
	/* (non-Javadoc)
	 * @see com.breakout.Command#execute()
	 */
	@Override
	public void execute(Board board) {
		//board.dequeBallX
		System.out.println("undo");
		board.pausePlayObj.execute(board);
		System.out.println(board.dequeBallX);
		System.out.println(board.dequeBallY);
		System.out.println(board.dequePaddleX);
		//board.ball.setX(245);
		int ballNewX, ballNewY, paddleNewX, ballNewXDir, ballNewYDir;
		for(int i = 0; i < 200 ; i++){
			ballNewX = (int)board.dequeBallX.removeLast();
			ballNewY = (int)board.dequeBallY.removeLast();
			paddleNewX = (int)board.dequePaddleX.removeLast();
			board.ball.setX(ballNewX);
			board.ball.setY(ballNewY);
			board.paddle.setX(paddleNewX);
			board.paddle.setY(board.paddle.getY());
			board.ball.xdir = (int)board.dequeBallXDir.removeLast();
			board.ball.ydir = (int)board.dequeBallYDir.removeLast();
		}
		//board.panelRight.repaint();
		board.gameTimer.seconds = board.gameTimer.seconds - 2;
		board.gameTimer.milliSeconds = board.gameTimer.milliSeconds - 2000;
		board.pausePlayObj.execute(board);
		
	}

}
