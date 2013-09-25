package com.gamedisplay.model.action;

import static org.junit.Assert.*;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.gamedisplay.model.ActionList;
import com.gamedisplay.model.Coordinate;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.EventActionMap;
import com.gamedisplay.model.GameInformation;
import com.gamedisplay.model.Sprite;
import com.gamedisplay.model.event.KeyPressEvent;
import com.gamemaker.utility.JSONAdaptor;

//Class MoveLeftAction is tested. 
//Key press event is created and checked before and after Coordinates.

public class MoveLeftActionTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void test() {

		Sprite sprite = new Sprite();
		Coordinate coordinate = new Coordinate(0, 0);
		
		coordinate.setxPosition(10);
		coordinate.setyPosition(10);
		sprite.setInitCoordinate(coordinate);
		
		Event event = new KeyPressEvent();
		
		HashMap<Event, ActionList> map = new HashMap<Event, ActionList>();
		ActionList al = new ActionList();
		al.add(new MoveLeftAction());
		map.put(event, al);
		EventActionMap eventActionMap = new EventActionMap();
		eventActionMap.setEventActionMap(map);
		sprite.setEventActionMap(eventActionMap);

		Coordinate CoordinateBefore = sprite.getCurrentCoordinate();
		
		MoveLeftAction moveaction = new MoveLeftAction();
		moveaction.performAction(sprite);
		
		Coordinate CoordinateAfter = new Coordinate(sprite.getCurrentCoordinate());
		
		Assert.assertNotSame(CoordinateAfter, CoordinateBefore);
		
		
	}
}
