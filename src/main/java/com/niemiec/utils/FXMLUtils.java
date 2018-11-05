package com.niemiec.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

//klasa odpowiedzialna za ładowanie wszystkich fxml naszej aplikacji
public class FXMLUtils {
	// Pane - klasa po której dziedzczą wszystkie kontenery JavyFx
	public static Pane fxmlLoader(String fxmlPath) {
		FXMLLoader loader = new FXMLLoader(FXMLUtils.class.getResource(fxmlPath));
		
		try {
			return loader.load();
		} catch (Exception e) {
//			DialogUtils.errorDialog(e.getMessage());
			System.out.println("error");
		}
		return null;
	}
}
