package com.carballeira.controller;

import com.carballeira.model.FinanceEntry;
import com.carballeira.view.AlertaView;
import com.carballeira.view.Main;

import javafx.stage.Stage;

public class LoginController {
	private FinanceEntry model;
	private Main vista;

	public static final String ERROR = "Error";
	public static final String ERROR_LOGIN = "Debe rellenar todos los campos para iniciar sesi�n.";

	public LoginController(FinanceEntry model, Main vista) {
		this.model = model;
		this.vista = vista;
	}

	public LoginController(Main vista) {
		this.vista = vista;
	}

	public LoginController() {
	}

	public void manejarInicioSesion(Main vista, Stage loginStage, String usuario, String contrasena,
			Stage primaryStage) {

		if (!usuario.isEmpty() && !contrasena.isEmpty()) {
			loginStage.close();
			vista.mostrarPantallaPrincipal(primaryStage);
		} else {
			AlertaView vista2 = new AlertaView();
			AlertaController controladorAlerta = new AlertaController(vista2);
			controladorAlerta.crearAlerta(ERROR, ERROR_LOGIN);
		}
	}

}
