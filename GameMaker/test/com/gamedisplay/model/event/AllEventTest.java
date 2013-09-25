package com.gamedisplay.model.event;

import static org.junit.Assert.*;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import com.gamedisplay.model.ActionList;
import com.gamedisplay.model.Event;

public class AllEventTest {

	@Test
	public void testHashSimilarityOfCollisionEvent() {
	    
	    Event event = new CollisionEvent();
	    ActionList al1 = event.getSupportedActions();
	    HashMap<Event, ActionList> map = new HashMap<Event, ActionList>();
	    map.put(event, al1);
	    ActionList al2 = map.get(new CollisionEvent());
	    Assert.assertEquals(al2.getList(), al1.getList());
	}
	
	@Test
	public void testHashSimilarityOfGameLooseEvent() {
	    
	    Event event = new GameLooseEvent();
	    ActionList al1 = event.getSupportedActions();
	    HashMap<Event, ActionList> map = new HashMap<Event, ActionList>();
	    map.put(event, al1);
	    ActionList al2 = map.get(new GameLooseEvent());
	    Assert.assertEquals(al2.getList(), al1.getList());
	}
	
	@Test
	public void testHashSimilarityOfKeyPressEvent() {
	    
	    Event event = new KeyPressEvent();
	    ActionList al1 = event.getSupportedActions();
	    HashMap<Event, ActionList> map = new HashMap<Event, ActionList>();
	    map.put(event, al1);
	    ActionList al2 = map.get(new KeyPressEvent());
	    Assert.assertEquals(al2.getList(), al1.getList());
	}
	
	@Test
	public void testHashSimilarityOfMoveEvent() {
	    
	    Event event = new MoveEvent();
	    ActionList al1 = event.getSupportedActions();
	    HashMap<Event, ActionList> map = new HashMap<Event, ActionList>();
	    map.put(event, al1);
	    ActionList al2 = map.get(new MoveEvent());
	    Assert.assertEquals(al2.getList(), al1.getList());
	}

}
