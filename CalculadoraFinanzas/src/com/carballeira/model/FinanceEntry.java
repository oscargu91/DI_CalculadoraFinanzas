package com.carballeira.model;

import javafx.scene.control.Button;

public class FinanceEntry {
	private final String name;
	private final String amount;
	private final String type;
	private Button deleteButton;

	public static final String ERROR_NOMBRE_VACIO = "El campo del nombre no puede estar vac�o.";
	public static final String ERROR_CANTIDAD_VACIA = "El campo de cantidad no puede estar vac�o.";
	public static final String ERROR_TIPO_NO_SELECCIONADO = "Debe seleccionar al menos un tipo: Gasto o Ingreso.";
	public static final String ERROR_CANTIDAD_INVALIDA = "Debe introducir un n�mero v�lido para la cantidad.";

	public FinanceEntry(String name, String amount, String type, Button deleteButton) {
		super();
		this.name = name;
		this.amount = amount;
		this.type = type;
		this.deleteButton = deleteButton;
	}

	public FinanceEntry(String name, String amount, String type) {
		super();
		this.name = name;
		this.amount = amount;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getAmount() {
		return amount;
	}

	public String getType() {
		return type;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public String validarEntrada() {
		// Verifica si el nombre est� vac�o
		if (name.isEmpty()) {
			return ERROR_NOMBRE_VACIO;
		}

		// Verifica si la cantidad est� vac�a
		if (amount.isEmpty()) {
			return ERROR_CANTIDAD_VACIA;
		}

		// Verifica si el tipo es nulo
		if (type == null) {
			return ERROR_TIPO_NO_SELECCIONADO;
		}

		// Verifica si la cantidad es un n�mero v�lido
		try {
			Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			return ERROR_CANTIDAD_INVALIDA;
		}
		// si no hay ningun error, devuelve un string vacio que se usa posteriormente
		// como validador
		return "";
	}

}
