package com.breakout;

public class BrickSaveableLoadable extends SaveableLoadable {

	public int[] get(Board boardObj) {

		int coords[] = new int[2];
		coords[0] = boardObj.brick.getX();
		coords[1] = boardObj.brick.getY();
		return coords;

	}

	public void set(Board boardObj, int brickCoords[]) {

		boardObj.brick.setX(brickCoords[0]);
		boardObj.brick.setY(brickCoords[1]);
		//TODO set the width and height
		
	}
}
