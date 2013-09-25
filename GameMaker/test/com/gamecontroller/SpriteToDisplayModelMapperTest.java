package com.gamecontroller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gamedisplay.model.Coordinate;
import com.gamedisplay.model.Sprite;
import com.gamemaker.model.DisplayModel;

//Class SpriteToDisplayModelMapper is tested.

public class SpriteToDisplayModelMapperTest {

	@Test
	public void test() {

		String Name = "Ball";
		int Height = 10;
		int Width = 10;
		Sprite sprite = new Sprite();
		Coordinate cordinate = new Coordinate();
		cordinate.setxPosition(10);
		cordinate.setyPosition(10);

		sprite.setName(Name);
		sprite.setImageFileUrl("ball.jpg");
		sprite.setHeight(Height);
		sprite.setWidth(Width);
		sprite.setInitCoordinate(cordinate);
		
 		DisplayModel displaymodel = new DisplayModel();
		
		SpriteToDisplayModelMapper.map(displaymodel, sprite);
		
		assertEquals(displaymodel.getName(), sprite.getName());
		assertEquals(displaymodel.getImageUrl(), sprite.getImageFileUrl());
		assertEquals(displaymodel.getHeight(), sprite.getHeight());
		assertEquals(displaymodel.getWidth(), sprite.getWidth());
		assertEquals(displaymodel.getInitX(), sprite.getInitCoordinate().getxPosition());
		assertEquals(displaymodel.getInitY(), sprite.getInitCoordinate().getyPosition());
		
	}
}
