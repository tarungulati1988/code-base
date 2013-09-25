
package com.breakout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Ball extends Sprite implements Dimensions, Observer {
	public int xdir;
	public int ydir;	
	public int height;
	public int width;
	public int newx;
	public int newy;
	public Board board;
	public int ballwidth;
	public int ballheight;
	
	public void set(Boolean isRunning,int bheight, int bwidth)
	{
		ballheight=5*bheight/100;
		ballwidth=5*bheight/100;
		if(!isRunning)
		{
			x = (bwidth-120)/2 + (13*bwidth/100)/2-5*bheight/200;
			y =  bheight-170-(5*bheight/100);
		}
		if(x>bwidth)
			
		{
			x=bwidth-120;
		}
	newx = (bwidth-120)/2 + (13*bwidth/100)/2-5*bheight/200;
	newy =  bheight-170-(5*bheight/100);
	height=bheight;
	width=bwidth;
	
	
	}
	
	public Ball(Board board, int bheight, int bwidth)
	{
		this.board = board;
		//System.out.print(+height);
		//Setting initial coordinates of ball
		x = (bwidth-120)/2 + (13*bwidth/100)/2-5*bheight/200;
		y =  bheight-170-(5*bheight/100);
		ballheight=5*bheight/100;
		ballwidth=5*bheight/100;
		
		newx = (bwidth-120)/2 + (13*bwidth/100)/2-5*bheight/200;
		newy =  bheight-170-(5*bheight/100);
		
		//Setting initial direction in which ball should move
		int arrXdir[] = {1,-1};
		
		Random random = new Random();
		
		xdir = arrXdir[random.nextInt(arrXdir.length)];
		ydir = -1;
	}
	
	public int getheight()
	{
		return ballheight;
	}
	
	
	public int getwidth()
	{
		return ballwidth;
	}
	public void resetState()
	{
		x = this.newx;
		y = this.newy;
		int arrXdir[] = {1,-1};
		
		Random random = new Random();
		
		xdir = arrXdir[random.nextInt(arrXdir.length)];
		ydir = -1;
	}
	//Check if the ball collides with the right end
	public boolean isCollidingWithRightWall()
	{
		if(x >= (this.width-120))
		{
			board.playSound("..//team7//sound//btnPlay.wav");
			return true;
			
		}
		return false;
	}

	//Check if the ball collides with the top end
	public boolean isCollidingWithTopWall()
	{
		if(y <= 45)
		{
			board.playSound("..//team7//sound//btnPlay.wav");
			return true;
		}
		return false;
	}
	
	//Check if the ball collides with the left end
	public boolean isCollidingWithLeftWall()
	{
		if(x <= 10)
		{
			board.playSound("..//team7//sound//btnPlay.wav");
			return true;
		}
		return false;
	}
	
	//Check if the ball collides with the paddle
	public boolean isCollidingWithPaddle()
	{
		
		if(x >= board.paddle.getX() - 30 && x <= board.paddle.getX() + board.paddle.paddlewidth + 30)
		{
			if(y >= board.paddle.getY()-25 && y <= board.paddle.getY()+5)
			{
				return true;
			}
		}
		return false;
	}
	
	//Check if the ball collides with the brick
	public boolean isCollidingWithBrick()
	{
		if(x >= board.brick.getX() - this.ballheight  && x <= board.brick.getX() + board.brick.width && y >= board.brick.getY() - this.ballheight && y <= board.brick.getY() + board.brick.height)
		{
			
			board.brick.height = 0;
			board.brick.width = 0;
			board.playSound("..//team7//sound//btnPlay.wav");
			return true;
			
		}
		return false;
	}
	
	//Check if the ball has touched the floor
	public boolean isGameOver()
	{
		if(x <= board.paddle.getX() - 40 || x >= board.paddle.getX() + 40)
		{
			if(y >= this.newy+15)
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 	update() method of the Observer class
	 * Used to update the position of the ball
	*/
	@Override
	public void update(){
		//System.out.println(x+","+y);
		if(!board.isReplay){
			board.dequeBallX.add(x);
			board.dequeBallY.add(y);
			board.dequePaddleX.add(board.paddle.getX());
			board.dequeBallXDir.add(xdir);
			board.dequeBallYDir.add(ydir);
		}
		
		/*System.out.println("-----------------");
		System.out.println(xdir);
		System.out.println(ydir);*/
		
		/*System.out.println("balldeque x size: " + board.dequeBallX);
		System.out.println("paddle deque x: " + board.dequePaddleX);*/
					
		if(isCollidingWithRightWall())
		{
			xdir = -1;
		}
		
		if(isCollidingWithTopWall())
		{
			ydir = 1;
		}
		
		if(isCollidingWithLeftWall())
		{
			xdir = 1;
		}
		
		if(isCollidingWithPaddle())
		{
			ydir = -1;
		}
		
		if(isCollidingWithBrick())
		{
			// player wins when brick is destroyed
			// all the observers are unregistered
			board.isRunning = false;
			board.isFirstTime = true;
			board.msgLabel.setText("You win!  ");
			board.timerTask.unRegister(this);
			board.timerTask.unRegister(board.paddle);
			board.timerTask.unRegister(board.gameTimer);
			board.replayBtn.setEnabled(true);
			board.startStopBtn.setEnabled(false);
			//board.startStopBtn.setText("Start");
			board.pausePlayBtn.setEnabled(false);
			board.undoBtn.setEnabled(false);
			board.panelTop.repaint();
		}
		
		if(isGameOver())
		{
			// player looses when the ball falls through the floor
			// game over message is printed
			// and all the observers are unregistered
			board.isRunning = false;
			board.msgLabel.setText("Game Over!");
			board.timerTask.unRegister(this);
			board.timerTask.unRegister(board.paddle);
			board.timerTask.unRegister(board.gameTimer);
			board.replayBtn.setEnabled(true);
			board.startStopBtn.setEnabled(false);
			board.pausePlayBtn.setEnabled(false);
			board.undoBtn.setEnabled(false);
		}
		x += xdir;
		y += ydir;
	}

	/*
	 * Repaint the board
	 */
	@Override
	public void draw(Board com){
		com.repaint();
	}
}
