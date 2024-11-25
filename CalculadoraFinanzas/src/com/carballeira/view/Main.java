package com.carballeira.view;

import com.carballeira.controller.AlertaController;
import com.carballeira.controller.FinanceController;
import com.carballeira.controller.LoginController;
import com.carballeira.model.FinanceEntry;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	FinanceController controller = new FinanceController(this);
	LoginController controllerL = new LoginController(this);

	private TextField nombre = new TextField();
	private TextField cantidad = new TextField();

	public static final String ERROR = "Error";
	public static final String INICIO_SESION = "Inicio de Sesi�n";
	public static final String USUARIO = "Usuario: ";
	public static final String INGRESE_USUARIO = "Ingrese su usuario";
	public static final String CONTRASENA = "Password: ";
	public static final String INGRESE_PASSWORD = "Ingrese su password";
	public static final String INICIAR_SESION = "Iniciar Sesi�n";
	public static final String SALIR = "Salir";
	public static final String CARGANDO1 = "Cargando";
	public static final String CARGANDO2 = "Cargando.";
	public static final String CARGANDO3 = "Cargando..";
	public static final String CARGANDO4 = "Cargando...";
	public static final String CARGANDO5 = "Cargando....";
	public static final String CALCULADORA_FINANZAS = "Calculadora de Finanzas";
	public static final String NOMBRE_GASTO_INGRESO = "Nombre del gasto/ingreso";
	public static final String CANTIDAD = "Cantidad";
	public static final String META_FINANCIERA = "Meta Financiera";
	public static final String CALCULAR = "Calcular";
	public static final String AGREGAR = "Agregar";
	public static final String NAME = "Nombre";
	public static final String TIPO = "Tipo";
	public static final String ACCION = "Acci�n";
	public static final String GASTO = "Gasto";
	public static final String INGRESO = "Ingreso";
	public static final String GASTOS = "Gastos: ";
	public static final String INGRESOS = "Ingresos: ";
	public static final String BALANCE = "Balance: ";
	public static final String CANTIDAD_INVALIDA = "Cantidad Inv�lida.";
	public static final String EURO = "�";
	public static final String RUTA_CSS = "application.css";
	public static final String RUTA_PAPELERA = "papelera_icon.png";
	public static final String DISTRIBUCION_FINANCIERA = "Distribuci�n Financiera";
	public static final String LE_FALTAN = "Le faltan ";
	public static final String EUROS_PARA_META = " � para llegar a la meta.";
	public static final String META_SUPERADA = "Meta superada por ";
	public static final String EURO2 = " �.";
	public static final String VALOR_INVALIDO = "Valor Inv�lido";
	public static final String ERROR_VALOR_INVALIDO = "Debe introducir un n�mero v�lido para la meta financiera.";
	public static final String ERROR_VALOR_NEGATIVO = "Debe introducir un n�mero positivo para la meta financiera.";

	@Override
	public void start(Stage primaryStage) {
		mostrarPantallaLogin(primaryStage);
	}

	// metodo para inicializar la pantalla de login
	private void mostrarPantallaLogin(Stage primaryStage) {
		Stage loginStage = new Stage();
		loginStage.initModality(Modality.APPLICATION_MODAL);
		loginStage.setTitle(INICIO_SESION);

		GridPane loginLayout = new GridPane();
		loginLayout.setAlignment(Pos.CENTER);
		loginLayout.setVgap(10);
		loginLayout.setHgap(10);

		Label user = new Label(USUARIO);
		TextField campoUsuario = new TextField();
		campoUsuario.setPromptText(INGRESE_USUARIO);

		Label pwd = new Label(CONTRASENA);
		PasswordField campoContrasena = new PasswordField();
		campoContrasena.setPromptText(INGRESE_PASSWORD);

		Button botonIniciarSesion = new Button(INICIAR_SESION);
		Button botonSalir = new Button(SALIR);
		botonSalir.setOnAction(event -> loginStage.close());

		// Crear un ProgressIndicator (�cono de carga)
		ProgressIndicator progressIndicator = new ProgressIndicator();
		progressIndicator.setVisible(false); // Oculto por defecto

		// Crear un Label para el texto de "Cargando..."
		Label loadingLabel = new Label(CARGANDO1);
		loadingLabel.setVisible(false); // Oculto por defecto

		// Crear un Timeline para animar el texto "Cargando..."
		Timeline loadingAnimation = new Timeline(new KeyFrame(Duration.ZERO, e -> loadingLabel.setText(CARGANDO1)),
				new KeyFrame(Duration.seconds(0.5), e -> loadingLabel.setText(CARGANDO2)),
				new KeyFrame(Duration.seconds(1), e -> loadingLabel.setText(CARGANDO3)),
				new KeyFrame(Duration.seconds(1.5), e -> loadingLabel.setText(CARGANDO4)),
				new KeyFrame(Duration.seconds(2), e -> loadingLabel.setText(CARGANDO5)));
		loadingAnimation.setCycleCount(Timeline.INDEFINITE);

		// Configurar acci�n del bot�n de inicio de sesi�n
		botonIniciarSesion.setOnAction(event -> {
			// Mostrar el indicador de carga y el texto de "Cargando..."
			progressIndicator.setVisible(true);
			loadingLabel.setVisible(true);
			loadingAnimation.play(); // Iniciar la animaci�n de "Cargando..."

			// Crear un nuevo hilo para simular la espera
			new Thread(() -> {
				try {
					// Simulaci�n de un tiempo de descarga (4 segundos)
					Thread.sleep(4000);

					// Ejecutar la l�gica de inicio de sesi�n en el hilo de la interfaz gr�fica
					javafx.application.Platform.runLater(() -> {
						progressIndicator.setVisible(false); // Ocultar el indicador de carga
						loadingLabel.setVisible(false); // Ocultar el texto de "Cargando..."
						loadingAnimation.stop(); // Detener la animaci�n
						controllerL.manejarInicioSesion(this, loginStage, campoUsuario.getText(),
								campoContrasena.getText(), primaryStage);
					});

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		});

		loginLayout.add(user, 0, 0);
		loginLayout.add(campoUsuario, 1, 0);
		loginLayout.add(pwd, 0, 1);
		loginLayout.add(campoContrasena, 1, 1);
		loginLayout.add(botonIniciarSesion, 0, 2);
		loginLayout.add(botonSalir, 1, 2);
		loginLayout.add(progressIndicator, 1, 3); // A�adir el indicador de carga en la interfaz
		loginLayout.add(loadingLabel, 0, 3); // A�adir el texto de carga en la interfaz

		// M�todos para captar la pulsaci�n de Enter y llamar al bot�n de iniciar sesi�n
		campoUsuario.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
				botonIniciarSesion.fire();
			}
		});

		campoContrasena.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
				botonIniciarSesion.fire();
			}
		});

		Scene loginScene = new Scene(loginLayout, 600, 300);
		loginScene.getStylesheets().add(getClass().getResource(RUTA_CSS).toExternalForm());
		loginStage.setScene(loginScene);
		loginStage.show();
	}

	// metodo para lanzar la pantalla principal si el login es correcto
	public void mostrarPantallaPrincipal(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			GridPane formulario = new GridPane();
			formulario.setVgap(10);
			formulario.setHgap(10);

			Label titulo = new Label(CALCULADORA_FINANZAS);
			TextField nombre = new TextField();
			nombre.setPromptText(NOMBRE_GASTO_INGRESO);
			TextField cantidad = new TextField();
			cantidad.setPromptText(CANTIDAD);
			TextField meta = new TextField();
			meta.setPromptText(META_FINANCIERA);

			ToggleGroup botones = new ToggleGroup();
			RadioButton gasto = new RadioButton(GASTO);
			RadioButton ingreso = new RadioButton(INGRESO);
			gasto.setToggleGroup(botones);
			ingreso.setToggleGroup(botones);

			Button calcular = new Button(CALCULAR);
			Button agregar = new Button(AGREGAR);
			Label resultadoMeta = new Label();

			formulario.add(titulo, 0, 0, 4, 1);
			formulario.add(nombre, 0, 1);
			formulario.add(cantidad, 1, 1);
			formulario.add(gasto, 2, 1);
			formulario.add(ingreso, 3, 1);
			formulario.add(meta, 0, 2, 1, 2);
			formulario.add(calcular, 1, 2);
			formulario.add(agregar, 4, 1);
			formulario.add(resultadoMeta, 0, 4, 2, 3);

			TableView<FinanceEntry> table = crearTablaFinance();
			ObservableList<FinanceEntry> financeData = FXCollections.observableArrayList();
			table.setItems(financeData);

			VBox pieChartContainer = new VBox();
			pieChartContainer.setVisible(false);

			calcular.setOnAction(event -> calcularMeta(meta, resultadoMeta, financeData));
			agregar.setOnAction(event -> {
				String nombre1 = nombre.getText();
				String cantidad1 = cantidad.getText();
				String gasto1 = gasto.isSelected() ? GASTO : ingreso.isSelected() ? INGRESO : null;

				FinanceEntry modelo = new FinanceEntry(nombre1, cantidad1, gasto1);
				controller.setModel(modelo);

				controller.agregarDatos(financeData, pieChartContainer, botones);
				nombre.clear();
				cantidad.clear();
				botones.selectToggle(null);
			});

			HBox contenidoInferior = new HBox();
			contenidoInferior.setSpacing(20);
			contenidoInferior.getChildren().addAll(table, pieChartContainer);

			root.setTop(formulario);
			root.setCenter(new Separator());
			root.setBottom(contenidoInferior);

			Scene scene = new Scene(root, 840, 700);
			scene.getStylesheets().add(getClass().getResource(RUTA_CSS).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// metodo para crear la tabla de datos
	private TableView<FinanceEntry> crearTablaFinance() {
		TableView<FinanceEntry> table = new TableView<>();
		TableColumn<FinanceEntry, String> nombreCol = new TableColumn<>(NAME);
		nombreCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nombreCol.setPrefWidth(60);
		nombreCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<FinanceEntry, Double> cantidadCol = new TableColumn<>(CANTIDAD);
		cantidadCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		cantidadCol.setPrefWidth(60);
		cantidadCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<FinanceEntry, String> tipoCol = new TableColumn<>(TIPO);
		tipoCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		tipoCol.setPrefWidth(60);
		tipoCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<FinanceEntry, Button> eliminarCol = new TableColumn<>(ACCION);
		eliminarCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
		eliminarCol.setPrefWidth(60);
		eliminarCol.setStyle("-fx-alignment: CENTER;");

		table.getColumns().addAll(nombreCol, cantidadCol, tipoCol, eliminarCol);
		table.setPrefWidth(250);
		return table;
	}

	// metodo para rellenar la tabla con la informacion del modelo FinanceEntry
	public void agregarEntrada(FinanceEntry model, ObservableList<FinanceEntry> financeData, VBox pieChartContainer,
			ToggleGroup botones) {
		String entryName = model.getName();
		String entryAmountStr = String.valueOf(model.getAmount());
		String entryType = model.getType();
 
		try {

			// Creamos el bot�n de eliminaci�n para la entrada
			Button deleteButton = crearBotonEliminar(entryName, entryAmountStr, financeData, pieChartContainer);

			// Agregamos la nueva entrada a los datos financieros
			financeData.add(new FinanceEntry(entryName, entryAmountStr, entryType, deleteButton));

			// Actualizamos el balance y el gr�fico
			actualizarBalanceYGrafico(financeData, pieChartContainer);

			// Limpiamos los campos despu�s de agregar la entrada
			nombre.clear();
			cantidad.clear();
			botones.selectToggle(null);
		} catch (IllegalArgumentException e) {
			// Si hay un error con el formato de la cantidad, mostramos un mensaje de error

			AlertaView vista2 = new AlertaView();
			AlertaController controladorAlerta = new AlertaController(vista2);
			controladorAlerta.crearAlerta(ERROR, CANTIDAD_INVALIDA);
		}
	}

	// metodo para a�adir el boton de eliminar en la tabla, asociado a la fila
	// correspondiente
	private Button crearBotonEliminar(String entryName, String entryAmount, ObservableList<FinanceEntry> financeData,
			VBox pieChartContainer) {
		Button deleteButton = new Button();
		Image papeleraIcon = new Image(getClass().getResourceAsStream(RUTA_PAPELERA));
		ImageView papeleraImageView = new ImageView(papeleraIcon);
		papeleraImageView.setFitWidth(20);
		papeleraImageView.setFitHeight(20);
		deleteButton.setGraphic(papeleraImageView);
		deleteButton.setStyle("-fx-background-color: transparent;");
		deleteButton.setOnAction(event -> {
			financeData.removeIf(entry -> entry.getName().equals(entryName) && entry.getAmount() == entryAmount);
			actualizarBalanceYGrafico(financeData, pieChartContainer);	
		});
		return deleteButton;
	}

	// metodo secundario usado por actualizarPiechart() para actualizar el gr�fico
	private void actualizarBalanceYGrafico(ObservableList<FinanceEntry> financeData, VBox pieChartContainer) {
		double totalIngresos = financeData.stream().filter(e -> e.getType().equals(INGRESO))
				.mapToDouble(e -> Double.parseDouble(e.getAmount())).sum();
		double totalGastos = financeData.stream().filter(e -> e.getType().equals(GASTO))
				.mapToDouble(e -> Double.parseDouble(e.getAmount())).sum();
		double balance = totalIngresos - totalGastos;

		actualizarPieChart(pieChartContainer, totalIngresos, totalGastos, balance);
	}

	// metodo que actualiza el gr�fico con ingresos gastos y balance
	private void actualizarPieChart(VBox pieChartContainer, double totalIngresos, double totalGastos, double balance) {
		if (!pieChartContainer.isVisible()) {
			PieChart pieChart = crearPieChart(totalIngresos, totalGastos, balance);
			pieChartContainer.getChildren().add(pieChart);
			pieChartContainer.setVisible(true);
		} else {
			PieChart pieChart = (PieChart) pieChartContainer.getChildren().get(0);
			actualizarDatosPieChart(pieChart, totalIngresos, totalGastos, balance);
		}
	}

	// metodo para inicializar el piechart
	private PieChart crearPieChart(double totalIngresos, double totalGastos, double balance) {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data(INGRESOS + totalIngresos + EURO, totalIngresos),
				new PieChart.Data(GASTOS + totalGastos + EURO, totalGastos),
				new PieChart.Data(BALANCE + balance + EURO, Math.abs(balance)));
		PieChart pieChart = new PieChart(pieChartData);
		pieChart.setTitle(DISTRIBUCION_FINANCIERA);
		pieChart.setLegendVisible(false);
		setBalanceColor(pieChartData.get(2), balance);
		return pieChart;
	}

	// metodo primario para actualizar los datos del piechart
	private void actualizarDatosPieChart(PieChart pieChart, double totalIngresos, double totalGastos, double balance) {
		pieChart.getData().set(0, new PieChart.Data(INGRESOS + totalIngresos + EURO, totalIngresos));
		pieChart.getData().set(1, new PieChart.Data(GASTOS + totalGastos + EURO, totalGastos));
		pieChart.getData().set(2, new PieChart.Data(BALANCE + balance + EURO, Math.abs(balance)));
		setBalanceColor(pieChart.getData().get(2), balance);
	}

	// metodo para calcular la diferencia entre el balance y la cantidad introducida
	// por el usuario
	private void calcularMeta(TextField meta, Label resultadoMeta, ObservableList<FinanceEntry> financeData) {
		try {
			double metaValue = Double.parseDouble(meta.getText());
			double balance = calcularBalance(financeData);

			if (balance < metaValue) {
				resultadoMeta.setText(LE_FALTAN + (metaValue - balance) + EUROS_PARA_META);
			} else if (metaValue < 0) {

				AlertaView vista2 = new AlertaView();
				AlertaController controladorAlerta = new AlertaController(vista2);
				controladorAlerta.crearAlerta(ERROR, ERROR_VALOR_NEGATIVO);
			} else {

				resultadoMeta.setText(META_SUPERADA + (balance - metaValue) + EURO2);
			}

		} catch (NumberFormatException e) {

			AlertaView vista2 = new AlertaView();
			AlertaController controladorAlerta = new AlertaController(vista2);
			controladorAlerta.crearAlerta(ERROR, ERROR_VALOR_INVALIDO);
		}
	}

	// metodo para calcular el balance actual (ingresos - gastos)
	private double calcularBalance(ObservableList<FinanceEntry> financeData) {
		double totalIngresos = financeData.stream().filter(e -> e.getType().equals(INGRESO))
				.mapToDouble(e -> Double.parseDouble(e.getAmount())).sum();
		double totalGastos = financeData.stream().filter(e -> e.getType().equals(GASTO))
				.mapToDouble(e -> Double.parseDouble(e.getAmount())).sum();
		return totalIngresos - totalGastos;
	}

	// metodo para colorear el grafico segun si el balance es positivo (verde) o
	// negativo (rojo)
	private void setBalanceColor(PieChart.Data data, double balance) {
		if (balance >= 0) {
			data.getNode().setStyle("-fx-pie-color: #66BB6A;");
		} else {
			data.getNode().setStyle("-fx-pie-color: #EF5350;");
		}
	}

	// metodo de inicio de la aplicacion
	public static void main(String[] args) {
		launch(args);
	}
}
