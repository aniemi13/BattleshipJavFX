package com.niemiec.logic;

import com.niemiec.objects.Board;
import com.niemiec.objects.GetBoxAndGameData;
import com.niemiec.objects.Player;
import com.niemiec.objects.Ship;

public class CheckData {
	public static final int MANUALLY = 0;
	public static final int AUTOMATICALLY = 1;
	
	public static final boolean REAL_PLAYER = true;
	public static final boolean VIRTUAL_PLAYER = false;
	
	private static GetBoxAndGameData getBoxAndGameData = new GetBoxAndGameData();

	public static boolean checkData(int[] xyFromButton, Player player, Ship ship, int currentNumberOfMasts,
			int inputMethod) {
		
		
		while (true) {

			if (inputMethod == AUTOMATICALLY)
				xyFromButton = getBoxAndGameData.downloadDataAutomatically(ship, currentNumberOfMasts);
			
			if (!checkIfBoxIsEmpty(xyFromButton, player.getBoard())) {
				if (inputMethod == MANUALLY)
					return false;
				else continue;
			}

			if (!checkIfAroundOneIsEmpty(xyFromButton, player.getBoard())) {
				if (inputMethod == MANUALLY)
					return false;
				else continue;
			}

			if (ship.getNumberOfMasts() != 1 && ship.getWay() == 0
					&& !checkIsThereAPlace(xyFromButton, player.getBoard(), ship)) {
				if (inputMethod == MANUALLY)
					return false;
				else continue;
			}

			if (ship.getNumberOfMasts() != 1 && ship.getCurrentNumberOfMasts() != 0 && ship.getWay() != 0
					&& !checkIfTheNextIsTheGoodWay(xyFromButton, player.getBoard(), ship)) {
				if (inputMethod == MANUALLY)
					return false;
				else continue;
			}

			break;
		}

		player.getBoard().setBox(xyFromButton[0], xyFromButton[1], 4);
		ship.setMast(xyFromButton[0], xyFromButton[1], currentNumberOfMasts + 1);
		return true;
	}

	// Sprawdza czy pole jest puste
	private static boolean checkIfBoxIsEmpty(int[] box, Board board) {
		if (board.getBox(box[0], box[1]) == 0)
			return true;
		return false;
	}

	private static boolean checkIfAroundOneIsEmpty(int[] box, Board board) {
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
	// a-wybrane pole, b-warto�� dodawana do a (patrzymy czy suma mie�ci si� w polu
	// gry)
	private static boolean checkIfWithinThePlayingField(int a, int b) {
		if (a + b <= 10 && a + b >= 1)
			return true;

		return false;
	}

	// Sprawdza, czy jest miejsce na statek
	// Pobiera warto�� x,y, odejmuje od tego ilo�� maszt�w i sprawdza od tego punktu
	// w prawo i w d�
	// czy statek si� zmie�ci
	private static boolean checkIsThereAPlace(int[] box, Board board, Ship ship) {
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
//				System.out.println("NIE ZMIE�CI SI�");
			return false;
		case 1:
			ship.setWay(1);
//				System.out.println("MIEJSCE WZD�ӯ X");
			return true;
		case 2:
			ship.setWay(2);
//				System.out.println("MIEJSCE WZD�ӯ Y");
			return true;
		case 3:
			ship.setWay(3);
//				System.out.println("MIEJSCE W OBIE STRONY");
			return true;
		default:
//				System.out.println("BRAK MIEJSCA? B��D?");
			return false;
		}
	}

	// Sprawdza pola czy s� wolne
	private static boolean checkTheBoxesNextX(int x, int y, int j, Board board, int numberOfMasts) {
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

	private static boolean checkTheBoxesNextY(int x, int y, int j, Board board, int numberOfMasts) {
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

	// Sprawdza drog� statku i wykonuje odpowiednie funkcje, dla odpowiedniej drogi
	// (kierunku)
	private static boolean checkIfTheNextIsTheGoodWay(int[] box, Board board, Ship ship) {
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

	// Je�eli r�nica pomi�dzy kt�rym� z x w statku, a nowym wprowadzanym wynosi 1
	// lub -1, a warto�� y nie ulega zmianie
	// to przypisywana jest odpowiednia droga i zwracana jest wartos� true
	private static boolean checkWayXY(int[] box, Ship ship) {
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

	// Je�eli r�nica pomi�dzy kt�ym� z y w statku, a nowym wprowadzanym wynosi 1 lub
	// -1, a warto�� x nie ulega zmianie
	// to zwracana jest warto�� true;
	private static boolean checkWayY(int[] box, Ship ship) {
		for (int i = ship.getCurrentNumberOfMasts(); i > 0; i--) {
			int x = ship.getX(i);
			int y = ship.getY(i);
			if (((y - box[1] == 1) || (y - box[1] == -1)) && ((x - box[0]) == 0)) {
				return true;
			}
		}

		return false;
	}

	// to samo co dla Y, tylko tutaj dla X
	private static boolean checkWayX(int[] box, Ship ship) {
		for (int i = ship.getCurrentNumberOfMasts(); i > 0; i--) {
			int x = ship.getX(i);
			int y = ship.getY(i);
			if (((x - box[0] == 1) || (x - box[0] == -1)) && ((y - box[1]) == 0)) {
				return true;
			}
		}

		return false;

	}
}
