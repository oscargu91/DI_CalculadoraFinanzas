package com.carballeira.aplication.view;

import com.carballeira.aplication.controller.FormularioController;
import com.carballeira.aplication.model.Usuario;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FormularioView extends Application {

	//Clave token repositorio: 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
	
	
	// VARIABLES GLOBALES:
	
	private TextField fieldNombre;
	private TextField fieldApellidos;
	private TextField fieldUsuario;
	private TextField fieldContrasenha;
	
	// CONSTANTES:
	
	private final String NOMBRE_LBL = "Nombre:* ";
	private final String APELLIDOS_LBL = "Apellidos:* ";
	private final String USUARIO_LBL = "Usuario:* ";
	private final String PASSWORD_LBL = "Contrasenha:* ";
	private final String ESTATUS_LBL = "Estatus:* ";
	
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	// DIBUJO EL FORMULARIO	
		GridPane root = new GridPane();
	// AÑADO LOS DATOS DEL USUARIO A LA TABLA CON EL EVENTO DEL BOTON	
		
		
	}
}
	
	/*
	
	
	// Crear la tabla
	TableView<Usuario> tabla = new TableView<>();
	@Override
	
	
	
	
	
	public void start(Stage primaryStage) throws Exception {
		try {

			// Creo el gridPane
			GridPane root = new GridPane();

			// Establecer espacios entre los nodos
			root.setHgap(8);
			root.setVgap(8);
			root.setPadding(new Insets(5));

			Scene scene = new Scene(root, 400, 400);

			
			
		//CREO LAS LABEL CON CONSTANTES:
			
			Label nombreLbl = new Label(NOMBRE_LBL);				
			Label apellidosLbl = new Label(APELLIDOS_LBL);				
			Label usuarioLbl = new Label(USUARIO_LBL);				
			Label contrasenhaLbl = new Label(PASSWORD_LBL);			
			Label estatusLbl = new Label(ESTATUS_LBL);
			

			fieldNombre = new TextField();
			fieldApellidos = new TextField();
			fieldUsuario = new TextField();
			fieldContrasenha = new TextField();

			
			
			
			// Creo un Hbox para meter dentro uno al lado del otro los radioButton.
			// Luego añado el Hbox a la casilla del gridPane que quiero.
			HBox caja = new HBox(5);

			// Creo los RadioButton

			RadioButton radioButtonActivo = new RadioButton("Activo");

			// texto5.setId("radio"); //Si quisiera añadir la característica css de forma
			// particular a estos dos radioButton

			RadioButton radioButtonInactivo = new RadioButton("Inactivo");

			// texto6.setId("radio");
			// Si quisiera añadir la característica css de forma particular a estos dos
			// radioButton

			// Añado los radioButton a la caja Hbox que he creado para luego añadirla al
			// gridPane.

			caja.getChildren().addAll(radioButtonActivo);
			caja.getChildren().addAll(radioButtonInactivo);

			// Creo columnas con el nombre de cada una de ellas
			TableColumn<Usuario, String> nombreColumna = new TableColumn<>("Nombre");
			nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre")); // Enlaza al atributo 'nombre'

			TableColumn<Usuario, String> apellidoColumna = new TableColumn<>("Apellidos");
			apellidoColumna.setCellValueFactory(new PropertyValueFactory<>("apellidos")); // Enlaza al atributo
																							// 'apellidos'

			TableColumn<Usuario, String> usuColumna = new TableColumn<>("Usuario");
			usuColumna.setCellValueFactory(new PropertyValueFactory<>("usu")); // Enlaza al atributo 'usu'

			TableColumn<Usuario, String> estatusColumna = new TableColumn<>("Estatus");
			estatusColumna.setCellValueFactory(new PropertyValueFactory<>("estatus")); // Enlaza al atributo 'estatus'

			// Añado las columnas a la tabla
			tabla.getColumns().add(nombreColumna);
			tabla.getColumns().add(apellidoColumna);
			tabla.getColumns().add(usuColumna);
			tabla.getColumns().add(estatusColumna);

			// Establecer el número de filas a mostrar (7)
			tabla.setPrefHeight(7 * 28); // Suponiendo que cada fila tiene una altura de 28 píxeles

			// Creo el Button "A�adir Usuario"

			Button botonAnhadirUsu = new Button("A�adir");
			
			
		
			
              botonAnhadirUsu.setOnAction(event -> {

				String nombre = fieldNombre.getText();
				String apellidos = fieldApellidos.getText();
				String usuario = fieldUsuario.getText();
				String password = fieldContrasenha.getText();
				String estatus = radioButtonActivo.isSelected() ? "Activo" : "Inactivo";

				
				
				Usuario nuevoUsuario = new Usuario(nombre, apellidos, usuario, password, estatus);

				
				
				// Creo controller

				FormularioController controlador = new FormularioController(nuevoUsuario, this);

				
				
				if(nuevoUsuario.comprobarDatos()) {
				controlador.addDatosTabla();
				}
				
				
	});

			// Añado los elementos al gridPane principal creado inicialmente.
			root.add(nombreLbl, 0, 0); // Columna 0 fila 0
			root.add(fieldNombre, 1, 0);// Columna 1 fila 0
			root.add(apellidosLbl, 2, 0);
			root.add(fieldApellidos, 3, 0);
			root.add(usuarioLbl, 0, 1);
			root.add(fieldUsuario, 1, 1);
			root.add(contrasenhaLbl, 2, 1);
			root.add(fieldContrasenha, 3, 1);
			root.add(estatusLbl, 0, 2);
			root.add(caja, 1, 2);
			root.add(botonAnhadirUsu, 2, 2);
			root.add(tabla, 0, 3, 5, 11); // Añado la tabla al gridPane, con el tamaño que quiero que ocupe.

			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle("Formulario de Usuario");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void addDatosTabla(Usuario user) {
		tabla.getItems().add(user);

	}
*/
