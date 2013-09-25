package com.gamemaker.utility;

import java.awt.Rectangle;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.gamecontroller.GameState;
import com.gamedisplay.model.ActionList;
import com.gamedisplay.model.Constants;
import com.gamedisplay.model.Coordinate;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.EventActionMap;
import com.gamedisplay.model.GameInformation;
import com.gamedisplay.model.Sprite;
import com.gamedisplay.model.action.FallThrough;
import com.gamedisplay.model.action.MoveAction;
import com.gamedisplay.model.action.MoveLeftAction;
import com.gamedisplay.model.action.MoveRightAction;
import com.gamedisplay.model.action.ReflectAction;
import com.gamedisplay.model.action.VanishAction;
import com.gamedisplay.model.event.CollisionEvent;
import com.gamedisplay.model.event.GameLooseEvent;
import com.gamedisplay.model.event.KeyPressEvent;
import com.gamedisplay.model.event.MoveEvent;

public class JSONAdaptorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void GameInformationToJsontest() {

		GameInformation gameInformation = addDummyTestDS();

		String firstJsonRepresentation = JSONAdaptor.convert(gameInformation);
		Assert.assertNotNull(firstJsonRepresentation);

		GameInformation gameInformation2 = JSONAdaptor
				.convert(firstJsonRepresentation);
		Assert.assertNotNull(gameInformation2);

		String secondJsonRepresentation = JSONAdaptor.convert(gameInformation2);
		Assert.assertEquals(firstJsonRepresentation, secondJsonRepresentation);
	}

	private GameInformation addDummyTestDS() {
		GameInformation gi = new GameInformation("abs");
		// create ball
		Sprite ball = new Sprite();
		ball.setName("ball");
		ball.setInitCoordinate(new Coordinate(200, 300));
		ball.setImageFileUrl("./Images/ball.JPG");
		ball.setDx(Constants.dx);
		ball.setDy(-Constants.dy);
		EventActionMap balleventActionMap = new EventActionMap();
		HashMap<Event, ActionList> balleventActionMap1 = new HashMap<Event, ActionList>();

		// add self move
		ActionList ballValue = new ActionList();
		ballValue.add(new MoveAction());
		balleventActionMap1.put(new MoveEvent(), ballValue);
		// add collision event
		ballValue = new ActionList();
		ballValue.add(new ReflectAction(GameState.NONE));
		balleventActionMap1.put(new CollisionEvent(), ballValue);

		// add game loose event
		ballValue = new ActionList();
		ballValue.add(new FallThrough(GameState.GAME_LOSE, new Rectangle()));
		balleventActionMap1.put(new GameLooseEvent(new Rectangle()), ballValue);

		// add collision event
		ballValue = new ActionList();
		ballValue.add(new ReflectAction(GameState.NONE));
		balleventActionMap1.put(new CollisionEvent(), ballValue);

		balleventActionMap.setEventActionMap(balleventActionMap1);
		ball.setEventActionMap(balleventActionMap);
		ball.setWidth(100);
		ball.setHeight(100);

		gi.addNewSprite(ball);

		// create wall
		Sprite wall = new Sprite();
		wall.setName("wall");
		wall.setInitCoordinate(new Coordinate(400, 0));
		wall.setImageFileUrl("./Images/brick_tile_bw_vertical.png");
		EventActionMap walleventActionMap = new EventActionMap();
		HashMap<Event, ActionList> walleventActionMap1 = new HashMap<Event, ActionList>();
		walleventActionMap.setEventActionMap(walleventActionMap1);
		wall.setEventActionMap(walleventActionMap);
		wall.setWidth(100);
		wall.setHeight(100);
		gi.addNewSprite(wall);

		// create brick
		Sprite brick = new Sprite();
		brick.setName("brick");
		brick.setInitCoordinate(new Coordinate(240, 50));
		brick.setImageFileUrl("./Images/Brick.png");

		EventActionMap brickeventActionMap = new EventActionMap();
		HashMap<Event, ActionList> brickeventActionMap1 = new HashMap<Event, ActionList>();

		// add collision event
		ballValue = new ActionList();
		ballValue.add(new VanishAction(GameState.NONE));
		brickeventActionMap1.put(new CollisionEvent(), ballValue);

		brickeventActionMap.setEventActionMap(brickeventActionMap1);
		brick.setEventActionMap(brickeventActionMap);
		brick.setWidth(100);
		brick.setHeight(100);
		gi.addNewSprite(brick);

		// create another wall wall = new Sprite(); wall.setName("wall");
		wall.setInitCoordinate(new Coordinate(0, 0));
		wall.setImageFileUrl("./Images/brick_tile_bw_vertical.png");
		walleventActionMap = new EventActionMap();
		walleventActionMap1 = new HashMap<Event, ActionList>();
		walleventActionMap.setEventActionMap(walleventActionMap1);
		wall.setEventActionMap(walleventActionMap);
		wall.setWidth(100);
		wall.setHeight(100);
		gi.addNewSprite(wall);

		// create another wall
		wall = new Sprite();
		wall.setName("wall");
		wall.setInitCoordinate(new Coordinate(0, 0));
		wall.setImageFileUrl("./Images/brick_tile_bw_horizontal.png");
		walleventActionMap = new EventActionMap();
		walleventActionMap1 = new HashMap<Event, ActionList>();
		walleventActionMap.setEventActionMap(walleventActionMap1);
		wall.setEventActionMap(walleventActionMap);

		wall.setWidth(100);
		wall.setHeight(100);
		gi.addNewSprite(wall);

		// paddle
		wall = new Sprite();
		wall.setName("paddle");
		wall.setInitCoordinate(new Coordinate(200, 400));
		wall.setImageFileUrl("./Images/paddle1.jpg");
		walleventActionMap = new EventActionMap();
		walleventActionMap1 = new HashMap<Event, ActionList>();

		// add self move
		ballValue = new ActionList();
		ballValue.add(new MoveLeftAction());
		ballValue.add(new MoveRightAction());
		walleventActionMap1.put(new KeyPressEvent(), ballValue);

		walleventActionMap.setEventActionMap(walleventActionMap1);
		wall.setEventActionMap(walleventActionMap);
		wall.setWidth(100);
		wall.setHeight(100);
		gi.addNewSprite(wall);

		return gi;
	}
}
