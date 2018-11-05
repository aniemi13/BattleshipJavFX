package com.niemiec.objects;

//W tej klasie zamieniamy dane wprowadzane na dane tablicowe
//W grze pole 1,1 to w tablicy 0,0
//Board - tablica
public class Board {
	// przechowuje stan pól gry
	// 0 - pusto
	// 1 - pud³o
	// 2 - statek
	// 3 - trafiony
	// 4 - wprowadzany - po wprowadzeniu zamieniany na 2
	// box - pude³ko
	int[][] boxes;

	public Board() {
		this.boxes = new int[10][10];
		resetArray(boxes);
	}

	public int getBox(int x, int y) {
		return this.boxes[y - 1][x - 1];

	}
	
	public int getBox(int[] box) {
		return this.boxes[box[1] - 1][box[0] - 1];
	}

	// ustawia wartoœæ danej komórki
	public void setBox(int x, int y, int stateOfBox) {
		this.boxes[y - 1][x - 1] = stateOfBox;
	}

	// Ustawia w tablicy same zera
	private void resetArray(int[][] a) {
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[i].length; j++)
				a[i][j] = 0;
	}

	public void check4To2() {
		for (int i = 0; i < boxes.length; i++)
			for (int j = 0; j < boxes[i].length; j++)
				if (boxes[i][j] == 4)
					boxes[i][j] = 2;
	}

	// i jako X
	// j jako Y
	public void viewBoard() {
		char a = 'A';
		System.out.print("    ");
		for (int i = 0; i < 10; i++)
			System.out.print(a++ + "  ");
		System.out.println("");
		for (int i = 0; i < 10; i++) {
			if (i < 9)
				System.out.print((i + 1) + "  ");
			else
				System.out.print((i + 1) + " ");

			for (int j = 0; j < 10; j++) {
				System.out.print(" " + boxes[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public void changeNearbyToFive(int[] box) {
		
	}
}
