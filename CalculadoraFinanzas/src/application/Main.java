package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			
			GridPane formulario = new GridPane();
			
			Label titulo = new Label("Calculadora de finanzas");
			TextField nombre = new TextField();
			TextField cantidad = new TextField();
			TextField meta = new TextField();
			
			ToggleGroup botones = new ToggleGroup();
			
			RadioButton gasto = new RadioButton("Gasto");
			RadioButton ingreso = new RadioButton("Ingreso");
			
			Button calcular = new Button("Calcular");
			
			gasto.setToggleGroup(botones);
			ingreso.setToggleGroup(botones);
			
			formulario.add(titulo, 0, 0, 4, 1);
			formulario.add(nombre, 0, 1);
			formulario.add(cantidad, 1, 1);
			formulario.add(gasto, 2, 1);
			formulario.add(ingreso, 3, 1);
			formulario.add(meta, 0, 2, 1, 2);
			formulario.add(calcular, 2, 2);
			
			
			
			root.add(formulario, 0, 0);
			
			
			
			Scene scene = new Scene(root,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
