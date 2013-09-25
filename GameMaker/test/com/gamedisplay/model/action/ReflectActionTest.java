package com.gamedisplay.model.action;

import static org.junit.Assert.*;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import com.gamecontroller.GameState;
import com.gamedisplay.model.ActionList;
import com.gamedisplay.model.Coordinate;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.EventActionMap;
import com.gamedisplay.model.Sprite;
import com.gamedisplay.model.event.CollisionEvent;
import com.gamedisplay.model.event.KeyPressEvent;

//Class ReflectAction is tested. 
//Collision event is created and checked before and after Coordinates.

public class ReflectActionTest {

	@Test
	public void test() {
		Sprite sprite = new Sprite();
		Coordinate coordinate = new Coordinate(0, 0);
		coordinate.setxPosition(10);
		coordinate.setyPosition(10);
		sprite.setInitCoordinate(coordinate);
		
		Event event = new CollisionEvent();
		
		HashMap<Event, ActionList> map = new HashMap<Event, ActionList>();
		ActionList al = new ActionList();
		al.add(new ReflectAction(GameState.NONE));
		map.put(event, al);
		EventActionMap eventActionMap = new EventActionMap();
		eventActionMap.setEventActionMap(map);
		sprite.setEventActionMap(eventActionMap);

		Coordinate CoordinateBefore = sprite.getCurrentCoordinate();
		
		ReflectAction reflectaction = new ReflectAction(GameState.NONE);
		reflectaction.performAction(sprite);
		
		Coordinate CoordinateAfter = new Coordinate(sprite.getCurrentCoordinate());
		
		Assert.assertNotSame(CoordinateAfter, CoordinateBefore);
	}
}
