package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			
			GridPane formulario = new GridPane();
			
			Label titulo = new Label("Calculadora de finanzas");
			TextField nombre = new TextField();
			nombre.setPromptText("Nombre del gasto/ingreso");
			TextField cantidad = new TextField();
			cantidad.setPromptText("Cantidad");
			TextField meta = new TextField();
			meta.setPromptText("Meta financiera");
			
			ToggleGroup botones = new ToggleGroup();
			
			RadioButton gasto = new RadioButton("Gasto");
			RadioButton ingreso = new RadioButton("Ingreso");
			
						
			gasto.setToggleGroup(botones);
			ingreso.setToggleGroup(botones);
			
			Button calcular = new Button("Calcular");
			Button agregar = new Button ("Agregar");
			
			// Datos del PieChart
	        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
	            new PieChart.Data("Ahorros", 40),
	            new PieChart.Data("Inversiones", 50),
	            new PieChart.Data("Gastos", 10)
	        );

	        // Crear el PieChart
	        PieChart pieChart = new PieChart(pieChartData);
	        pieChart.setTitle("Distribución Financiera");

	       
			
	     // Contenedor para el PieChart
	        VBox contenedorGrafico = new VBox(pieChart);
	        
	        
			
			formulario.add(titulo, 0, 0, 4, 1);
			formulario.add(nombre, 0, 1);
			formulario.add(cantidad, 1, 1);
			formulario.add(gasto, 2, 1);
			formulario.add(ingreso, 3, 1);
			formulario.add(meta, 0, 2, 1, 2);
			formulario.add(calcular, 1, 2);
			formulario.add(agregar, 2, 2);
			formulario.add(contenedorGrafico, 0, 5);
			
			
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
