package com.carballeira.view;

import javafx.scene.control.Alert;

public class AlertaView {

	// metodo de creacion de las alertas
	public void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
