package com.gamedisplay.model.event;

import java.awt.Rectangle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamecontroller.GameState;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.action.FallThrough;

/**
 * This basically checks whether any of the supported actions have happend. If
 * yes, then action should return GameState.Loose
 * 
 * @author Amey
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class GameLooseEvent extends Event {
	String name = "GameLooseEvent";
	Rectangle rect;
	
	public GameLooseEvent() {
		super();
	}

	public GameLooseEvent(Rectangle rect) {
		super();
		this.rect = rect;
		addSupportedActions();
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
		GameLooseEvent other = (GameLooseEvent) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	@JsonIgnore
	protected void addSupportedActions() {
		actions.removeAll();
		actions.add(new FallThrough(GameState.GAME_LOSE, this.rect));
	}
}
