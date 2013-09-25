package com.gamecontroller;

/**
 * This are game terminating conditions. If any action (mostly collision action)
 * has associated either win or loose condition, then upon executing this
 * action, we return the condition being fulfilled. i.e. user has won or lost
 * the game
 * 
 * @author Tarun
 * 
 */
public class GameState {
	public static final String GAME_WIN = "Game Win";
	public static final String GAME_LOSE = "Game Lose";
	public static final String NONE = "None";
}
