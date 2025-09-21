package CurrencyConverter.model;

public class Currency {
    private String code;
    private String name;
    private double rateToEuro;

    public Currency(String code, String name, double rateToEuro) {
        this.code = code;
        this.name = name;
        this.rateToEuro = rateToEuro;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public double getRateToEuro() {
        return this.rateToEuro;
    }

    @Override
    public String toString() {
        return this.code + " - " + this.name;
    }
}
