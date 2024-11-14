package com.carballeira.controller;

import com.carballeira.model.FinanceEntry;
import com.carballeira.view.AlertaView;
import com.carballeira.view.Main;

import javafx.collections.ObservableList;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class FinanceController {
	private FinanceEntry model;
	private Main vista;
	
	public static final String ERROR = "Error";
	public static final String GASTO = "Gasto";
	public static final String INGRESO = "Ingreso";
	public FinanceController(FinanceEntry model, Main vista) {
		this.model = model;
		this.vista = vista;
	}

	public FinanceController(Main vista) {
		this.vista = vista;
	}

	
	
	
	public void agregarDatos(ObservableList<FinanceEntry> financeData, VBox pieChartContainer, ToggleGroup botones) {
	


	        // Validamos los datos utilizando el método de la clase modelo
	        if (model.validarEntrada().equals("")) {

	        // Si la validación es correcta, se llama a la vista para agregar la entrada
	        vista.agregarEntrada(model, financeData, pieChartContainer, botones);
	        }
	        

	        // Si la validación falla, mostramos un error
	        else {
	        	AlertaView vista2 = new AlertaView();
	            AlertaController controladorAlerta = new AlertaController(vista2);
	        	controladorAlerta.crearAlerta(ERROR,model.validarEntrada());
	        }
	   
	}

	public FinanceEntry getModel() {
		return model;
	}

	public void setModel(FinanceEntry model) {
		this.model = model;
	}
	
	


}
