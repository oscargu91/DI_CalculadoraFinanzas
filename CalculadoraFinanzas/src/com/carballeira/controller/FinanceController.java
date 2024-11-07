package com.carballeira.controller;

import com.carballeira.model.FinanceEntry;
import com.carballeira.view.Main;

import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class FinanceController {
	private FinanceEntry model;
	private Main vista;

	public FinanceController(FinanceEntry model, Main vista) {
		this.model = model;
		this.vista = vista;
	}

	public FinanceController(Main vista) {
		this.vista = vista;
	}

	
	
	
	public void agregarDatos(TextField nombre, TextField cantidad, RadioButton gasto, RadioButton ingreso, ObservableList<FinanceEntry> financeData, VBox pieChartContainer, ToggleGroup botones) {
	    String nombre1 = nombre.getText();
	    String cantidad1 = cantidad.getText();
	    String gasto1 = gasto.isSelected() ? "Gasto" : ingreso.isSelected() ? "Ingreso" : null;

	    try {
	        // Validamos los datos utilizando el m�todo de la clase modelo
	        FinanceEntry.validarEntrada(nombre1, cantidad1, gasto1);

	        // Si la validaci�n es correcta, se llama a la vista para agregar la entrada
	        vista.agregarEntrada(nombre, cantidad, gasto, ingreso, financeData, pieChartContainer, botones);
	    } catch (IllegalArgumentException e) {
	        // Si la validaci�n falla, mostramos un error
	        vista.showAlert("Error", e.getMessage());
	    }
	}


}