package com.gamedisplay.model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.action.MoveAction;

/**
 * Identifies self movement actions
 * 
 * @author Madhu, Amey
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class MoveEvent extends Event {
	String name = "MoveEvent";

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
		MoveEvent other = (MoveEvent) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public MoveEvent() {
		super();
	}

	@Override
	@JsonIgnore
	protected void addSupportedActions() {
		actions.add(new MoveAction());
	}
}
