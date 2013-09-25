package com.gamecontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gamedisplay.model.ActionList;
import com.gamedisplay.model.Constants;
import com.gamedisplay.model.Coordinate;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.EventActionMap;
import com.gamedisplay.model.Sprite;
import com.gamedisplay.model.action.MoveAction;
import com.gamedisplay.model.action.MoveLeftAction;
import com.gamedisplay.model.action.MoveRightAction;
import com.gamedisplay.model.action.ReflectAction;
import com.gamedisplay.model.action.VanishAction;
import com.gamedisplay.model.event.CollisionEvent;
import com.gamedisplay.model.event.KeyPressEvent;
import com.gamedisplay.model.event.MoveEvent;
import com.gamemaker.model.DisplayModel;

/**
 * Helper utility to convert DisplayModel to Sprite.
 * 
 * In our MVC, controllers and views only talks via simple pojo objects.
 * DisplayModel is a pojo style object. This helper utility can quickly map
 * view's input inside the controller
 * 
 * @author Amey, Sneha, Tarun
 * 
 */
public class DisplayModelToSpriteMapper {

	public static void map(DisplayModel displayModel, Sprite sprite) {
		sprite.setName(displayModel.getName());
		sprite.setImageFileUrl(Constants.STORED_IMAGE_LOCATION_PREFIX
				+ displayModel.getImageUrl());
		sprite.setInitCoordinate(new Coordinate(displayModel.getInitX(),
				displayModel.getInitY()));
		sprite.setHeight(displayModel.getHeight());
		sprite.setWidth(displayModel.getWidth());
		sprite.setEventActionMap(getEventActionMap(displayModel
				.getEventActionMap()));
	}

	/*
	 * We get String(Event)->ActionList(String). But we need to decode this
	 * string to our data structures. Way to do that is; - Read the string event
	 * - > Decode it - Read each string action -> Decode it - Prepare an array
	 * of actions. - Put event, actionArray in a map
	 */
	private static EventActionMap getEventActionMap(
			Map<String, ArrayList<String>> stringEventActionMap) {
		Set<String> keys = stringEventActionMap.keySet();
		HashMap<Event, ActionList> tempEventActionMap = new HashMap<Event, ActionList>();
		for (String key : keys) {
			ArrayList<String> stringActions = stringEventActionMap.get(key);
			Event event = getEvent(key);
			ActionList actions = new ActionList();
			if (event != null) {
				for (String stringAction : stringActions) {
					if (ActionName.MOVE_LEFT_ACTION.equals(stringAction)) {
						actions.add(new MoveLeftAction());
					} else if (ActionName.MOVE_RIGHT_ACTION
							.equals(stringAction)) {
						actions.add(new MoveRightAction());
					} else if (ActionName.SELF_MOVE.equals(stringAction)) {
						actions.add(new MoveAction());
					} else if (ActionName.REFLECT_EVENT.equals(stringAction.split(":")[0])) {
						actions.add(new ReflectAction(stringAction.split(":")[1]));
					} else if (ActionName.DISAPPEAR_EVENT.equals(stringAction.split(":")[0])) {
						actions.add(new VanishAction(stringAction.split(":")[1]));
					}
				}
				tempEventActionMap.put(event, actions);
			}

		}

		EventActionMap eventActionMap = new EventActionMap();
		eventActionMap.setEventActionMap(tempEventActionMap);
		return eventActionMap;
	}

	private static Event getEvent(String key) {
		if (key.equals(ActionName.KEY_PRESS_EVENT_KEY)) {
			return new KeyPressEvent();
		}
		if (key.equals(ActionName.COLLISION_EVENT)) {
			return new CollisionEvent();
		}
		if (key.equals(ActionName.MOVE_EVENT)) {
			return new MoveEvent();
		}
		return null;
	}

}
