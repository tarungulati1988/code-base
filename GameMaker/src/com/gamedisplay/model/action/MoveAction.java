package com.gamedisplay.model.action;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamecontroller.GameState;
import com.gamedisplay.model.Action;
import com.gamedisplay.model.Coordinate;
import com.gamedisplay.model.Sprite;

/**
 * Defines the forward movement action of the sprite.
 * 
 * @author Amey, Madhu
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class MoveAction implements Action {
	String name = "MoveAction";
	
	@Override
	public String performAction(Sprite sprite) {
		Coordinate currentCoordinate = sprite.getCurrentCoordinate();
		int x = currentCoordinate.getxPosition() + sprite.getDx();
		int y = currentCoordinate.getyPosition() + sprite.getDy();
		currentCoordinate.setxPosition(x);
		currentCoordinate.setyPosition(y);
		sprite.setCurrentCoordinate(currentCoordinate);
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
		MoveAction other = (MoveAction) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
