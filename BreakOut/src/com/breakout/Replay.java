
package com.breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Replay extends JFrame implements Command {
	Board board;
	public Timer t;

	class TimerTaskListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int ballNewX, ballNewY, paddleNewX;
			if (!board.dequeBallX.isEmpty()) {
				ballNewX = (int) board.dequeBallX.removeFirst();
				ballNewY = (int) board.dequeBallY.removeFirst();
				paddleNewX = (int) board.dequePaddleX.removeFirst();
				board.ball.xdir = (int) board.dequeBallXDir.removeFirst();
				board.ball.ydir = (int) board.dequeBallYDir.removeFirst();
				board.ball.setX(ballNewX);
				board.ball.setY(ballNewY);
				board.paddle.setX(paddleNewX);
				board.paddle.setY(board.paddle.getY());
				board.brick.setX(board.brick.getX());
				board.brick.setY(board.brick.getY());
				board.brick.height = Dimensions.BRICK_HEIGHT;
				board.brick.width = Dimensions.BRICK_WIDTH;
				board.ball.draw(board);
				board.paddle.draw(board);
				board.panelTop.repaint();
			} else {
				t.stop();
			}
		}
	}

	@Override
	public void execute(Board board) {
		this.board = board;
		t = new Timer(10, new TimerTaskListener());
		t.start();

	}
}
