package com.blackout;

public class CurrentCell {
	private String mazeGuid;
	private String note;
	private boolean atEnd;
	private boolean previouslyVisited;
	private String north;
	private String east;
	private String south;
	private String west;
	private int x;
	private int y;

	public String getMazeGuid() {
		return mazeGuid;
	}

	public void setMazeGuid(String mazeGuid) {
		this.mazeGuid = mazeGuid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isAtEnd() {
		return atEnd;
	}

	public void setAtEnd(boolean atEnd) {
		this.atEnd = atEnd;
	}

	public boolean isPreviouslyVisited() {
		return previouslyVisited;
	}

	public void setPreviouslyVisited(boolean previouslyVisited) {
		this.previouslyVisited = previouslyVisited;
	}

	public String getNorth() {
		return north;
	}

	public void setNorth(String north) {
		this.north = north;
	}

	public String getEast() {
		return east;
	}

	public void setEast(String east) {
		this.east = east;
	}

	public String getSouth() {
		return south;
	}

	public void setSouth(String south) {
		this.south = south;
	}

	public String getWest() {
		return west;
	}

	public void setWest(String west) {
		this.west = west;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "CurrentCell [mazeGuid=" + mazeGuid + ", note=" + note
				+ ", atEnd=" + atEnd + ", previouslyVisited="
				+ previouslyVisited + ", N:" + north + ", E=" + east
				+ ", S=" + south + ", W=" + west + ", x=" + x + ", y="
				+ y + "]";
	}
}
