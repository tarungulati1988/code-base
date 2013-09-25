package com.breakout;

public class ClockSaveableLoadable extends SaveableLoadable {
	public int[] get(Board boardObj) {

		int timer[] = new int[2];
		timer[0] = boardObj.gameTimer.minutes;
		timer[1] = boardObj.gameTimer.seconds;
		return timer;

	}

	public void set(Board boardObj, int timerData[]) {

		boardObj.gameTimer.minutes = timerData[0];
		boardObj.gameTimer.seconds = timerData[1];

	}
}
