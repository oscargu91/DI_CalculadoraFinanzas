package com.carballeira.view;

import com.carballeira.controller.FinanceController;
import com.carballeira.controller.LoginController;
import com.carballeira.model.FinanceEntry;

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

public class Main extends Application {
	
	FinanceController controller = new FinanceController(this);
	LoginController controllerL = new LoginController(this);
	

    @Override
    public void start(Stage primaryStage) {
        mostrarPantallaLogin(primaryStage);
    }

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
        botonIniciarSesion.setOnAction(event -> controllerL.manejarInicioSesion(this,loginStage, campoUsuario.getText(), campoContrasena.getText(), primaryStage));

        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(event -> loginStage.close());

        loginLayout.add(user, 0, 0);
        loginLayout.add(campoUsuario, 1, 0);
        loginLayout.add(pwd, 0, 1);
        loginLayout.add(campoContrasena, 1, 1);
        loginLayout.add(botonIniciarSesion, 0, 2);
        loginLayout.add(botonSalir, 1, 2);

        Scene loginScene = new Scene(loginLayout, 600, 300);
        loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        loginStage.setScene(loginScene);
        loginStage.show();
        
        
    }



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


    private void actualizarBalanceYGrafico(ObservableList<FinanceEntry> financeData, VBox pieChartContainer) {
        double totalIngresos = financeData.stream().filter(e -> e.getType().equals("Ingreso")).mapToDouble(FinanceEntry::getAmount).sum();
        double totalGastos = financeData.stream().filter(e -> e.getType().equals("Gasto")).mapToDouble(FinanceEntry::getAmount).sum();
        double balance = totalIngresos - totalGastos;

        actualizarPieChart(pieChartContainer, totalIngresos, totalGastos, balance);
    }

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

    private void actualizarDatosPieChart(PieChart pieChart, double totalIngresos, double totalGastos, double balance) {
        pieChart.getData().set(0, new PieChart.Data("Ingresos: " + totalIngresos + " €", totalIngresos));
        pieChart.getData().set(1, new PieChart.Data("Gastos: " + totalGastos + " €", totalGastos));
        pieChart.getData().set(2, new PieChart.Data("Balance: " + balance + " €", Math.abs(balance)));
        setBalanceColor(pieChart.getData().get(2), balance);
    }

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

    private double calcularBalance(ObservableList<FinanceEntry> financeData) {
        double totalIngresos = financeData.stream().filter(e -> e.getType().equals("Ingreso")).mapToDouble(FinanceEntry::getAmount).sum();
        double totalGastos = financeData.stream().filter(e -> e.getType().equals("Gasto")).mapToDouble(FinanceEntry::getAmount).sum();
        return totalIngresos - totalGastos;
    }

    private void setBalanceColor(PieChart.Data data, double balance) {
        if (balance >= 0) {
            data.getNode().setStyle("-fx-pie-color: #66BB6A;");
        } else {
            data.getNode().setStyle("-fx-pie-color: #EF5350;");
        }
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
