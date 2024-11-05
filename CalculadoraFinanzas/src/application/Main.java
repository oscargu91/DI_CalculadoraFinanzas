package application;

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

    @Override
    public void start(Stage primaryStage) {
        mostrarPantallaLogin(primaryStage);
    }

    private void mostrarPantallaLogin(Stage primaryStage) {
        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setTitle("Inicio de Sesi�n");

        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setVgap(10);
        loginLayout.setHgap(10);

        Label user = new Label("Usuario: ");
        TextField campoUsuario = new TextField();
        campoUsuario.setPromptText("Ingrese su usuario");

        Label pwd = new Label("Contrase�a: ");
        PasswordField campoContrasena = new PasswordField();
        campoContrasena.setPromptText("Ingrese su contrase�a");

        Button botonIniciarSesion = new Button("Iniciar Sesi�n");
        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(event -> loginStage.close());

        botonIniciarSesion.setOnAction(event -> {
            String usuario = campoUsuario.getText();
            String contrasena = campoContrasena.getText();

            if (!usuario.isEmpty() && !contrasena.isEmpty()) {
                loginStage.close();
                mostrarPantallaPrincipal(primaryStage);
            } else {
                showAlert("Error", "Debe rellenar todos los campos para iniciar sesi�n.");
            }
        });

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

    private void mostrarPantallaPrincipal(Stage primaryStage) {
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

            agregar.setOnAction(event -> agregarEntrada(nombre, cantidad, gasto, ingreso, financeData, pieChartContainer, botones));

            calcular.setOnAction(event -> calcularMeta(meta, resultadoMeta, financeData));

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

        // Definir columnas
        TableColumn<FinanceEntry, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(60); // Ajustar ancho de columna
        nameColumn.setStyle("-fx-alignment: CENTER;"); // Centrar texto en la columna

        TableColumn<FinanceEntry, Double> amountColumn = new TableColumn<>("Cantidad");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountColumn.setPrefWidth(60); // Ajustar ancho de columna
        amountColumn.setStyle("-fx-alignment: CENTER;"); // Centrar texto en la columna

        TableColumn<FinanceEntry, String> typeColumn = new TableColumn<>("Tipo");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setPrefWidth(60); // Ajustar ancho de columna
        typeColumn.setStyle("-fx-alignment: CENTER;"); // Centrar texto en la columna

        TableColumn<FinanceEntry, Button> actionColumn = new TableColumn<>("Acci�n");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        actionColumn.setPrefWidth(60); // Ajustar ancho de columna
        actionColumn.setStyle("-fx-alignment: CENTER;"); 

        // Agregar columnas a la tabla
        table.getColumns().addAll(nameColumn, amountColumn, typeColumn, actionColumn);
        table.setPrefWidth(250); // Ajustar el ancho total de la tabla

        return table;
    }



    private void agregarEntrada(TextField nombre, TextField cantidad, RadioButton gasto, RadioButton ingreso,
                                ObservableList<FinanceEntry> financeData, VBox pieChartContainer, ToggleGroup botones) {
        String entryName = nombre.getText();
        String entryAmountStr = cantidad.getText();
        String entryType = gasto.isSelected() ? "Gasto" : ingreso.isSelected() ? "Ingreso" : null;

        try {
            validarEntrada(entryName, entryAmountStr, entryType);
            Double entryAmount = Double.parseDouble(entryAmountStr);

            // Crear el bot�n de eliminar con el �cono de papelera
            Button deleteButton = new Button();
            Image papeleraIcon = new Image(getClass().getResourceAsStream("papelera_icon.png"));
            ImageView papeleraImageView = new ImageView(papeleraIcon);
            papeleraImageView.setFitWidth(20); // Ajustar ancho del �cono
            papeleraImageView.setFitHeight(20); // Ajustar alto del �cono
            deleteButton.setGraphic(papeleraImageView); // Asignar el �cono al bot�n
            deleteButton.setStyle("-fx-background-color: transparent;"); // Hacer el fondo del bot�n transparente
            deleteButton.setOnAction(event -> {
                financeData.removeIf(entry -> entry.getName().equals(entryName) && entry.getAmount() == entryAmount);
                actualizarBalanceYGrafico(financeData, pieChartContainer);
            });

            // Crear una nueva entrada
            FinanceEntry entry = new FinanceEntry(entryName, entryAmount, entryType, deleteButton);

            financeData.add(entry);
            actualizarBalanceYGrafico(financeData, pieChartContainer);

            nombre.clear();
            cantidad.clear();
            botones.selectToggle(null);
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        }
    }
    private void validarEntrada(String nombre, String cantidad, String tipo) {
        if (nombre.isEmpty()) throw new IllegalArgumentException("El campo del nombre no puede estar vac�o.");
        if (cantidad.isEmpty()) throw new IllegalArgumentException("El campo de cantidad no puede estar vac�o.");
        if (tipo == null) throw new IllegalArgumentException("Debe seleccionar al menos un tipo: Gasto o Ingreso.");

        try {
            Double.parseDouble(cantidad);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Debe introducir un n�mero v�lido para la cantidad.");
        }
    }

    private void actualizarBalanceYGrafico(ObservableList<FinanceEntry> financeData, VBox pieChartContainer) {
        double totalIngresos = financeData.stream().filter(e -> e.getType().equals("Ingreso")).mapToDouble(FinanceEntry::getAmount).sum();
        double totalGastos = financeData.stream().filter(e -> e.getType().equals("Gasto")).mapToDouble(FinanceEntry::getAmount).sum();
        double balance = totalIngresos - totalGastos;

        if (!pieChartContainer.isVisible()) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Ingresos: " + totalIngresos + " �", totalIngresos),
                    new PieChart.Data("Gastos: " + totalGastos + " �", totalGastos),
                    new PieChart.Data("Balance: " + balance + " �", Math.abs(balance))
            );
            PieChart pieChart = new PieChart(pieChartData);
            pieChart.setTitle("Distribuci�n Financiera");
            pieChart.setLegendVisible(false);
            setBalanceColor(pieChartData.get(2), balance);

            pieChartContainer.getChildren().add(pieChart);
            pieChartContainer.setVisible(true);
        } else {
            PieChart pieChart = (PieChart) pieChartContainer.getChildren().get(0);
            pieChart.getData().set(0, new PieChart.Data("Ingresos: " + totalIngresos + " �", totalIngresos));
            pieChart.getData().set(1, new PieChart.Data("Gastos: " + totalGastos + " �", totalGastos));
            pieChart.getData().set(2, new PieChart.Data("Balance: " + balance + " �", Math.abs(balance)));
            setBalanceColor(pieChart.getData().get(2), balance);
        }
    }

    private void calcularMeta(TextField meta, Label resultadoMeta, ObservableList<FinanceEntry> financeData) {
        try {
            double metaValue = Double.parseDouble(meta.getText());
            double totalIngresos = financeData.stream().filter(e -> e.getType().equals("Ingreso")).mapToDouble(FinanceEntry::getAmount).sum();
            double totalGastos = financeData.stream().filter(e -> e.getType().equals("Gasto")).mapToDouble(FinanceEntry::getAmount).sum();
            double balance = totalIngresos - totalGastos;

            if (balance < metaValue) {
                resultadoMeta.setText("Le faltan " + (metaValue - balance) + " � para llegar a la meta.");
            } else {
                resultadoMeta.setText("Meta superada por " + (balance - metaValue) + " �.");
            }
        } catch (NumberFormatException e) {
            showAlert("Valor Inv�lido", "Debe introducir un n�mero v�lido para la meta financiera.");
        }
    }

    private void setBalanceColor(PieChart.Data data, double balance) {
        if (balance >= 0) {
            data.getNode().setStyle("-fx-pie-color: #66BB6A;");
        } else {
            data.getNode().setStyle("-fx-pie-color: #EF5350;");
        }
    }

    private void showAlert(String title, String content) {
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
