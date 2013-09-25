package com.gamecontroller;

/**
 * Constants class to gold action names. Our controller-view communication takes
 * place in pojo languages like string and int. So we need them to be defined
 * for UI and controller decoding.
 */
public class ActionName {
	public static String MOVE_LEFT_ACTION = "MoveLeft";
	public static String MOVE_RIGHT_ACTION = "MoveRight";
	public static String MOVE_UP_ACTION = "MoveUp";
	public static String MOVE_DOWN_ACTION = "MoveDown";
	public static String SELF_MOVE = "SelfMove";
	public static String REFLECT_EVENT = "Reflect";
	public static String FALL_THROUGH_EVENT = "FallThrough";
	public static String DISAPPEAR_EVENT = "Disappear";
	public static String GAME_WIN_ACTION = "GameWin";
	public static String GAME_LOSE_ACTION = "GameLose";
	public static String NONE_ACTION = "None";
	public static String KEY_PRESS_EVENT_KEY = "KeyPressEvent";
	public static String COLLISION_EVENT = "CollisionEvent";
	public static String MOVE_EVENT = "MoveEvent";
}
