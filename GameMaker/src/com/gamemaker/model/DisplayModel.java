package com.gamemaker.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * This is plain old class containing just strings and ints. This helps use to
 * use platform independent information object to transfer data between view and
 * controller. This is important because later view can be detached all together
 * from the controller.
 * 
 * @author Amey, Madhu, Tarun, Sneha
 * 
 */
public class DisplayModel {
	private String name;
	private String imageUrl;
	private int initX, initY;
	private int height, width;
	private Map<String, ArrayList<String>> eventActionMap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getInitX() {
		return initX;
	}

	public void setInitX(int initX) {
		this.initX = initX;
	}

	public int getInitY() {
		return initY;
	}

	public void setInitY(int initY) {
		this.initY = initY;
	}

	public Map<String, ArrayList<String>> getEventActionMap() {
		return eventActionMap;
	}

	public void setEventActionMap(Map<String, ArrayList<String>> eventActionMap) {
		this.eventActionMap = eventActionMap;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
