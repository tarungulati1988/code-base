package com.gamemaker.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamedisplay.model.ActionList;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.EventActionMap;
import com.gamedisplay.model.event.KeyPressEvent;

public class EventDeserializationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws JsonGenerationException, JsonMappingException,
			IOException {
		
		Event event = new KeyPressEvent();
		
		EventActionMap eventActionMap = new EventActionMap();
		HashMap<Event, ActionList> map = new HashMap<Event, ActionList>();

		
		map.put(event, event.getSupportedActions());
		eventActionMap.setEventActionMap(map);

		ObjectMapper mapper = new ObjectMapper();
		OutputStream out = new ByteArrayOutputStream();

		mapper.writeValue(out, eventActionMap);
		String firstSearializedVersion = out.toString();

		mapper = new ObjectMapper();

		EventActionMap eventActionMap2 = mapper.readValue(out.toString(),
				EventActionMap.class);
		Assert.assertNotNull(eventActionMap2);

		mapper = new ObjectMapper();
		out = new ByteArrayOutputStream();

		mapper.writeValue(out, eventActionMap2);
		Assert.assertEquals(firstSearializedVersion, out.toString());
	}

	public String strconvert(Event event) throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		OutputStream out = new ByteArrayOutputStream();

		mapper.writeValue(out, event);
		return out.toString();
	}

	private Event convertBack(String json) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Event event = mapper.readValue(json, Event.class);
		return event;
	}

}
