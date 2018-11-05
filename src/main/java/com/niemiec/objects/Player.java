package com.niemiec.objects;

public interface Player {
	public final static boolean VIRTUAL_PLAYER = true;
	public final static boolean REAL_PLAYER = false;

	public boolean getInformationInThePlayerIsVirtual();
	
	public Board getBoard();
	public Board getOpponentBoard();
	
	public int getSunkenShips();
	public CollectionShips getCollectionShips();
	public void increaseSunkenShips();
	public String getName();
	
	public void addShipsAutomatically();
}
