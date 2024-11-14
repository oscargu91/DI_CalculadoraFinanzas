package com.carballeira.controller;

import com.carballeira.model.FinanceEntry;
import com.carballeira.view.Main;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	private FinanceEntry model;
	private Main vista;
	
	public LoginController(FinanceEntry model, Main vista) {
		this.model = model;
		this.vista = vista;
	}

	public LoginController(Main vista) {
		this.vista = vista;
	}
	
	
	
	public LoginController() {
	}

	public void manejarInicioSesion(Main vista ,Stage loginStage, String usuario, String contrasena, Stage primaryStage) {

        if (!usuario.isEmpty() && !contrasena.isEmpty()) {
            loginStage.close();
            vista.mostrarPantallaPrincipal(primaryStage);
        } else {
            vista.showAlert("Error", "Debe rellenar todos los campos para iniciar sesión.");
        }
    }

	
}
