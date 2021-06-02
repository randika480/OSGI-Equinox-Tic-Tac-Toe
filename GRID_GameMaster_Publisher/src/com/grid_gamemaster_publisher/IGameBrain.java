package com.grid_gamemaster_publisher;

public interface IGameBrain {
	
	public int setMarker(int cellLocation, String player);
	public void initiateGame(int gameType);

}
