package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();

			// Parte superior: formulario
			GridPane formulario = new GridPane();
			formulario.setVgap(10);
			formulario.setHgap(10);

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
			Button agregar = new Button("Agregar");

			formulario.add(titulo, 0, 0, 4, 1);
			formulario.add(nombre, 0, 1);
			formulario.add(cantidad, 1, 1);
			formulario.add(gasto, 2, 1);
			formulario.add(ingreso, 3, 1);
			formulario.add(meta, 0, 2, 1, 2);
			formulario.add(calcular, 1, 2);
			formulario.add(agregar, 2, 2);

			// Tabla para mostrar los datos
			TableView<FinanceEntry> table = new TableView<>();
			TableColumn<FinanceEntry, String> nameColumn = new TableColumn<>("Nombre");
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

			TableColumn<FinanceEntry, Double> amountColumn = new TableColumn<>("Cantidad");
			amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

			TableColumn<FinanceEntry, String> typeColumn = new TableColumn<>("Tipo");
			typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

			table.getColumns().addAll(nameColumn, amountColumn, typeColumn);

			// Lista observable para la tabla
			ObservableList<FinanceEntry> financeData = FXCollections.observableArrayList();
			table.setItems(financeData);

			// Crear un contenedor para el PieChart
			VBox pieChartContainer = new VBox();
			pieChartContainer.setVisible(false); // Inicialmente invisible

			// A�adir funcionalidad al bot�n "Agregar"
			agregar.setOnAction(event -> {
				String entryName = nombre.getText();
				Double entryAmount = Double.parseDouble(cantidad.getText());
				String entryType = gasto.isSelected() ? "Gasto" : "Ingreso";

				// Agregar la nueva entrada a la lista de finanzas
				financeData.add(new FinanceEntry(entryName, entryAmount, entryType));

				// Calcular los totales de ingresos y gastos
				double totalIngresos = 0;
				double totalGastos = 0;

				for (FinanceEntry entry : financeData) {
					if (entry.getType().equals("Ingreso")) {
						totalIngresos += entry.getAmount();
					} else if (entry.getType().equals("Gasto")) {
						totalGastos += entry.getAmount();
					}
				}

				// Calcular el balance
				double balance = totalIngresos - totalGastos;

				// Crear el PieChart solo si hay entradas
				if (!pieChartContainer.isVisible()) {

					if (balance < 0) {
						ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
								new PieChart.Data("Ingresos: " + totalIngresos + " �", totalIngresos),
								new PieChart.Data("Gastos: " + totalGastos + " �", totalGastos),
								new PieChart.Data("Balance: -" + Math.abs(balance) + " �", Math.abs(balance)) 

						);

						// Crear el PieChart
						PieChart pieChart = new PieChart(pieChartData);
						pieChart.setTitle("Distribuci�n Financiera");
						pieChart.setLegendVisible(false);

						// Establecer el color del balance seg�n su valor
						setBalanceColor(pieChartData.get(2), balance); // Establece el color del balance

						pieChartContainer.getChildren().add(pieChart);
						pieChartContainer.setVisible(true); // Hacer visible el contenedor del PieChart

					} else {

						ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
								new PieChart.Data("Ingresos: " + totalIngresos + " �", totalIngresos),
								new PieChart.Data("Gastos: " + totalGastos + " �", totalGastos),
								new PieChart.Data("Balance: " + Math.abs(balance) + " �", Math.abs(balance)) 

						);
						// Crear el PieChart
						PieChart pieChart = new PieChart(pieChartData);
						pieChart.setTitle("Distribuci�n Financiera");
						pieChart.setLegendVisible(false);

						// Establecer el color del balance seg�n su valor
						setBalanceColor(pieChartData.get(2), balance); // Establece el color del balance

						pieChartContainer.getChildren().add(pieChart);
						pieChartContainer.setVisible(true); // Hacer visible el contenedor del PieChart

					}

				} else {
					// Actualizar los datos del PieChart si ya existe
					PieChart pieChart = (PieChart) pieChartContainer.getChildren().get(0);
					pieChart.getData().set(0, new PieChart.Data("Ingresos: " + totalIngresos + " �", totalIngresos));
					pieChart.getData().set(1, new PieChart.Data("Gastos: " + totalGastos + " �", totalGastos));
					if (balance < 0) {
						pieChart.getData().set(2,
								new PieChart.Data("Balance: -" + Math.abs(balance) + " �", Math.abs(balance)));
					} else {
						pieChart.getData().set(2,
								new PieChart.Data("Balance: " + Math.abs(balance) + " �", Math.abs(balance)));
					}

					// Establecer el color del balance seg�n su valor
					setBalanceColor(pieChart.getData().get(2), balance); // Establece el color del balance
				}

				// Limpiar los campos despu�s de agregar
				nombre.clear();
				cantidad.clear();
				botones.selectToggle(null);
			});

			// Organizar los elementos en un HBox
			HBox contenidoInferior = new HBox();
			contenidoInferior.setSpacing(20);
			contenidoInferior.getChildren().addAll(table, pieChartContainer);

			// A�adir los componentes al BorderPane
			root.setTop(formulario);
			root.setCenter(new Separator());
			root.setBottom(contenidoInferior);

			// Crear la escena
			Scene scene = new Scene(root, 1000, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Calculadora de Finanzas"); // T�tulo de la ventana
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// M�todo para establecer el color del segmento de balance
	private void setBalanceColor(PieChart.Data data, Double balance) {
		data.getNode().setStyle("-fx-pie-color: red;");

		if (balance >= 0) {
			data.getNode().setStyle("-fx-pie-color: green;");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
