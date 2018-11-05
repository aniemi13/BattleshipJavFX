package com.niemiec.controllers;

import java.util.Random;

import com.niemiec.logic.CheckData;
import com.niemiec.logic.GameLogic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainScreenController {

	@FXML
	private VBox myBorder;

	@FXML
	private Button mb00;

	@FXML
	private Button mb10;

	@FXML
	private Button mb20;

	@FXML
	private Button mb30;

	@FXML
	private Button mb40;

	@FXML
	private Button mb50;

	@FXML
	private Button mb60;

	@FXML
	private Button mb70;

	@FXML
	private Button mb80;

	@FXML
	private Button mb90;

	@FXML
	private Button mb01;

	@FXML
	private Button mb11;

	@FXML
	private Button mb21;

	@FXML
	private Button mb31;

	@FXML
	private Button mb41;

	@FXML
	private Button mb51;

	@FXML
	private Button mb61;

	@FXML
	private Button mb71;

	@FXML
	private Button mb81;

	@FXML
	private Button mb91;

	@FXML
	private Button mb02;

	@FXML
	private Button mb12;

	@FXML
	private Button mb22;

	@FXML
	private Button mb32;

	@FXML
	private Button mb42;

	@FXML
	private Button mb52;

	@FXML
	private Button mb62;

	@FXML
	private Button mb72;

	@FXML
	private Button mb82;

	@FXML
	private Button mb92;

	@FXML
	private Button mb03;

	@FXML
	private Button mb13;

	@FXML
	private Button mb23;

	@FXML
	private Button mb33;

	@FXML
	private Button mb43;

	@FXML
	private Button mb53;

	@FXML
	private Button mb63;

	@FXML
	private Button mb73;

	@FXML
	private Button mb83;

	@FXML
	private Button mb93;

	@FXML
	private Button mb04;

	@FXML
	private Button mb14;

	@FXML
	private Button mb24;

	@FXML
	private Button mb34;

	@FXML
	private Button mb44;

	@FXML
	private Button mb54;

	@FXML
	private Button mb64;

	@FXML
	private Button mb74;

	@FXML
	private Button mb84;

	@FXML
	private Button mb94;

	@FXML
	private Button mb05;

	@FXML
	private Button mb15;

	@FXML
	private Button mb25;

	@FXML
	private Button mb35;

	@FXML
	private Button mb45;

	@FXML
	private Button mb55;

	@FXML
	private Button mb65;

	@FXML
	private Button mb75;

	@FXML
	private Button mb85;

	@FXML
	private Button mb95;

	@FXML
	private Button mb06;

	@FXML
	private Button mb16;

	@FXML
	private Button mb26;

	@FXML
	private Button mb36;

	@FXML
	private Button mb46;

	@FXML
	private Button mb56;

	@FXML
	private Button mb66;

	@FXML
	private Button mb76;

	@FXML
	private Button mb86;

	@FXML
	private Button mb96;

	@FXML
	private Button mb07;

	@FXML
	private Button mb17;

	@FXML
	private Button mb27;

	@FXML
	private Button mb37;

	@FXML
	private Button mb47;

	@FXML
	private Button mb57;

	@FXML
	private Button mb67;

	@FXML
	private Button mb77;

	@FXML
	private Button mb87;

	@FXML
	private Button mb97;

	@FXML
	private Button mb08;

	@FXML
	private Button mb18;

	@FXML
	private Button mb28;

	@FXML
	private Button mb38;

	@FXML
	private Button mb48;

	@FXML
	private Button mb58;

	@FXML
	private Button mb68;

	@FXML
	private Button mb78;

	@FXML
	private Button mb88;

	@FXML
	private Button mb98;

	@FXML
	private Button mb09;

	@FXML
	private Button mb19;

	@FXML
	private Button mb29;

	@FXML
	private Button mb39;

	@FXML
	private Button mb49;

	@FXML
	private Button mb59;

	@FXML
	private Button mb69;

	@FXML
	private Button mb79;

	@FXML
	private Button mb89;

	@FXML
	private Button mb99;

	@FXML
	private VBox opponentBorder;

	@FXML
	private Button ob00;

	@FXML
	private Button ob10;

	@FXML
	private Button ob20;

	@FXML
	private Button ob30;

	@FXML
	private Button ob40;

	@FXML
	private Button ob50;

	@FXML
	private Button ob60;

	@FXML
	private Button ob70;

	@FXML
	private Button ob80;

	@FXML
	private Button ob90;

	@FXML
	private Button ob01;

	@FXML
	private Button ob11;

	@FXML
	private Button ob21;

	@FXML
	private Button ob31;

	@FXML
	private Button ob41;

	@FXML
	private Button ob51;

	@FXML
	private Button ob61;

	@FXML
	private Button ob71;

	@FXML
	private Button ob81;

	@FXML
	private Button ob91;

	@FXML
	private Button ob02;

	@FXML
	private Button ob12;

	@FXML
	private Button ob22;

	@FXML
	private Button ob32;

	@FXML
	private Button ob42;

	@FXML
	private Button ob52;

	@FXML
	private Button ob62;

	@FXML
	private Button ob72;

	@FXML
	private Button ob82;

	@FXML
	private Button ob92;

	@FXML
	private Button ob03;

	@FXML
	private Button ob13;

	@FXML
	private Button ob23;

	@FXML
	private Button ob33;

	@FXML
	private Button ob43;

	@FXML
	private Button ob53;

	@FXML
	private Button ob63;

	@FXML
	private Button ob73;

	@FXML
	private Button ob83;

	@FXML
	private Button ob93;

	@FXML
	private Button ob04;

	@FXML
	private Button ob14;

	@FXML
	private Button ob24;

	@FXML
	private Button ob34;

	@FXML
	private Button ob44;

	@FXML
	private Button ob54;

	@FXML
	private Button ob64;

	@FXML
	private Button ob74;

	@FXML
	private Button ob84;

	@FXML
	private Button ob94;

	@FXML
	private Button ob05;

	@FXML
	private Button ob15;

	@FXML
	private Button ob25;

	@FXML
	private Button ob35;

	@FXML
	private Button ob45;

	@FXML
	private Button ob55;

	@FXML
	private Button ob65;

	@FXML
	private Button ob75;

	@FXML
	private Button ob85;

	@FXML
	private Button ob95;

	@FXML
	private Button ob06;

	@FXML
	private Button ob16;

	@FXML
	private Button ob26;

	@FXML
	private Button ob36;

	@FXML
	private Button ob46;

	@FXML
	private Button ob56;

	@FXML
	private Button ob66;

	@FXML
	private Button ob76;

	@FXML
	private Button ob86;

	@FXML
	private Button ob96;

	@FXML
	private Button ob07;

	@FXML
	private Button ob17;

	@FXML
	private Button ob27;

	@FXML
	private Button ob37;

	@FXML
	private Button ob47;

	@FXML
	private Button ob57;

	@FXML
	private Button ob67;

	@FXML
	private Button ob77;

	@FXML
	private Button ob87;

	@FXML
	private Button ob97;

	@FXML
	private Button ob08;

	@FXML
	private Button ob18;

	@FXML
	private Button ob28;

	@FXML
	private Button ob38;

	@FXML
	private Button ob48;

	@FXML
	private Button ob58;

	@FXML
	private Button ob68;

	@FXML
	private Button ob78;

	@FXML
	private Button ob88;

	@FXML
	private Button ob98;

	@FXML
	private Button ob09;

	@FXML
	private Button ob19;

	@FXML
	private Button ob29;

	@FXML
	private Button ob39;

	@FXML
	private Button ob49;

	@FXML
	private Button ob59;

	@FXML
	private Button ob69;

	@FXML
	private Button ob79;

	@FXML
	private Button ob89;

	@FXML
	private Button ob99;

	private String name = "gracz1";
	private GameLogic gameLogic;
	private int spacingOfShips = CheckData.MANUALLY;
	private int queue;

	public MainScreenController() {
		// okno z pobraniem nazwy
		gameLogic = new GameLogic(name);
		queue = randomStart();
	}

	// funkcja zaczynająca grę od początku
	// wywoływana na starcie i po wciśnięciu przycisku zacznik od nowa
	@FXML
	void startGame() {
		queue = randomStart();
		myBorder.setDisable(true);
		opponentBorder.setDisable(true);
		gameLogic.startNewGame(name, myBorder.getChildren(), opponentBorder.getChildren());
		if (spacingOfShips == CheckData.AUTOMATICALLY) {
			// AUTOMATYCZNIE ROZŁOŻYĆ
			while (true) {
				if (gameLogic.placeAShip(new int[] { 0, 0 }, myBorder.getChildren(), spacingOfShips)) {
					break;
				}
			}
			myBorder.setDisable(true);
			opponentBorder.setDisable(false);
			//Jeżeli wylosowane zostanie 0 to zaczyna komputer
			//więc nie można zacząć gry od wciśnięcia przysiku
			//na tym etapie trzeba zacząć rozrywkę
			//przekazujemy tablicę z zerami, dzięki której klasa GameLogic wie,
			//że powinna uruchomić funkję odpowiedzialną za momenty, gdy
			//pierwsze trafienie musi wykonać komputer
			if (queue == 0) {
				opponentBorder.setDisable(true);
				gameLogic.shotAShip(new int[] {0, 0}, myBorder.getChildren(), opponentBorder.getChildren());
				opponentBorder.setDisable(false);
				queue = 3;
			}
		} else {
			myBorder.setDisable(false);
			opponentBorder.setDisable(true);
		}

	}

	@FXML
	void myButtonAction(ActionEvent event) {
		// jeżeli wszystkie statki ustawione myBorder ustawia na nieaktywny
		
		if (gameLogic.placeAShip(getXYFromButton((Button) event.getSource()), myBorder.getChildren(), spacingOfShips)) {
			myBorder.setDisable(true);
			opponentBorder.setDisable(false);
			if (queue == 0) {
				opponentBorder.setDisable(true);
				gameLogic.shotAShip(new int[] {0, 0}, myBorder.getChildren(), opponentBorder.getChildren());
				opponentBorder.setDisable(false);
			}
		}
	}

	// po wciśnięciu tego przyciusku wykonuje się kolejka zarówno gracza, jak i
	// komputera
	// jeżeli przyciski odblokowane to zaczyna się kolejka gracza
	@FXML
	void opponentButtonAction(ActionEvent event) {
		// przekazuje opponentBorder i jeżeli nie trafiony to gameLogic blokuje
		// opponenBorder, na czas
		// trafiania przeciwnika jego plansza musi zostać dla nas zablokowana
//		opponentBorder.setDisable(true);
		if (gameLogic.shotAShip(getXYFromButton((Button) event.getSource()), myBorder.getChildren(), opponentBorder.getChildren())) {
			
			//gra skończona
			//wyświetlić komunikat kto wygrał i czy zacznąć nową grę
			
			opponentBorder.setDisable(true);
		}
//		opponentBorder.setDisable(false);
	}

	private int[] getXYFromButton(Button button) {
		String id = button.getId();
		char[] chars = new char[2];
		id.getChars(2, 4, chars, 0);

		return new int[] { Character.getNumericValue(chars[0]) + 1, Character.getNumericValue(chars[1]) + 1 };
	}

	@FXML
	void initialize() {
		opponentBorder.setDisable(true);
	}

	@FXML
	void close() {

	}

	@FXML
	void setAutomaticallySpacingOfShips() {
		if (spacingOfShips == CheckData.MANUALLY)
			spacingOfShips = CheckData.AUTOMATICALLY;
		else
			spacingOfShips = CheckData.MANUALLY;

		startGame();
	}
	
	private int randomStart() {
		Random random = new Random();
		return random.nextInt(2);
	}
}
