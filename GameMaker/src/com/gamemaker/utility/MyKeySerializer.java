package com.gamemaker.utility;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gamedisplay.model.Event;

/**
 * Helper class for JACKSON to map ActionEvent objects
 * 
 * @author Amey
 * 
 */
public class MyKeySerializer extends JsonSerializer<Event>
{
	static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void serialize(Event value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		String json = mapper.writeValueAsString(value);
		jgen.writeFieldName(json);
	}
}