package com.niemiec.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ship {
	private List<Integer> xx;
	private List<Integer> yy;

	// private int x[];
	// private int y[];
	private int numberOfMasts; // maksymalna ilo�� maszt�w
	private int currentNumberOfMasts; // aktualna ilo�� zbudowanych maszt�w
	private int currentNumberOfHitMasts; // aktualna ilo�� trafionych maszt�w
	private int way; // 0 - brak, 1 - wzd�� x, 2 - wzd�� y, 3 - w obie strony
	private boolean sunk; // true, gdy zatopiony

	public Ship(int numberOfMasts) {
		this.xx = new ArrayList<Integer>();
		this.yy = new ArrayList<Integer>();

		// this.x = new int[numberOfMasts];
		// this.y = new int[numberOfMasts];
		this.currentNumberOfMasts = 0;
		this.currentNumberOfHitMasts = 0;
		this.numberOfMasts = numberOfMasts;
		this.way = 0;
		this.sunk = false;
	}

	public int getX(int numberOfMasts) {
		// return x[numberOfMasts - 1];
		return xx.get(numberOfMasts - 1);
	}

	public int getY(int numberOfMasts) {
		// return y[numberOfMasts - 1];
		return yy.get(numberOfMasts - 1);
	}

	public void setMast(int x, int y, int currentNumberOfMasts) {
		this.xx.add(x);
		this.yy.add(y);

		// this.x[currentNumberOfMasts - 1] = x;
		// this.y[currentNumberOfMasts - 1] = y;
		this.currentNumberOfMasts++;

		if (this.currentNumberOfMasts > 1)
			sortMasts();
	}

	public boolean getSunk() {
		return this.sunk;
	}

	public void setSunkTrue() {
		this.sunk = true;
	}

	public int getNumberOfMasts() {
		return this.numberOfMasts;
	}

	public int getWay() {
		return this.way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public int getCurrentNumberOfMasts() {
		return currentNumberOfMasts;
	}

	public void setCurrentNumberOfMasts(int currentNumberOfMasts) {
		this.currentNumberOfMasts = currentNumberOfMasts;
	}

	public void sortMasts() {
		if ((xx.get(1) - xx.get(0)) == 0)
			Collections.sort(yy);
		else if ((yy.get(1) - yy.get(0)) == 0)
			Collections.sort(xx);
	}

	public int getCurrentNumberOfHitMasts() {
		return currentNumberOfHitMasts;
	}

	public void setCurrentNumberOfHitMasts(int currentNumberOfHitMasts) {
		this.currentNumberOfHitMasts = currentNumberOfHitMasts;
	}
	
	public void increaseCurrentNumberOfHitMasts() {
		this.currentNumberOfHitMasts++;
	}
}
