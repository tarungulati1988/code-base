package com.gamedisplay.model;

/**
 * Handy class to store both x and y together
 * 
 * @author Amey
 * 
 */
public class Coordinate {
	private int xPosition;
	private int yPosition;

	public Coordinate() {
		xPosition = 0;
		yPosition = 0;
	}

	@Override
	public String toString() {
		return "Coordinate [xPosition=" + xPosition + ", yPosition="
				+ yPosition + "]";
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xPosition;
		result = prime * result + yPosition;
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
		Coordinate other = (Coordinate) obj;
		if (xPosition != other.xPosition)
			return false;
		if (yPosition != other.yPosition)
			return false;
		return true;
	}

	public Coordinate(Coordinate coordinate) {
		this.xPosition = coordinate.getxPosition();
		this.yPosition = coordinate.getyPosition();
	}

	public Coordinate(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}
}
