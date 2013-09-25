package com.breakout;

public class BallSaveableLoadable extends SaveableLoadable {

	public int[] get(Board boardObj) {

		int coords[] = new int[4];
		//coords[0] = boardObj.ball.getX();
		coords[0] = (int)boardObj.dequeBallX.peekLast();
		//coords[1] = boardObj.ball.getY();
		coords[1] = (int)boardObj.dequeBallY.peekLast();
		coords[2] = boardObj.ball.xdir;
		coords[3] = boardObj.ball.ydir;
		return coords;
	}

	public void set(Board boardObj, int ballCoords[]) {

		boardObj.ball.setX(ballCoords[0]);
		boardObj.ball.setY(ballCoords[1]);
		boardObj.ball.xdir = ballCoords[2];
		boardObj.ball.ydir = ballCoords[3];
		//TODO set the width and height

	}
}
