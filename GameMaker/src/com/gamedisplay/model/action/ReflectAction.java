package com.gamedisplay.model.action;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamecontroller.GameState;
import com.gamedisplay.model.Action;
import com.gamedisplay.model.Constants;
import com.gamedisplay.model.Sprite;

/**
 * This changes the direction depending upon curent direction of the object.
 * Please refer to the state table decision box given below.
 * 
 * @author Madhu, Amey
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class ReflectAction implements Action {
	String name = "ReflectAction";
	private final String gameStateObj;
	
	public ReflectAction() {
		this.gameStateObj = GameState.NONE;
	}

	public ReflectAction(String gameStateObj) {
		this.gameStateObj = gameStateObj;
	}
	
	@Override
	public String performAction(Sprite sprite) {
		int currentDx = sprite.getDx();
		int currentDy = sprite.getDy();

		// To determine new values of Dx and Dy, we have analyzed the directions
		// based on current values of dx, dy. Following chart gives new
		// direction map
		// --------------------------------------
		// Old Direction | New Direction
		// +x,-y (right wall)| -x,-y (towards up)
		// -x,-y (top wall) | -x,+y (towards left)
		// -x,+y (left wall) | +x,+y (bottom)
		// +x, +y(bottom) | +x, -y (right wall)
		// --------------------------------------
		if (currentDx > 0 && currentDy < 0) {
			currentDx = -Constants.dx;
		} else if (currentDx < 0 && currentDy < 0) {
			currentDx = -Constants.dx;
			currentDy = Constants.dy;
		} else if (currentDx < 0 && currentDy > 0) {
			currentDx = Constants.dx;
			currentDy = Constants.dy;
		} else if (currentDx > 0 && currentDy > 0) {
			currentDx = Constants.dx;
			currentDy = -Constants.dy;
		}

		sprite.setDx(currentDx);
		sprite.setDy(currentDy);
		return gameStateObj;
		
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
		ReflectAction other = (ReflectAction) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
