package application;

public class FinanceEntry {
    private final String name;
    private final Double amount;
    private final String type;
   
    public FinanceEntry(String name, Double amount, String type) {
        this.name = name;
        this.amount = amount;
        this.type = type;

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

}
