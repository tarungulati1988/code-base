package com.gamedisplay.model.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamecontroller.GameState;
import com.gamedisplay.model.Action;
import com.gamedisplay.model.Sprite;

/**
 * Sets the visibility to false.
 * 
 * @author Tarun, Amey
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class VanishAction implements Action {
	String name = "VanishAction";
	@JsonProperty
	private final String gameStateObj;

	public VanishAction() {
		this.gameStateObj = GameState.NONE;
	}

	public VanishAction(String gameStateObj) {
		this.gameStateObj = gameStateObj;
	}

	@Override
	public String performAction(Sprite sprite) {
		sprite.setVisible(false);
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
		VanishAction other = (VanishAction) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
