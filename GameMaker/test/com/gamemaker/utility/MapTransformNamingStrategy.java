package com.gamemaker.utility;

import java.util.Map;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy;

public class MapTransformNamingStrategy extends
		LowerCaseWithUnderscoresStrategy {

	private static final long serialVersionUID = 1L;

	private final Map<String, String> mapping;

	public MapTransformNamingStrategy(Map<String, String> mapping) {
		this.mapping = mapping;
	}

	@Override
	public String translate(String property) {
		if (mapping.containsKey(property)) {
			return mapping.get(property);
		}

		return property;
	}
}