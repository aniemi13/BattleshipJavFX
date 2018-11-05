package com.niemiec.logic;

import java.util.Random;

import com.niemiec.objects.GetBoxAndGameData;
import com.niemiec.objects.InsertShips;
import com.niemiec.objects.Player;
import com.niemiec.objects.Ship;
import com.niemiec.objects.VirtualPlayer;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class GameQueue {

	private Player[] players;
	private GetBoxAndGameData getBoxAndGameData;

	public GameQueue(Player[] players) {
		this.players = players;
		this.getBoxAndGameData = new GetBoxAndGameData();
	}

	public boolean shot(int[] xyFromButton, ObservableList<Node> observableList) {

		// jeżeli gracz trafił to funka zwraca
		// jeżeli nie trafił wpada do pętli gdzie gra komputer i gdy ten się pomyli to
		// zwraca
		int a = playRealPlayer(xyFromButton, 0);
		if (a == 0) {
			if (playVirtualPlayer(1)) {
				System.out.println("WYGRYWA KOMPUTER!");
				return true;
			}

			return false;
		} else if (a == 1) {
			return false;
		} else if (a == 2) {
			System.out.println("WYGRYWA GRACZ " + players[0].getName());
			return true;
		}
		return false;
	}

	// funkjca używana przy peirwszym strzale komputer!!
	public void shotWhenTheComputerFirst() {
		playVirtualPlayer(1);
	}

	public void setPlayers(Player[] players) {
		this.players = players;
		// WSTWIĆ ZEROWANIE ZMIENNYCH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	private int playRealPlayer(int[] box, int activePlayer) {
		int opponentPlayer = 1;
		// pobiera dane - do wyboru w zale�no�ci czy wirtualny czy realny
		// (dwie
		// oddzielne metody)

		// sprawdza z plansz� przeciwnika
		// je�eli trafiony to gramy dalej
		// je�eli pud�o zwraca false
		// je�eli wszystkie statki zbite zwraca true
//		System.out.println("Wybrane punty to: x = " + box[0] + ", y = " + box[1]);
		// najpierw por�wna z plansz� przeciwnika, kt�r� gracz ma u siebie,
		// czy dany punkt ju� zaznacza�
		// je�eli nie zaznacza� (==0) to sprawdza z tablic� przeciwnika
		// (pobiera od niego punkt i sprawdza czy statek, czy pud�o)
		// je�eli trafione to zwraca true
		// je�eli pud�o zwraca false
		// co ka�dy zatopiony statek zwi�ksza zmienn� snukenShips o jeden
		int a = players[opponentPlayer].getBoard().getBox(box);
		if (!checkWithTheOpponentsBoard(activePlayer, box) && a == 0) {
			return 0;
		}

		

		if (players[opponentPlayer].getSunkenShips() == 10)
			return 2;
		
		if (a != 0)
			return 1;
		return 1;

	}

	// je�eli zwr�ci prawd� to wszystkie statki trafione i gracz wygrywa
	private boolean playVirtualPlayer(int activePlayer) {
		int opponentPlayer = 0;
		int box[];
		while (true) {
			box = downloadBoxFromVirtualPlayer(activePlayer);
			System.out.println("Wybrane punty to: x = " + box[0] + ", y = " + box[1]);
			// je�eli statek si� zmie�ci to sprawdza trafienie, je�eli �aden
			// statek si� nie zmie�ci to szuka dalej
			if (!checkWithTheOpponentsBoard(activePlayer, box))
				return false;

			if (players[opponentPlayer].getSunkenShips() == 10)
				return true;
		}

	}

	private int[] downloadBoxFromVirtualPlayer(int activePlayer) {
		VirtualPlayer vp = (VirtualPlayer) players[activePlayer];
		if (vp.isOnHit()) {
			System.out.println("JESTEM W FUNKCJI downloadBoxFromVirtualPlayer w vp.isOnHit() - TRUE");
			// je�eli jest w trakcie trafiania to losuje inaczej
			// wtedy mamy warto�ci ostatniego trafionego pola
			// po nich dotrzemy do danych statku i po drugim trafieniu mo�emy pobra� inf. o
			// kierunku
			return nextMoveVirtualPlayer(activePlayer);
		} else {
			return firstMoveVirtualPlayer(activePlayer);
		}
	}

	private int[] nextMoveVirtualPlayer(int activePlayer) {
		VirtualPlayer vp = (VirtualPlayer) players[activePlayer];

		if (vp.getWayOnHit() == 0) {
			// jeszcze nie zost� ustalony kierunek, wi�c to drugi ruch
			// 1. losowanie x lub y
			// 2. sprawdzenie czy w wylosowanym kierunku si� zmie�ci
			// 3. losowanie g�ra, d�/prawo, lewo
			// 4. sprawdzenie czy wolne pole
			// 5. je�eli tak zwrac box
			// 6. je�eli nie powt�rzenie od 1
			// -way- (1 - wzd�� y, 2 - wzd�� x)
			// -direction- (0 - prawo, d�, 1 - lewo, g�ra)
			return hitOfSecondMastByVirtualPlayer(vp, activePlayer);
		} else {
			return hitOfTheOthersMastsByVirtualPlayer(vp, activePlayer);
		}
	}

	private int[] hitOfTheOthersMastsByVirtualPlayer(VirtualPlayer vp, int activePlayer) {
		Random random = new Random();
		int opponentPlayer = (activePlayer == 0) ? 1 : 0;
		int direction = 0;
		int x = vp.getBoxOnHit()[0];
		int y = vp.getBoxOnHit()[1];

		int cnohm = players[opponentPlayer].getCollectionShips().getShip(vp.getBoxOnHit()).getCurrentNumberOfHitMasts();
		int i = 0;
		while (true) {
			direction = random.nextInt(2);
			System.out.println("JESETEM W FUNKCJI hitOfTheOthersMastsByVirtualPlayer i wylosowane 'direction' wynosi "
					+ direction + ", way = " + vp.getWayOnHit());
			if (vp.getWayOnHit() == 1) {
				if (direction == 0 && checkIfWithinThePlayingField(y, 1)
						&& vp.getOpponentBoard().getBox(x, y + 1) == 0) {
					return new int[] { x, y + 1 };
				} else if (direction == 0 && checkIfWithinThePlayingField(y, cnohm)
						&& vp.getOpponentBoard().getBox(x, y + cnohm - 1) == 3
						&& vp.getOpponentBoard().getBox(x, y + cnohm) == 0) {
					return new int[] { x, y + cnohm };
				}
				if (direction == 1 && checkIfWithinThePlayingField(y, -1)
						&& vp.getOpponentBoard().getBox(x, y - 1) == 0) {
					return new int[] { x, y - 1 };
				} else if (direction == 1 && checkIfWithinThePlayingField(y, -cnohm)
						&& vp.getOpponentBoard().getBox(x, y - cnohm + 1) == 3
						&& vp.getOpponentBoard().getBox(x, y - cnohm) == 0) {
					return new int[] { x, y - cnohm };
				}
			} else if (vp.getWayOnHit() == 2) {
				if (direction == 0 && checkIfWithinThePlayingField(x, 1)
						&& vp.getOpponentBoard().getBox(x + 1, y) == 0) {
					return new int[] { x + 1, y };
				} else if (direction == 0 && checkIfWithinThePlayingField(x, cnohm)
						&& vp.getOpponentBoard().getBox(x + cnohm - 1, y) == 3
						&& vp.getOpponentBoard().getBox(x + cnohm, y) == 0) {
					return new int[] { x + cnohm, y };
				}
				if (direction == 1 && checkIfWithinThePlayingField(x, -1)
						&& vp.getOpponentBoard().getBox(x - 1, y) == 0) {
					return new int[] { x - 1, y };
				} else if (direction == 1 && checkIfWithinThePlayingField(x, -cnohm)
						&& vp.getOpponentBoard().getBox(x - cnohm + 1, y) == 3
						&& vp.getOpponentBoard().getBox(x - cnohm, y) == 0) {
					return new int[] { x - cnohm, y };
				}
			}
			i++;
			if (i == 30) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
		}
	}

	private int[] hitOfSecondMastByVirtualPlayer(VirtualPlayer vp, int activePlayer) {
		Random random = new Random();
		int opponentPlayer = (activePlayer == 0) ? 1 : 0;
		int way = 0;
		int direction = 0;
		int box[] = new int[] { 0, 0 };
		while (true) {
			way = random.nextInt(2) + 1;
			direction = random.nextInt(2);
			if (way == 1) {
				box[0] = vp.getBoxOnHit()[0];
				if (direction == 0 && checkIfWithinThePlayingField(box[1], 1)) {
					box[1] = vp.getBoxOnHit()[1] + 1;
				} else if (direction == 1 && checkIfWithinThePlayingField(box[1], -1)) {
					box[1] = vp.getBoxOnHit()[1] - 1;
				}
			} else if (way == 2) {
				box[1] = vp.getBoxOnHit()[1];
				if (direction == 0 && checkIfWithinThePlayingField(box[0], 1)) {
					box[0] = vp.getBoxOnHit()[0] + 1;
				} else if (direction == 1 && checkIfWithinThePlayingField(box[0], -1)) {
					box[0] = vp.getBoxOnHit()[0] - 1;
				}
			}

			if (box[0] > 0 && box[0] <= 10 && box[1] > 0 && box[1] <= 10
					&& vp.getOpponentBoard().getBox(box[0], box[1]) == 0) {
				break;
			}
		}
		if (box[0] != 0 && box[1] != 0 && players[opponentPlayer].getBoard().getBox(box) == 2)
			vp.setWayOnHit(way);
		return box;
	}

	// je�eli nie jest w trakcie trafiania
	// podczas pierwszego trafiana sprawdza, czy jest miejsce
	// potrzebna p�tla! osobna metoda
	// willTheShipFit(box, minimalNumberOfMasts, activePlayer);
	private int[] firstMoveVirtualPlayer(int activePlayer) {
		int opponentPlayer = (activePlayer == 0) ? 1 : 0;
		int minimalNumberOfMasts = getMinimalNumberOfMasts(opponentPlayer);
		int box[] = new int[] { 0, 0 };
		while (true) {
			box = getBoxAndGameData.randomTheFirstMast();
			if (players[activePlayer].getOpponentBoard().getBox(box[0], box[1]) == 0
					&& willTheShipFit(box, minimalNumberOfMasts, activePlayer)) {
				break;
			}
		}

		return box;
	}

	private int getMinimalNumberOfMasts(int opponentPlayer) {
		int a = players[opponentPlayer].getCollectionShips().getShip(0).getNumberOfMasts();
		for (int i = 1; i < 10; i++) {
			int b = players[opponentPlayer].getCollectionShips().getShip(i).getNumberOfMasts();
			if (b < a)
				a = b;
		}
		return a;
	}

	// gdy zwraca true znaczy, �e trafiony
	private boolean checkWithTheOpponentsBoard(int activePlayer, int[] box) {
		int opponentPlayer = (activePlayer == 0) ? 1 : 0;

		// pobiera pole z tablicy rzeciwnika
		int opponentBox = players[opponentPlayer].getBoard().getBox(box[0], box[1]);

		// sprawdza pole i uruchamia opdowiednie metody
		switch (opponentBox) {
		case 0:
//			System.out.println("PUD�O!");
			players[activePlayer].getOpponentBoard().setBox(box[0], box[1], 1);
			players[opponentPlayer].getBoard().setBox(box[0], box[1], 1);
			return false;
		case 2:
//			System.out.println("Trafiony");
			actionAfterHit(activePlayer, box);
			return true; // tutaj odpowiednia funkcja podczas trafienia (zlicza
							// czy zatopiony, obrabia w ok� zatopoionego
		// statku pola, itp.)
		default:
			System.out.println("NIEOCZEKIWANY B�AD");
			break;
		}
		return false;
	}

	private void actionAfterHit(int activePlayer, int[] box) {
		int opponentPlayer = (activePlayer == 0) ? 1 : 0;
		if (players[opponentPlayer].getCollectionShips().checkShip(box)) {
			// zmien na 5 bo zatopiony
			// players[activePlayer].getOpponentBoard().setBox(box[0], box[1],
			// 5);
			change3To5AndInsert1Around(activePlayer, box);
			players[opponentPlayer].increaseSunkenShips();
			// je�eli virtualny gracz trafi statek to zmienna informuj�ca, �e
			// jest w trakcie zatapiania jest zmieniana na false
			if (players[activePlayer].getInformationInThePlayerIsVirtual()) {
				VirtualPlayer vp = (VirtualPlayer) players[activePlayer];
				vp.setOnHit(false);
				vp.setBoxOnHit(0, 0);
				vp.setWayOnHit(0);
			}

		} else {
			// zmiana na 3 bo nie zatopiony
			players[activePlayer].getOpponentBoard().setBox(box[0], box[1], 3);
			players[opponentPlayer].getBoard().setBox(box[0], box[1], 3);
			if (players[activePlayer].getInformationInThePlayerIsVirtual()) {
				VirtualPlayer vp = (VirtualPlayer) players[activePlayer];
				vp.setOnHit(true);
				vp.setBoxOnHit(box);
			}
		}

	}

	private void change3To5AndInsert1Around(int activePlayer, int[] box) {
		int opponentPlayer = (activePlayer == 0) ? 1 : 0;
		Ship ship = players[opponentPlayer].getCollectionShips().getShip(box);
		for (int i = 1; i <= ship.getNumberOfMasts(); i++) {
			int x = ship.getX(i);
			int y = ship.getY(i);
			players[activePlayer].getOpponentBoard().setBox(x, y, 5);
			players[opponentPlayer].getBoard().setBox(x, y, 5);
			System.out.println("Jeste�my w funkcji zamieniaj�cej 3 na 5, i tak x = " + x + ", y = " + y);
		}
		for (int i = 1; i <= ship.getNumberOfMasts(); i++) {
			int x = ship.getX(i);
			int y = ship.getY(i);
			for (int j = -1; j < 2; j++) {
				for (int k = -1; k < 2; k++) {
					if (checkIfWithinThePlayingField(x, j) && checkIfWithinThePlayingField(y, k)) {
						if (players[opponentPlayer].getBoard().getBox(x + j, y + k) != 1
								&& players[opponentPlayer].getBoard().getBox(x + j, y + k) != 5) {
							players[opponentPlayer].getBoard().setBox(x + j, y + k, 1);
							players[activePlayer].getOpponentBoard().setBox(x + j, y + k, 1);
						}
					}
				}
			}
		}
	}

	private boolean checkIfWithinThePlayingField(int a, int b) {
		if (a + b <= 10 && a + b >= 1)
			return true;

		return false;
	}

	private boolean willTheShipFit(int[] box, int minimalNumberOfMasts, int activePlayer) {
		InsertShips insertShips = new InsertShips();
		if (insertShips.checkIsThereAPlace(box, minimalNumberOfMasts, players[activePlayer].getOpponentBoard()) != 0)
			return true;
		return false;
	}
}
