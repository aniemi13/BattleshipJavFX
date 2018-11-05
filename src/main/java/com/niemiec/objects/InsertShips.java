package com.niemiec.objects;

//Zarz�dzanie dodawaniem statk�w
public class InsertShips {
	final int MANUALLY = 0;
	final int AUTOMATICALLY = 1;

	int[] quantityShips;

	public InsertShips() {
		this.quantityShips = new int[] { 4, 3, 2, 1 };
	}

	public void addShipsAutomatically(Board board, CollectionShips collectionShips) {
		addShips(board, collectionShips, AUTOMATICALLY);
	}
	
	public void addShipsManually(Board board, CollectionShips collectionShips) {
		addShips(board, collectionShips, MANUALLY);
	}

	// G��wna metoda zarz�dzaj�ca wprowadzaniem statk�w
	// pierwsza p�tla - ilo�� statk�w wg maszt�w - 4 rodzaje
	// druga p�tla - dla ka�dego rodzaju statki dodawane osobno
	public void addShips(Board board, CollectionShips collectionShips, int inputMethod) {
		for (int i = 0; i < quantityShips.length; i++) {
			for (int j = quantityShips[quantityShips.length - (i + 1)]; j > 0; j--) {
				if (inputMethod == MANUALLY) System.out.println("--------------------Dodajemy " + j + " " + quantityShips[i]
						+ " msztowc�w--------------------");
				addShip(board, collectionShips, quantityShips[i], inputMethod);
			}
		}
//		board.viewBoard();
	}

	// tablica, kolekcja statk�w, maksymalna ilo�� maszt�w
	private void addShip(Board board, CollectionShips collectionShips, int numberOfMasts, int inputMethod) {
		Ship ship = new Ship(numberOfMasts);

		for (int i = 0; i < numberOfMasts; i++) {
			checkData(board, ship, i, inputMethod);
			if (inputMethod == MANUALLY) board.viewBoard();
		}
		collectionShips.addShip(ship);
		// na ko�cu warto�ci 4 w obieckie Board zostaj� zamienione na 2
		board.check4To2();
		if (inputMethod == MANUALLY) board.viewBoard();
	}

	// sprawdzane s� dane wprowadzane przez gracza
	// uzupe�niane s� pola w obieckie Board
	// uzupe�niane s� dane statku
	private int[] checkData(Board board, Ship ship, int currentNumberOfMasts, int inputMethod) {
		GetBoxAndGameData getBoxData = new GetBoxAndGameData();
		int[] box;

		while (true) {
			if (inputMethod == MANUALLY)
				box = getBoxData.downloadDataFromRealPlayer();
			else 
				box = getBoxData.downloadDataAutomatically(ship, currentNumberOfMasts);
			
			if (!checkIfBoxIsEmpty(box, board)) {
				if (inputMethod == MANUALLY) System.out.println("-----!!!!!-----Zaj�te pole------!!!!!-----");
				continue;
			}
			if (!checkIfAroundOneIsEmpty(box, board)) {
				if (inputMethod == MANUALLY) System.out.println("-----!!!!!-----Nie mo�na stwia� statku obok innego statku------!!!!!-----");
				continue;
			}
			// Najprawdopodobniej ten warunek zniknie, bo zostnie zwarty w
			// ostatnim
			// LUB W TYM WARUNKU B�D� DODATKOWO WARUNKI DLA 3 MASZTOWC�W
			if (ship.getNumberOfMasts() != 1 && ship.getWay() == 0 && !checkIsThereAPlace(box, board, ship)) {
				if (inputMethod == MANUALLY) System.out.println("-----!!!!!-----Statek nie zmie�ci si� w tym miejscu------!!!!!-----");
				continue;
			}
			if (ship.getNumberOfMasts() != 1 && ship.getCurrentNumberOfMasts() != 0 && ship.getWay() != 0
					&& !checkIfTheNextIsTheGoodWay(box, board, ship)) {
				if (inputMethod == MANUALLY) System.out.println("-----!!!!!-----Nie mo�na w taki spos�b budowa� statku------!!!!!-----");
				continue;
			}
			break;
		}

		board.setBox(box[0], box[1], 4);
		ship.setMast(box[0], box[1], currentNumberOfMasts + 1);
		// ship. segregacja wsp�rz�dnych (od lewej do prawej, od g�ry do do�u)

		return null;
	}

	//Sprawdza drog� statku i wykonuje odpowiednie funkcje, dla odpowiedniej drogi (kierunku)
	private boolean checkIfTheNextIsTheGoodWay(int[] box, Board board, Ship ship) {
		if ((ship.getWay() == 1) && checkWayX(box, ship)) {
			return true;
		}
		if ((ship.getWay() == 2) && checkWayY(box, ship)) {
			return true;
		}
		if ((ship.getWay() == 3) && checkWayXY(box, ship)) {
			return true;
		}

		return false;
	}

	//Je�eli r�nica pomi�dzy kt�rym� z x w statku, a nowym wprowadzanym wynosi 1 lub -1, a warto�� y nie ulega zmianie
	//to przypisywana jest odpowiednia droga i zwracana jest wartos� true
	private boolean checkWayXY(int[] box, Ship ship) {
		for (int i = ship.getCurrentNumberOfMasts(); i > 0; i--) {
			int x = ship.getX(i);
			int y = ship.getY(i);
			if (((x - box[0] == 1) || (x - box[0] == -1)) && ((y - box[1]) == 0)) {
				ship.setWay(1);
				return true;
			}
			if (((y - box[1] == 1) || (y - box[1] == -1)) && ((x - box[0]) == 0)) {
				ship.setWay(2);
				return true;
			}
		}
		return false;
	}

	//Je�eli r�nica pomi�dzy kt�ym� z y w statku, a nowym wprowadzanym wynosi 1 lub -1, a warto�� x nie ulega zmianie
	// to zwracana jest warto�� true;
	private boolean checkWayY(int[] box, Ship ship) {
		for (int i = ship.getCurrentNumberOfMasts(); i > 0; i--) {
			int x = ship.getX(i);
			int y = ship.getY(i);
			if (((y - box[1] == 1) || (y - box[1] == -1)) && ((x - box[0]) == 0)) {
				return true;
			}
		}

		return false;
	}

	//to samo co dla Y, tylko tutaj dla X
	private boolean checkWayX(int[] box, Ship ship) {
		for (int i = ship.getCurrentNumberOfMasts(); i > 0; i--) {
			int x = ship.getX(i);
			int y = ship.getY(i);
			if (((x - box[0] == 1) || (x - box[0] == -1)) && ((y - box[1]) == 0)) {
				return true;
			}
		}

		return false;

	}
	
	//funkcja na potrzeby metody willTheShipFit w klasie Game
	public int checkIsThereAPlace(int[] box, int miminalNumberOfMasts, Board board) {
		int x = box[0];
		int y = box[1];

		int sum = 0;
		for (int i = 1; i <= miminalNumberOfMasts; i++) {
			for (int j = (-miminalNumberOfMasts) + i; j <= 0; j++) {
				if (checkTheBoxesNextX(x, y, j, board, miminalNumberOfMasts)) {
					sum += 1;
					break;
				}
			}
			if (sum != 0) {
				break;
			}
		}
		for (int i = 1; i <= miminalNumberOfMasts; i++) {

			for (int j = (-miminalNumberOfMasts) + i; j <= 0; j++) {
				if (checkTheBoxesNextY(x, y, j, board, miminalNumberOfMasts)) {
					sum += 2;
					break;
				}
			}
			if (sum == 3) {
				break;
			}
		}
		return sum;
	}

	//Sprawdza, czy jest miejsce na statek
	//Pobiera warto�� x,y, odejmuje od tego ilo�� maszt�w i sprawdza od tego punktu w prawo i w d�
	//czy statek si� zmie�ci
	private boolean checkIsThereAPlace(int[] box, Board board, Ship ship) {
		int x = box[0];
		int y = box[1];

		int sumWay = 0;
		for (int i = 1; i <= ship.getNumberOfMasts(); i++) {

			for (int j = (-ship.getNumberOfMasts()) + i; j <= 0; j++) {
				if (checkTheBoxesNextX(x, y, j, board, ship.getNumberOfMasts())) {
					sumWay += 1;
					break;
				}
			}
			if (sumWay != 0) {
				break;
			}
		}

		for (int i = 1; i <= ship.getNumberOfMasts(); i++) {

			for (int j = (-ship.getNumberOfMasts()) + i; j <= 0; j++) {
				if (checkTheBoxesNextY(x, y, j, board, ship.getNumberOfMasts())) {
					sumWay += 2;
					break;
				}
			}
			if (sumWay != 0) {
				break;
			}
		}

		switch (sumWay) {
		case 0:
			ship.setWay(0);
//			System.out.println("NIE ZMIE�CI SI�");
			return false;
		case 1:
			ship.setWay(1);
//			System.out.println("MIEJSCE WZD�ӯ X");
			return true;
		case 2:
			ship.setWay(2);
//			System.out.println("MIEJSCE WZD�ӯ Y");
			return true;
		case 3:
			ship.setWay(3);
//			System.out.println("MIEJSCE W OBIE STRONY");
			return true;
		default:
//			System.out.println("BRAK MIEJSCA? B��D?");
			return false;
		}
	}

	// Sprawdza pola czy s� wolne
	private boolean checkTheBoxesNextX(int x, int y, int j, Board board, int numberOfMasts) {
		int counter = 0;

		for (int k = 0; k < numberOfMasts; k++) {
			if (checkIfWithinThePlayingField(x, j + k) && checkIfAroundOneIsEmpty(new int[] { x + j + k, y }, board)
					&& board.getBox(x + j + k, y) == 0) {
				counter++;
			} else {
				return false;
			}
		}

		if (counter == numberOfMasts)
			return true;
		return false;
	}

	private boolean checkTheBoxesNextY(int x, int y, int j, Board board, int numberOfMasts) {
		int counter = 0;

		for (int k = 0; k < numberOfMasts; k++) {
			if (checkIfWithinThePlayingField(y, j + k) && checkIfAroundOneIsEmpty(new int[] { x, y + j + k }, board)
					&& board.getBox(x, y + j + k) == 0) {
				counter++;
			} else {
				return false;
			}
		}

		if (counter == numberOfMasts)
			return true;
		return false;
	}

	private boolean checkIfAroundOneIsEmpty(int[] box, Board board) {
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++) {
				if ((!(i == 0 && j == 0)) && checkIfWithinThePlayingField(box[0], i)
						&& checkIfWithinThePlayingField(box[1], j) && (board.getBox(box[0] + i, box[1] + j) == 2)) {
					return false;
				}
			}
		return true;
	}

	// Sprawdza, �eby dane mie�ci�y si� w obr�bie pola gry: 
	//a-wybrane pole, b-warto�� dodawana do a (patrzymy czy suma mie�ci si� w polu gry)
	private boolean checkIfWithinThePlayingField(int a, int b) {
		if (a + b <= 10 && a + b >= 1)
			return true;

		return false;
	}

	//Sprawdza czy pole jest puste
	private boolean checkIfBoxIsEmpty(int[] box, Board board) {
		if (board.getBox(box[0], box[1]) == 0)
			return true;
		return false;
	}
}
