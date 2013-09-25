package com.gamemaker.utility;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gamedisplay.model.GameInformation;

/**
 * This Adaptor is to perform the necessary conversions before inserting or
 * after reading the JSON objects
 * 
 */
public class JSONAdaptor {

	/*
	 * This function is used to convert the list of commands into string
	 * representation before writing it as JSON array.
	 */
	public static String convert(GameInformation gameInformation) {

		try {
		ObjectMapper mapper = new ObjectMapper();
		OutputStream out = new ByteArrayOutputStream();

		mapper.writeValue(out, gameInformation);
			return out.toString();
		} catch (Exception e) {

		}
		return null;
	}

	/*
	 * This function is used to convert the string into LinkedList
	 */
	public static GameInformation convert(String commandString) {

		try{
			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			GameInformation gameInformation = mapper.readValue(commandString, GameInformation.class);
			return gameInformation;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
