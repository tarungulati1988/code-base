package com.gamecontroller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.gamedisplay.model.Constants;
import com.gamedisplay.model.Sprite;
import com.gamemaker.model.DisplayModel;

//Class DisplayModelToSpriteMapper is tested. 

public class DisplayModelToSpriteMapperTest {

	@Test
	public void test() {
		String Name = "Ball";
		int Height = 10;
		int Width = 10;
		Map<String, ArrayList<String>> eventActionMap = new HashMap<String, ArrayList<String>>();
		
		DisplayModel displaymodel = new DisplayModel();
		displaymodel.setName(Name);
		displaymodel.setImageUrl("ball.jpg");
		displaymodel.setHeight(Height);
		displaymodel.setWidth(Width);
		displaymodel.setEventActionMap(eventActionMap);
		
		Sprite sprite = new Sprite();
		
		DisplayModelToSpriteMapper.map(displaymodel, sprite);
		
		assertEquals(sprite.getName(), displaymodel.getName());
		assertEquals(sprite.getImageFileUrl(), Constants.STORED_IMAGE_LOCATION_PREFIX + displaymodel.getImageUrl());
		assertEquals(sprite.getHeight(), displaymodel.getHeight());
		assertEquals(sprite.getWidth(), displaymodel.getWidth());
		assertEquals(sprite.getInitCoordinate().getxPosition(), displaymodel.getInitX());
		assertEquals(sprite.getInitCoordinate().getyPosition(), displaymodel.getInitY());

	}
}
