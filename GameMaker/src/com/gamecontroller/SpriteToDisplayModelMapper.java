package com.gamecontroller;

import com.gamedisplay.model.Sprite;
import com.gamemaker.model.DisplayModel;

/**
 * Helper utility to convert Sprite to DisplayModel.
 * 
 * In our MVC, controllers and views only talks via simple pojo objects.
 * DisplayModel is a pojo style object. This helper utility can quickly map
 * sprite to display model
 * 
 * @author Amey, Sneha, Tarun
 * 
 */
public class SpriteToDisplayModelMapper {

	public static void map(DisplayModel displayModel, Sprite sprite) {
		displayModel.setName(sprite.getName());
		displayModel.setImageUrl(sprite.getImageFileUrl());
		displayModel.setInitX(sprite.getInitCoordinate().getxPosition());
		displayModel.setInitY(sprite.getInitCoordinate().getyPosition());
		displayModel.setHeight(sprite.getHeight());
		displayModel.setWidth(sprite.getWidth());

		// TO-DO sprite to display mapper for event action map
		// Not needed for now
	}

}
