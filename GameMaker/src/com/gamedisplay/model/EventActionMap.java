package com.gamedisplay.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gamemaker.utility.MyKeyDeSerializer;
import com.gamemaker.utility.MyKeySerializer;

/**
 * Handy class to store and load the map
 * 
 * @author Amey
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class EventActionMap {
	@JsonProperty
	@JsonSerialize(keyUsing = MyKeySerializer.class)
	@JsonDeserialize(keyUsing = MyKeyDeSerializer.class)
	private HashMap<Event, ActionList> eventActionMap;

	public EventActionMap() {
		eventActionMap = new HashMap<Event, ActionList>();
	}

	public Map<Event, ActionList> getEventActionMap() {
		return eventActionMap;
	}

	public void setEventActionMap(HashMap<Event, ActionList> eventActionMap) {
		this.eventActionMap = eventActionMap;
	}
}
