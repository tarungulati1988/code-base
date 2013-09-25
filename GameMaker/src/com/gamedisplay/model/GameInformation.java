package com.gamedisplay.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * GameInfo stores all the information that is required by GamePlay Controller
 * to run the game. This is JSON friendly so we can stored this object by
 * converting it to json and then load by other controller.
 * 
 * @author Amey
 * 
 */
public class GameInformation {
	private String name;

	private List<Sprite> sprites;

	public GameInformation() {
		this.sprites = new ArrayList<Sprite>();
		this.setName(new String());
	}

	public GameInformation(String name, List<Sprite> sprites) {
		this.setName(name);
		this.sprites = sprites;
	}

	public GameInformation(String name) {
		this();
		this.name = name;
	}

	public void addNewSprite(Sprite sprite) {
		Sprite oldSprite = this.getSpriteByName(sprite.getName());
		if(oldSprite != null){
			this.sprites.remove(oldSprite);
		}
		this.sprites.add(sprite);
	}

	public String getName() {
		return name;
	}

	public Sprite getSpriteByName(String name) {
		for (Sprite sprite : sprites) {
			if(sprite.getName().equals(name))
				return sprite;
		}
		return null;
	}

	public List<Sprite> getSprites() {
		return sprites;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSprites(List<Sprite> sprites) {
		this.sprites = sprites;
	}

	@JsonIgnore
	public List<String> getAllSpriteNames() {
		List<String> spriteNames = new ArrayList<String>();
		for (Sprite sprite : sprites) {
			spriteNames.add(sprite.getName());
		}
		return spriteNames;
	}

	public void removeSprite(Sprite sprite) {
		boolean ret = this.sprites.remove(sprite);
	}

}
