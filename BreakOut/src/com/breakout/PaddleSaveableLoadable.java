package com.breakout;

public class PaddleSaveableLoadable extends SaveableLoadable {

	public int[] get(Board boardObj) {

		int coords[] = new int[1];
		coords[0] = boardObj.paddle.getX();
		return coords;

	}

	public void set(Board boardObj, int paddleCoords[]) {
		
		boardObj.paddle.setX(paddleCoords[0]);
		//TODO set paddle y, width and height
		
	}
}
