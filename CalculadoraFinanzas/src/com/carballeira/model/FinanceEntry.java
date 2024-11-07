package com.carballeira.model;

import javafx.scene.control.Button;

public class FinanceEntry {
    private final String name;
    private final Double amount;
    private final String type;
    private final Button deleteButton;

    public FinanceEntry(String name, Double amount, String type, Button deleteButton) {
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.deleteButton = deleteButton;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    
    public static void validarEntrada(String nombre, String cantidad, String tipo) {
        if (nombre.isEmpty()) throw new IllegalArgumentException("El campo del nombre no puede estar vac�o.");
        if (cantidad.isEmpty()) throw new IllegalArgumentException("El campo de cantidad no puede estar vac�o.");
        if (tipo == null) throw new IllegalArgumentException("Debe seleccionar al menos un tipo: Gasto o Ingreso.");
        try {
            Double.parseDouble(cantidad);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Debe introducir un n�mero v�lido para la cantidad.");
        }
    }
}