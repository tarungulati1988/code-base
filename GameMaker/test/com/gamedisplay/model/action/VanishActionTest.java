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
import com.gamedisplay.model.event.KeyPressEvent;

//Class VanishAction is tested. 
//Key press event is created and checked visibility.

public class VanishActionTest {

	@Test
	public void test() {
		Sprite sprite = new Sprite();
		
		Event event = new KeyPressEvent();
		HashMap<Event, ActionList> map = new HashMap<Event, ActionList>();
		ActionList al = new ActionList();
		al.add(new VanishAction(GameState.NONE));
		map.put(event, al);
		EventActionMap eventActionMap = new EventActionMap();
		eventActionMap.setEventActionMap(map);
		sprite.setEventActionMap(eventActionMap);

		Boolean visibilityBefore = sprite.isVisible() ;
		
		VanishAction vanishaction = new VanishAction(GameState.NONE);
		vanishaction.performAction(sprite);
		
		Boolean visbilityAfter = sprite.isVisible();
		
		Assert.assertNotSame(visbilityAfter, visibilityBefore);

	}

}
