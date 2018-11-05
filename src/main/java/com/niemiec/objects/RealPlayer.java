package com.niemiec.objects;

//Klasa gracza

//sunkenShips - przechowuj� liczb� zatopionych statk�w (liczb�, jak� zatopi� RealPlayer, a nie jak� jemu zatopiono)
public class RealPlayer implements Player {
	private Board board;
	private Board opponentBoard;
	private int sunkenShips;
	private CollectionShips collectionShips;
	private String name;
	private boolean virtualPlayer;

	public RealPlayer(String name) {
		this.board = new Board();
		this.opponentBoard = new Board();
		this.collectionShips = new CollectionShips();
		this.name = name;
		this.virtualPlayer = false;
		this.sunkenShips = 0;
	}

	public String getName() {
		return this.name;
	}

	public boolean getInformationInThePlayerIsVirtual() {
		return virtualPlayer;
	}

	public Board getBoard() {
		return board;
	}

	public Board getOpponentBoard() {
		return opponentBoard;
	}

	public int getSunkenShips() {
		return sunkenShips;
	}

	public void setSunkenShips(int sunkenShips) {
		this.sunkenShips = sunkenShips;
	}

	public CollectionShips getCollectionShips() {
		return this.collectionShips;
	}

	public void increaseSunkenShips() {
		this.sunkenShips++;
	}

	public void addShipsAutomatically() {
		// TODO Auto-generated method stub
		
	}

}
