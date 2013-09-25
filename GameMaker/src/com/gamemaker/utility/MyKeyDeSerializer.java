package com.gamemaker.utility;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamedisplay.model.Event;

/**
 * Helper class for JACKSON to map ActionEvent objects
 * 
 * @author Amey
 * 
 */
public class MyKeyDeSerializer extends KeyDeserializer
{
	static ObjectMapper mapper = new ObjectMapper();

	@Override
	public Object deserializeKey(String key, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return mapper.readValue(key, Event.class);
	}
}
