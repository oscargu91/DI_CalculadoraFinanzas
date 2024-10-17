package com.carballeira.aplicacion.controller;
import javafx.stage.Stage;
import com.carballeira.aplicacion.model.Usuario;
import com.carballeira.aplicacion.view.AplicacionMenuView;
import com.carballeira.aplicacion.view.UsuarioView;

public class UsuarioController {

	
	private UsuarioView vista;
	private Usuario modelo;
	
	//Creo un constructor para saber en que modelo y vista trabajar
	
	public UsuarioController(Usuario modelo, UsuarioView vista) {
		this.modelo = modelo;
		this.vista = vista;
		
	}
	public void checkCredencialesAcceso(Stage stage) {
		// LLAMAMOS AL MODELO PARA CHEQUEAR SI ESTÁ CORRECTO EL LOGIN
		if(modelo.comprobarUsuario()) {
			// USUARIO CORRECTO, REENVIA A OTRA PANTALLA
			AplicacionMenuView menuView = new AplicacionMenuView();	
			menuView.startMenuView(stage);
		}
		else {
		// NO ES CORRECTO EL USUARIO, DEVUELVE UNA ALERTA INFORMATIVA
			
					
			
		}
	
	
	
				
  	}
}