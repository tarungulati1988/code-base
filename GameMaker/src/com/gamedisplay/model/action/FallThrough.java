package com.gamedisplay.model.action;

import java.awt.Rectangle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamecontroller.GameState;
import com.gamedisplay.model.Action;
import com.gamedisplay.model.Sprite;

/**
 * Checks whether given object has gone out of the game. Returns the game state
 * condition (defined during the init), to the caller
 * 
 * @author Tarun
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class FallThrough implements Action {
	String name = "FallThrough";
	@JsonProperty
	private final String gameStateObj;
	private Rectangle rectangle;
	
	public FallThrough() {
		this.gameStateObj = GameState.NONE;
	}

	public FallThrough(String gameStateObj, Rectangle rectangle) {
		this.gameStateObj = gameStateObj;
		this.rectangle = rectangle;
	}
	
	@Override
	public String performAction(Sprite sprite) {
		int x = sprite.getCurrentCoordinate().getxPosition();
		int y = sprite.getCurrentCoordinate().getyPosition();
		double gameX = this.rectangle.getWidth();
		double gameY = this.rectangle.getHeight();
		if(x<0 || x> gameX){
			return gameStateObj;
		}
		if(y<0 || y> gameY){
			return gameStateObj;
		}
		return GameState.NONE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FallThrough other = (FallThrough) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
