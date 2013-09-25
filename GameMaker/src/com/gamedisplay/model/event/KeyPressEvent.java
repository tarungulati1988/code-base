package com.gamedisplay.model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.action.MoveLeftAction;
import com.gamedisplay.model.action.MoveRightAction;

/**
 * Identifies keyboard related movement actions.
 * 
 * @author Sneha, Madhu
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class KeyPressEvent extends Event {
	String name = "KeyPressEvent";

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
		KeyPressEvent other = (KeyPressEvent) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public KeyPressEvent() {
		super();
	}

	@Override
	@JsonIgnore
	protected void addSupportedActions() {
		actions.add(new MoveLeftAction());
		actions.add(new MoveRightAction());
	}
}
