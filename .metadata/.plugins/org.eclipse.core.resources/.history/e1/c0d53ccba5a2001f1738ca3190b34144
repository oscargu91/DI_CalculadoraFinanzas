package com.carballeira.view;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	
	FinanceController controller = new FinanceController(this);
	LoginController controllerL = new LoginController(this);
	

    @Override
    public void start(Stage primaryStage) {
        mostrarPantallaLogin(primaryStage);
    }
    //metodo para inicializar la pantalla de login
    private void mostrarPantallaLogin(Stage primaryStage) {
        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setTitle("Inicio de Sesión");

        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setVgap(10);
        loginLayout.setHgap(10);

        Label user = new Label("Usuario: ");
        TextField campoUsuario = new TextField();
        campoUsuario.setPromptText("Ingrese su usuario");

        Label pwd = new Label("Contraseña: ");
        PasswordField campoContrasena = new PasswordField();
        campoContrasena.setPromptText("Ingrese su contraseña");

        Button botonIniciarSesion = new Button("Iniciar Sesión");
        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(event -> loginStage.close());

        // Crear un ProgressIndicator (ícono de carga)
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false); // Oculto por defecto

        // Crear un Label para el texto de "Cargando..."
        Label loadingLabel = new Label("Cargando");
        loadingLabel.setVisible(false); // Oculto por defecto

        // Crear un Timeline para animar el texto "Cargando..."
        Timeline loadingAnimation = new Timeline(
            new KeyFrame(Duration.ZERO, e -> loadingLabel.setText("Cargando")),
            new KeyFrame(Duration.seconds(0.5), e -> loadingLabel.setText("Cargando.")),
            new KeyFrame(Duration.seconds(1), e -> loadingLabel.setText("Cargando..")),
            new KeyFrame(Duration.seconds(1.5), e -> loadingLabel.setText("Cargando...")),
            new KeyFrame(Duration.seconds(2), e -> loadingLabel.setText("Cargando...."))
        );
        loadingAnimation.setCycleCount(Timeline.INDEFINITE);

        // Configurar acción del botón de inicio de sesión
        botonIniciarSesion.setOnAction(event -> {
            // Mostrar el indicador de carga y el texto de "Cargando..."
            progressIndicator.setVisible(true);
            loadingLabel.setVisible(true);
            loadingAnimation.play(); // Iniciar la animación de "Cargando..."

            // Crear un nuevo hilo para simular la espera
            new Thread(() -> {
                try {
                    // Simulación de un tiempo de descarga (4 segundos)
                    Thread.sleep(4000);
                    
                    // Ejecutar la lógica de inicio de sesión en el hilo de la interfaz gráfica
                    javafx.application.Platform.runLater(() -> {
                        progressIndicator.setVisible(false); // Ocultar el indicador de carga
                        loadingLabel.setVisible(false); // Ocultar el texto de "Cargando..."
                        loadingAnimation.stop(); // Detener la animación
                        controllerL.manejarInicioSesion(this, loginStage, campoUsuario.getText(), campoContrasena.getText(), primaryStage);
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
        loginLayout.add(progressIndicator, 1, 3); // Añadir el indicador de carga en la interfaz
        loginLayout.add(loadingLabel, 0, 3); // Añadir el texto de carga en la interfaz

        // Métodos para captar la pulsación de Enter y llamar al botón de iniciar sesión
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
        loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        loginStage.setScene(loginScene);
        loginStage.show();
    }
    
    //metodo para lanzar la pantalla principal si el login es correcto
    public void mostrarPantallaPrincipal(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
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
            agregar.setOnAction(event -> controller.agregarDatos(nombre, cantidad, gasto, ingreso, financeData, pieChartContainer, botones));

            HBox contenidoInferior = new HBox();
            contenidoInferior.setSpacing(20);
            contenidoInferior.getChildren().addAll(table, pieChartContainer);

            root.setTop(formulario);
            root.setCenter(new Separator());
            root.setBottom(contenidoInferior);

            Scene scene = new Scene(root, 840, 700);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //metodo para crear la tabla de datos
    private TableView<FinanceEntry> crearTablaFinance() {
        TableView<FinanceEntry> table = new TableView<>();
        TableColumn<FinanceEntry, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nombreCol.setPrefWidth(60);
        nombreCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<FinanceEntry, Double> cantidadCol = new TableColumn<>("Cantidad");
        cantidadCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        cantidadCol.setPrefWidth(60);
        cantidadCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<FinanceEntry, String> tipoCol = new TableColumn<>("Tipo");
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        tipoCol.setPrefWidth(60);
        tipoCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<FinanceEntry, Button> eliminarCol = new TableColumn<>("Acción");
        eliminarCol.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        eliminarCol.setPrefWidth(60);
        eliminarCol.setStyle("-fx-alignment: CENTER;");

        table.getColumns().addAll(nombreCol, cantidadCol, tipoCol, eliminarCol);
        table.setPrefWidth(250);
        return table;
    }
    
    //metodo para rellenar la tabla con la informacion del modelo FinanceEntry
    public void agregarEntrada(TextField nombre, TextField cantidad, RadioButton gasto, RadioButton ingreso, ObservableList<FinanceEntry> financeData, VBox pieChartContainer, ToggleGroup botones) {
        String entryName = nombre.getText();
        String entryAmountStr = cantidad.getText();
        String entryType = gasto.isSelected() ? "Gasto" : ingreso.isSelected() ? "Ingreso" : null;

        try {
            // Convertimos la cantidad de String a Double
            Double entryAmount = Double.parseDouble(entryAmountStr);
            // Creamos el botón de eliminación para la entrada
            Button deleteButton = crearBotonEliminar(entryName, entryAmount, financeData, pieChartContainer);

            // Agregamos la nueva entrada a los datos financieros
            financeData.add(new FinanceEntry(entryName, entryAmount, entryType, deleteButton));

            // Actualizamos el balance y el gráfico
            actualizarBalanceYGrafico(financeData, pieChartContainer);

            // Limpiamos los campos después de agregar la entrada
            nombre.clear();
            cantidad.clear();
            botones.selectToggle(null);
        } catch (IllegalArgumentException e) {
            // Si hay un error con el formato de la cantidad, mostramos un mensaje de error
            showAlert("Error", "Cantidad inválida.");
        }
    }
    
    //metodo para añadir el boton de eliminar en la tabla, asociado a la fila correspondiente
    private Button crearBotonEliminar(String entryName, double entryAmount, ObservableList<FinanceEntry> financeData, VBox pieChartContainer) {
        Button deleteButton = new Button();
        Image papeleraIcon = new Image(getClass().getResourceAsStream("papelera_icon.png"));
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
    
    //metodo secundario usado por actualizarPiechart() para actualizar el gráfico
    private void actualizarBalanceYGrafico(ObservableList<FinanceEntry> financeData, VBox pieChartContainer) {
        double totalIngresos = financeData.stream().filter(e -> e.getType().equals("Ingreso")).mapToDouble(FinanceEntry::getAmount).sum();
        double totalGastos = financeData.stream().filter(e -> e.getType().equals("Gasto")).mapToDouble(FinanceEntry::getAmount).sum();
        double balance = totalIngresos - totalGastos;

        actualizarPieChart(pieChartContainer, totalIngresos, totalGastos, balance);
    }
    
    //metodo que actualiza el gráfico con ingresos gastos y balance
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
    
    //metodo para inicializar el piechart
    private PieChart crearPieChart(double totalIngresos, double totalGastos, double balance) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Ingresos: " + totalIngresos + " €", totalIngresos),
                new PieChart.Data("Gastos: " + totalGastos + " €", totalGastos),
                new PieChart.Data("Balance: " + balance + " €", Math.abs(balance))
        );
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Distribución Financiera");
        pieChart.setLegendVisible(false);
        setBalanceColor(pieChartData.get(2), balance);
        return pieChart;
    }
   
    //metodo primario para actualizar los datos del piechart
    private void actualizarDatosPieChart(PieChart pieChart, double totalIngresos, double totalGastos, double balance) {
        pieChart.getData().set(0, new PieChart.Data("Ingresos: " + totalIngresos + " €", totalIngresos));
        pieChart.getData().set(1, new PieChart.Data("Gastos: " + totalGastos + " €", totalGastos));
        pieChart.getData().set(2, new PieChart.Data("Balance: " + balance + " €", Math.abs(balance)));
        setBalanceColor(pieChart.getData().get(2), balance);
    }
    
    //metodo para calcular la diferencia entre el balance y la cantidad introducida por el usuario
    private void calcularMeta(TextField meta, Label resultadoMeta, ObservableList<FinanceEntry> financeData) {
        try {
            double metaValue = Double.parseDouble(meta.getText());
            double balance = calcularBalance(financeData);

            if (balance < metaValue) {
                resultadoMeta.setText("Le faltan " + (metaValue - balance) + " € para llegar a la meta.");
            } else {
                resultadoMeta.setText("Meta superada por " + (balance - metaValue) + " €.");
            }
        } catch (NumberFormatException e) {
            showAlert("Valor Inválido", "Debe introducir un número válido para la meta financiera.");
        }
    }
    
    //metodo para calcular el balance actual (ingresos - gastos)
    private double calcularBalance(ObservableList<FinanceEntry> financeData) {
        double totalIngresos = financeData.stream().filter(e -> e.getType().equals("Ingreso")).mapToDouble(FinanceEntry::getAmount).sum();
        double totalGastos = financeData.stream().filter(e -> e.getType().equals("Gasto")).mapToDouble(FinanceEntry::getAmount).sum();
        return totalIngresos - totalGastos;
    }
    
    //metodo para colorear el grafico segun si el balance es positivo (verde) o negativo (rojo)
    private void setBalanceColor(PieChart.Data data, double balance) {
        if (balance >= 0) {
            data.getNode().setStyle("-fx-pie-color: #66BB6A;");
        } else {
            data.getNode().setStyle("-fx-pie-color: #EF5350;");
        }
    }
    
    //metodo de creacion de las alertas
    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    //metodo de inicio de la aplicacion
    public static void main(String[] args) {
        launch(args);
    }
}
