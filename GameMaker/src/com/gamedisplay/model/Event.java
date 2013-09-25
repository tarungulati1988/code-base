package com.gamedisplay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamedisplay.model.event.CollisionEvent;
import com.gamedisplay.model.event.GameLooseEvent;
import com.gamedisplay.model.event.KeyPressEvent;
import com.gamedisplay.model.event.MoveEvent;

/**
 * Minimal contract for any type of event. This event based code makes all over
 * events/triggers generic. Sprites can register to events upto supported action
 * level.
 * 
 * @author Amey, Madhu
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
@JsonSubTypes({ @JsonSubTypes.Type(value = KeyPressEvent.class),
		@JsonSubTypes.Type(value = CollisionEvent.class),
		@JsonSubTypes.Type(value = MoveEvent.class),
		@JsonSubTypes.Type(value = GameLooseEvent.class),})
public abstract class Event {
	@JsonProperty
	protected final ActionList actions;

	public Event() {
		actions = new ActionList();
		addSupportedActions();
	}

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);
	
	@JsonIgnore
	protected abstract void addSupportedActions();

	@JsonIgnore
	public ActionList getSupportedActions() {
		return actions;
	}
}
