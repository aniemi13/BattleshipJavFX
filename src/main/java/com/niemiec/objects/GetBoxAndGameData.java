package com.niemiec.objects;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GetBoxAndGameData {
	private Scanner in;
	private Random random;

	public GetBoxAndGameData() {
		this.in = new Scanner(System.in);
		this.random = new Random();
	}

	public int[] downloadDataFromRealPlayer() {
		int box[] = new int[] { 0, 0 };
		System.out.println("Wprowadz pole A-J");
		box[0] = getCharToInt();
		System.out.println("Wprowadz pole 1-10");
		box[1] = getInt();
//		if (MainScreenController.getMyXY()[0] != 0)
//			box = MainScreenController.getMyXY();
		System.out.println("x: " + box[0] + ", y: " + box[1]);
		return box;
	}

	// Pobiera z klawiatury warto�� int - walidacja danych
	private int getInt() {
		String s = null;

		s = getStringFromKeyboard();
		while (!Pattern.matches("[1-9]", s) && !Pattern.matches("10", s)) {
			System.out.println("Z�e dane, wprowadz liczb� od 1 do 10: " + s);
			s = getStringFromKeyboard();
		}

		return Integer.parseInt(s);
	}

	// Pobiera z klawiatury warto�� String - walidacja danych, od razu zamienia
	// na int
	private int getCharToInt() {
		String s = null;

		s = getStringFromKeyboard();
		while (!Pattern.matches("[a-jA-J]", s)) {
			System.out.println("Z�e dane, wprowadz liter� od a do j: " + s);
			s = getStringFromKeyboard();
		}
		return changeLetterToInteger(s);
	}

	// Zmiana liter na cyfry/liczby
	private int changeLetterToInteger(String s) {
		String ss = s.toLowerCase();
		ss.trim();
		String[] znaki = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };

		for (int i = 0; i < znaki.length; i++) {
			if (ss.equals(znaki[i])) {
				return (i + 1);
			}
		}
		System.out.println("Z�E DANE!");
		return 0;
	}

	// Pobiera tekst z klawiatury
	private String getStringFromKeyboard() {
		String s = null;

		try {
			s = in.nextLine();
			s.trim();
		} catch (Exception e) {

		}
		return s;
	}

	// 04.10.2018 00:25 - zabezpieczy� przez przyjmowaniem warto�ci wi�kszych
	// ni� rozmiar tablicy
	// je�eli w prawo za du�y to bra� w lewo i odwrotnie, to samo z g�r�
	public int[] downloadDataAutomatically(Ship ship, int currentNumberOfMasts) {

		int[] box = new int[] { 0, 0 };

		while (true) {
			switch (currentNumberOfMasts) {
				case 0: box = randomTheFirstMast(); break;
				case 1: box = randomTheSecondMast(ship); break;
				default: box = randomTheReamainingMasts(ship, currentNumberOfMasts); break;
			}

			if (checkIfItIsWithinThePlayingField(box))
				return box;
		}
	}
	
	public int[] randomTheFirstMast() {
		int box[] = new int[] { 0, 0 };
		// losujemy po ca�ej tablicy board
		box[0] = random.nextInt(10) + 1;
		box[1] = random.nextInt(10) + 1;
		return box;
	}

	private int[] randomTheSecondMast(Ship ship) {
		// pobieramy ostatnie wsp�rz�dne statku i losujemy w 4 punktach
		// obok
		// losuje najpierw kierunek -way- (0 - wzd�� y, 1 - wzd�� x)
		// potem losuje stron� -direction- (0 - prawo, d�, 1 - lewo, g�ra)
		int way = random.nextInt(2);
		int box[] = new int[] { 0, 0 };
		int x = ship.getX(1);
		int y = ship.getY(1);
	
		int direction = random.nextInt(2);
		if (way == 0) {
			box[0] = x;
	
			if (direction == 1 && (y - 1) > 0)
				box[1] = y - 1;
			else if (direction == 0 && (y + 1) <= 10)
				box[1] = y + 1;
		} else if (way == 1) {
			box[1] = y;
	
			if (direction == 1 && (x - 1) > 0)
				box[0] = x - 1;
			else if (direction == 0 && (x + 1) <= 10)
				box[0] = x + 1;
		}
		return box;
	}

	private int[] randomTheReamainingMasts(Ship ship, int currentNumberOfMasts) {
		int direction = random.nextInt(2);
		int box[] = new int[] { 0, 0 };
		int x = ship.getX(1);
		int y = ship.getY(1);
		
		if (ship.getWay() == 1) {
			box[1] = y;
			if (direction == 1 && (x - 1) > 0) {
				box[0] = x - 1;
			} else if (direction == 0 && (x + ship.getCurrentNumberOfMasts()) <= 10) {
				box[0] = x + ship.getCurrentNumberOfMasts();
			}
		} else if (ship.getWay() == 2) {
			box[0] = x;
	
			if (direction == 1 && (y - 1) > 0) {
				box[1] = y - 1;
			} else if (direction == 0 && (y + ship.getCurrentNumberOfMasts()) <= 10) {
				box[1] = y + ship.getCurrentNumberOfMasts();
			}
		}
		return box;
	}

	private boolean checkIfItIsWithinThePlayingField(int[] box) {
		if (box[0] > 0 && box[0] <= 10 && box[1] > 0 && box[1] <= 10)
			return true;
		return false;
	}
	
	public String downloadPlayerName() {
		String s = getStringFromKeyboard();
		while (!Pattern.matches("[a-zA-Z0-9]{3,15}", s)) {
			System.out.println("Wprowadz od 3 do 15 znak�w: " + s);
			s = getStringFromKeyboard();
		}
		return s;
	}

	public int get1Or2() {
		String s = null;

		s = getStringFromKeyboard();
		while (!Pattern.matches("[1-2]", s)) {
			System.out.println("Z�e dane, wprowadz liczb� od 1 do 2: " + s);
			s = getStringFromKeyboard();
		}

		return Integer.parseInt(s);
	}

}
