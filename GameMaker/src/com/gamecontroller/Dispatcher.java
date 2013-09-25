package com.gamecontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gamedisplay.model.Action;
import com.gamedisplay.model.ActionList;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.EventActionMap;
import com.gamedisplay.model.Sprite;
import com.gamedisplay.model.event.CollisionEvent;
import com.gamedisplay.model.event.GameLooseEvent;

/**
 * Dispatcher is helper mediator that is use by Controllers to associate
 * triggered actions to the registered sprites. In our architectures, we are
 * storing supported events->actions map with sprite. But during game play, we
 * need action->sprites mapping. Naive way of searching through all sprites is
 * foolish and takes more time. Hence this complex data structure is maintained
 * that basically optimizes the lookup during run time.
 * 
 * @author Amey Jahagirdar
 * 
 */
public class Dispatcher {
	// Stores Actions to list of registered sprites. Whenever any action is
	// fired we lookup into this map for quick retrieval of sprites.
	Map<Action, ArrayList<Sprite>> eventSpriteMap;

	public Dispatcher() {
		eventSpriteMap = new HashMap<Action, ArrayList<Sprite>>();
	}

	public void dispatch(Action action) {
		for (Sprite sprite : getArray(action)) {
			action.performAction(sprite);
		}
	}

	/*
	 * For given event, we will find out which actions are supported. Then we
	 * look up into our cache for registered sprites for those actions. It then
	 * fires those actions on given sprite
	 */
	public String dispatch(CollisionEvent collisionEvent) {
		Sprite sprite1 = collisionEvent.getSprite1();
		Sprite sprite2 = collisionEvent.getSprite2();

		if (sprite1.getEventActionMap().getEventActionMap()
				.containsKey(collisionEvent)) {
			ActionList actionList = sprite1.getEventActionMap()
					.getEventActionMap().get(collisionEvent);
			for (Action action : actionList) {
				String state = action.performAction(sprite1);
				if (state.equals(GameState.GAME_WIN)
						|| state.equals(GameState.GAME_LOSE))
					return state;
			}
		}

		if (sprite2.getEventActionMap().getEventActionMap()
				.containsKey(collisionEvent)) {
			ActionList actionList = sprite2.getEventActionMap()
					.getEventActionMap().get(collisionEvent);
			for (Action action : actionList) {
				String state = action.performAction(sprite2);
				if (state.equals(GameState.GAME_WIN)
						|| state.equals(GameState.GAME_LOSE))
					return state;
			}
		}

		return GameState.NONE;
	}

	public String dispatch(GameLooseEvent gle) {
		ActionList supportedActionsForGivenEvent = gle.getSupportedActions();
		for (Action action2 : supportedActionsForGivenEvent) {
			for (Sprite sprite : getArray(action2)) {
				String state = action2.performAction(sprite);
				if (state.equals(GameState.GAME_WIN)
						|| state.equals(GameState.GAME_LOSE))
					return state;
			}
		}

		return GameState.NONE;
	}

	/*
	 * We need to iterate over all supported actions of that given sprite. To
	 * get this info; we iterate as get all supported events for sprite then for
	 * all supported events, find out supported actions.
	 * 
	 * We need to then add this to our action->sprites map
	 */
	public void updateEntry(Sprite sprite) {

		EventActionMap eventActionMap = sprite.getEventActionMap();
		Set<Event> eventSet = eventActionMap.getEventActionMap().keySet();
		for (Event spriteEvent : eventSet) {
			ActionList spriteActionList = eventActionMap.getEventActionMap()
					.get(spriteEvent);
			for (Action action : spriteActionList) {
				ArrayList<Sprite> spriteArray = getArray(action);
				spriteArray.add(sprite);
				eventSpriteMap.put(action, spriteArray);
			}
		}

	}

	/*
	 * In Java, map returns null exception whenever non existing key is searched
	 * for the value. To overcome this, we wrong this small helper function that
	 * returns blank value whenever key is not found
	 */
	private ArrayList<Sprite> getArray(Action action) {
		if (eventSpriteMap.containsKey(action) == false) {
			ArrayList<Sprite> sprites = new ArrayList<Sprite>();
			eventSpriteMap.put(action, sprites);
		}
		return eventSpriteMap.get(action);
	}
}
