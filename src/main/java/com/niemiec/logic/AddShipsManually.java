package com.niemiec.logic;

import com.niemiec.objects.Player;
import com.niemiec.objects.Ship;

public class AddShipsManually {
	int[] quantityShips;
	Player player;
	int currentQuantityShipsOfGivenType;
	int currentShip;
	int currentMast;
	Ship ship;

	public AddShipsManually(Player player) {
		this.quantityShips = new int[] { 4, 3, 2, 1 };
		setTheInitialConditions();
		this.player = player;
	}

	// jeżeli wszystkie statki ustawione zwraca true i tablica zostaje zablokowana
	// a odblokowana zostaje tablica przeciwnika
	public boolean addShips(int[] xyFromButton, int inputMethod) {
		if (addShip(xyFromButton, inputMethod)) {
			currentShip--;
			if (currentShip == 0) {
				currentQuantityShipsOfGivenType++;
				if (currentQuantityShipsOfGivenType == quantityShips.length && currentShip == 0)
					return true;
				updateCurrentShip();
			}
			if (currentMast == 0) {
				ship = new Ship(quantityShips[currentQuantityShipsOfGivenType]);
			}
		}
		return false;
	}

	// dodaje statek do kolekcji
	// zwraca true, jeżeli statek dodany
	private boolean addShip(int[] xyFromButton, int inputMethod) {

		if (CheckData.checkData(xyFromButton, player, ship, currentMast, inputMethod)) {
			currentMast++;
		}

		if (currentMast == quantityShips[currentQuantityShipsOfGivenType]) {
			player.getCollectionShips().addShip(ship);
			player.getBoard().check4To2();		
			currentMast = 0;
			
			return true;
		} else {
			return false;
		}
	}

	private void updateCurrentShip() {
		currentShip = quantityShips[quantityShips.length - (currentQuantityShipsOfGivenType + 1)];
	}
	
	public void setPlayer(Player player) {
		this.player = player;
		setTheInitialConditions();
	}
	
	private void setTheInitialConditions() {
		this.quantityShips = new int[] { 4, 3, 2, 1 };
		this.currentQuantityShipsOfGivenType = 0;
		updateCurrentShip();
		this.ship = new Ship(quantityShips[currentQuantityShipsOfGivenType]);
		this.currentMast = 0;
	}
}
