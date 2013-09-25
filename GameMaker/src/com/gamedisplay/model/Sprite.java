package com.gamedisplay.model;

/**
 * Sprite represents game object. It is heart of the game. Users can add any
 * object as a sprite, then associate them with events.
 * 
 * @author Amey, Madhu
 * 
 */
public class Sprite {
	private Coordinate currentCoordinate = null;
	private int dx = 0;
	private int dy = 0;
	private EventActionMap eventActionMap = null;
	private int height = 0;
	private String imageFileUrl = null;
	private Coordinate initCoordinate = null;
	private boolean isVisible = true;
	private String name = null;
	private int width = 0;

	public Sprite() {
		dx = Constants.dx;
		dy = Constants.dy;
	}

	public Coordinate getCurrentCoordinate() {
		return currentCoordinate;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
	public EventActionMap getEventActionMap() {
		return eventActionMap;
	}
	public int getHeight() {
		return height;
	}

	public String getImageFileUrl() {
		return imageFileUrl;
	}

	public Coordinate getInitCoordinate() {
		return initCoordinate;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setCurrentCoordinate(Coordinate currentCoordinate) {
		this.currentCoordinate = currentCoordinate;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public void setEventActionMap(EventActionMap eventActionMap) {
		this.eventActionMap = eventActionMap;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setImageFileUrl(String imageFileUrl) {
		this.imageFileUrl = imageFileUrl;
	}

	public void setInitCoordinate(Coordinate initCoordinate) {
		this.initCoordinate = initCoordinate;
		setCurrentCoordinate(new Coordinate(initCoordinate));
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "Sprite [name=" + name + ", imageFileUrl=" + imageFileUrl
				+ ", initCoordinate=" + initCoordinate + ", height=" + height
				+ ", width=" + width + ", currentCoordinate="
				+ currentCoordinate + "]";
	}

}
