package com.niemiec.objects;

//Zarz�dzanie tablic� statk�w
public class CollectionShips {
	Ship[] ships;
	int currentlyNumberOfShips;
	//w tej tablicy przechwywane b�d� indeksy statk�w - numer w tablicy ships
	//z tablicy boardWithShipsIndex pobieramy indeks i od razu dokonujemy zmiany w konkretnym statku
	//bo znamy jego numer w tablicy
	Board boardWithShipsIndex;

	public CollectionShips() {
		this.ships = new Ship[10];
		this.currentlyNumberOfShips = 0;
		this.boardWithShipsIndex = new Board();
	}
	
	public void addShip(Ship ship) {
		ships[currentlyNumberOfShips] = ship;
		currentlyNumberOfShips++;
		for (int i = 1; i <= ship.getNumberOfMasts(); i++) {
			boardWithShipsIndex.setBox(ship.getX(i), ship.getY(i), currentlyNumberOfShips);
		}
//		System.out.println("FUNKCJA COLLECTIONSHIPS, STATEK DODANO! currentlyNumberOfShips: " + currentlyNumberOfShips);
	}
	
	public void viewStatistic() {
		for (int i = 0; i < 10; i++) {
			Ship s = ships[i];
			System.out.print("Statek " + s.getNumberOfMasts() + ", posiada wsp�rz�dne: ");
			for (int j = 1; j <= s.getNumberOfMasts(); j++) {
				System.out.print("x" + j + ": " + s.getX(j) + ", y" + j + ": " + s.getY(j) +"; ");
			}
			System.out.println("");	
		}
		boardWithShipsIndex.viewBoard();
	}

	public boolean checkShip(int[] box) {
		int index = boardWithShipsIndex.getBox(box[0], box[1]);
		Ship ship = ships[index - 1];
		ship.increaseCurrentNumberOfHitMasts();
		if (ship.getCurrentNumberOfHitMasts() == ship.getNumberOfMasts()) {
			ship.setSunkTrue();
			return true;
		}
		return false;
	}

	public Ship getShip(int[] box) {
		int index = boardWithShipsIndex.getBox(box[0], box[1]);
		return ships[index - 1];
	}
	
	public Ship getShip(int index) {
		return ships[index];
	}
}
