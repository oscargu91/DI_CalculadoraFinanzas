module CalculadoraFinanzas {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens com.carballeira.view to javafx.graphics, javafx.fxml, javafx.base;
	opens com.carballeira.model to javafx.graphics, javafx.fxml, javafx.base;

}
