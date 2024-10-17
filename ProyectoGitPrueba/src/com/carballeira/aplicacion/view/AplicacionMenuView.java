package com.carballeira.aplicacion.view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AplicacionMenuView {

	private final String MENUITEM_1="Archivo";
	private final String MENUITEM_11="Abrir";
	private final String MENUITEM_12="Cerrar";
	
	private final String LAYOUT_STYLE="layout";
	private final String MENUITEM_STYLE="menubar";
	
	private final String TITULO_MENU= "Barra de menú";
	
	private final String SMS_MENUITEM_11="Se seleccionó la opción Abrir";
	private final String SMS_MENUITEM_12="Se seleccionó la opción Cerrar";
	private final String PATH_CSS="/application/application.css";
	
	private final String PATH_IMAGEN_MENU_ARCHIVO = "/application/images/archivo_img.png";
	
	public void startMenuView(Stage primaryStage) {

        GridPane panel = new GridPane();
        
        Menu archivo = new Menu(MENUITEM_1);       
        MenuItem abrir = new MenuItem(MENUITEM_11);
        MenuItem cerrar = new MenuItem(MENUITEM_12);       
        archivo.getItems().addAll(abrir,cerrar);
        
        MenuBar menuBar = new MenuBar();
        
        menuBar.getMenus().add(archivo);
        
        panel.add(menuBar, 0, 0);
                
        
        // Crear la escena
        Scene scene = new Scene(panel, 700, 500);
   	    scene.getStylesheets().add(getClass().getResource(PATH_CSS).toExternalForm());
        
   	    primaryStage.setTitle(TITULO_MENU);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}

