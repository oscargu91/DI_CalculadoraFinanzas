package com.carballeira.aplicacion.view;
import com.carballeira.aplicacion.controller.UsuarioController;
import com.carballeira.aplicacion.model.Usuario;

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

public class UsuarioView extends Application {


	//token: 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
	
	
    // VARIABLES GLOBALES:
    private TextField fieldUsuario;
    private TextField fieldContrasenha;
    private Button botonLogin;

    // CONSTANTES:
    private final String USUARIO_LBL = "USUARIO: ";
    private final String TITULO_LBL = "Bienvenido! Haga Login";
    private final String PASSWORD_LBL = "PWD: ";
    private final String BUTTON_LBL = "ENTRAR";
    private final String TITLE_FORM_LBL = "LOGIN";
    private final String PATH_CSS = "/application/application.css";
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        
    	this.primaryStage = primaryStage;
    	try {
        	
            // Crear el GridPane
            GridPane root = new GridPane();
            root.setHgap(8);
            root.setVgap(8);
            root.setPadding(new Insets(5));

            Scene scene = new Scene(root, 400, 300);

            // Crear las etiquetas y los campos de texto
            Label tituloLbl = new Label (TITULO_LBL);
            
            Label usuarioLbl = new Label(USUARIO_LBL);
            fieldUsuario = new TextField();

            Label contrasenhaLbl = new Label(PASSWORD_LBL);
            fieldContrasenha = new TextField();

            // Crear el botón de login
            botonLogin = new Button(BUTTON_LBL);
            botonLogin.setOnAction(event -> {
                //Creo un usuario
            	String name = fieldUsuario.getText();
                String password = fieldContrasenha.getText();
                Usuario usuario = new Usuario (name,password);
                
                //Creo un controller y le paso el usuario que he creado
                UsuarioController controller = new UsuarioController (usuario,this); 
                
                //Hago que el controller que acabo de crear llame al metodo que 
                //comprueba las credenciales y le paso esta escena para sobreescribirle.
                controller.checkCredencialesAcceso(primaryStage);
            });

            // Añadir elementos al GridPane
            root.add(tituloLbl, 0, 0, 2, 1);
            root.add(usuarioLbl, 0, 1);
            root.add(fieldUsuario, 1, 1);
            root.add(contrasenhaLbl, 0, 2);
            root.add(fieldContrasenha, 1, 2);
            root.add(botonLogin, 1, 3);

            // Aplicar el archivo CSS
            scene.getStylesheets().add(getClass().getResource(PATH_CSS).toExternalForm());

            primaryStage.setTitle(TITLE_FORM_LBL);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    

    
}
