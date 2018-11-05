package com.niemiec.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogUtils {
	public static void setNameAndShip() {
		Alert alert = new Alert(Alert.AlertType.NONE);
		alert.setTitle("Podaj szczegóły gry");
		alert.setWidth(400);
		alert.setHeight(700);

		alert.getDialogPane().setContent(FXMLUtils.fxmlLoader("/fxml/getNameAndShipScreen.fxml"));
		alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
		alert.show();
		
//		Optional<ButtonType> result = alert.showAndWait();
//		return (result.get() == ButtonType.OK);
	}
}
