package application;

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
        this.deleteButton= deleteButton;

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
    
    

}
