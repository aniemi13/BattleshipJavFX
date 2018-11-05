package com.niemiec.objects;

public class VirtualPlayer implements Player {
	private Board board;
	private Board opponentBoard;
	private int sunkenShips;
	private CollectionShips collectionShips;
	private String name;
	private boolean virtualPlayer;
	private boolean onHit;
	private int[] boxOnHit;
	//przechowuje kieruenk podczas zbijania statku
	//0 - nie zweryfikowane, 1 - wzd�� X, 2 - wzd�� Y, 3 - oba kierunki
	private int wayOnHit;

	public VirtualPlayer(String name) {
		this.board = new Board();
		this.opponentBoard = new Board();
		this.collectionShips = new CollectionShips();
		this.name = name;
		this.virtualPlayer = true;
		this.sunkenShips = 0;
		this.onHit = false;
		this.boxOnHit = new int[] { 0, 0 };
		this.wayOnHit = 0;
	}

	public void addShipsAutomatically() {
		InsertShips insertShips = new InsertShips();
		insertShips.addShipsAutomatically(board, collectionShips);
//		collectionShips.viewStatistic();
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

	public void addShipsManually() {
		addShipsAutomatically();
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

	public boolean isOnHit() {
		return onHit;
	}

	public void setOnHit(boolean onHit) {
		this.onHit = onHit;
	}

	public int[] getBoxOnHit() {
		return boxOnHit;
	}

	public void setBoxOnHit(int x, int y) {
		this.boxOnHit[0] = x;
		this.boxOnHit[1] = y;
	}

	public void setBoxOnHit(int[] box) {
		this.boxOnHit = box;
	}

	public int getWayOnHit() {
		return wayOnHit;
	}

	public void setWayOnHit(int wayOnHit) {
		this.wayOnHit = wayOnHit;
	}

}
