package com.niemiec.logic;

import com.niemiec.objects.Board;
import com.niemiec.objects.GetBoxAndGameData;
import com.niemiec.objects.Player;
import com.niemiec.objects.RealPlayer;
import com.niemiec.objects.VirtualPlayer;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

//tworzenie graczy
//dodawanie statków
//rozgrywka
//klasa zostanie utworzona w kontrolerze MainScreenController
//podstawowa klasa do zarządzania logiką gry
public class GameLogic {
	private Player[] players;
	boolean typeOfPlayer;
	private AddShipsManually addShipsManually;
	private GameQueue gameQueue;

	public GameLogic(String name) {
		createPlayers(name);
		this.addShipsManually = new AddShipsManually(players[0]);
		this.gameQueue = new GameQueue(players);
	}

	public void startNewGame(String name, ObservableList<Node> observableList, ObservableList<Node> observableList2) {
		createPlayers(players[0].getName());
		updateBorderInJavaFX(players[0].getBoard(), observableList);
//		updateBorderInJavaFX(players[1].getBoard(), observableList2);
		addShipsManually.setPlayer(players[0]);
		gameQueue.setPlayers(players);
	}

	public boolean placeAShip(int[] xyFromButton, ObservableList<Node> observableList, int inputMethod) {
		if (addShipsManually.addShips(xyFromButton, inputMethod)) {
			updateBorderInJavaFX(players[0].getBoard(), observableList);
//			players[0].getBoard().viewBoard();
			return true;
		}
		updateBorderInJavaFX(players[0].getBoard(), observableList);

		return false;
	}

	public boolean shotAShip(int[] xyFromButton, ObservableList<Node> observableList, ObservableList<Node> observableList2) {
		if (xyFromButton[0] == 0) {
			gameQueue.shotWhenTheComputerFirst();
			updateBorderInJavaFX(players[0].getBoard(), observableList);
			updateBorderInJavaFX(players[0].getOpponentBoard(), observableList2);
			return false;
		}
		
		if (gameQueue.shot(xyFromButton, observableList)) {
			updateBorderInJavaFX(players[0].getOpponentBoard(), observableList);
			return true;
		}
		updateBorderInJavaFX(players[0].getBoard(), observableList);
		updateBorderInJavaFX(players[0].getOpponentBoard(), observableList2);
		
		return false;
	}

	private void createPlayers(String name) {
		this.players = new Player[2];
		players[0] = new RealPlayer(name);
		players[1] = new VirtualPlayer("Virtual Player");
		players[1].addShipsAutomatically();
	}

	private void updateBorderInJavaFX(Board board, ObservableList<Node> observableList) {
		for (int y = 0; y < 10; y++) {
			HBox hbox = (HBox) observableList.get(y);
			for (int x = 0; x < 10; x++) {
				Button button = (Button) hbox.getChildren().get(x);
				int box = board.getBox(x + 1, y + 1);
				button.setText(box != 0 ? Integer.toString(box) : "");
			}
		}
	}
}
