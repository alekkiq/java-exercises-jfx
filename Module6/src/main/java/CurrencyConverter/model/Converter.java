package CurrencyConverter.model;

import CurrencyConverter.model.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {
    private Map<String, Currency> currencies = new HashMap<>();

    public Converter() {
        // not real rates, just for demo
        add(new Currency("EUR", "Euro", 1.0));
        add(new Currency("USD", "United States Dollar", 0.92));
        add(new Currency("GBP", "British Pound", 1.18));
        add(new Currency("JPY", "Japanese Yen", 0.0063));
        add(new Currency("CAD", "Canadian Dollar", 0.68));
        add(new Currency("AUD", "Australian Dollar", 0.61));
        add(new Currency("CHF", "Swiss Franc", 1.03));
        add(new Currency("SEK", "Swedish Krona", 0.087));
        add(new Currency("NOK", "Norwegian Krone", 0.086));
    }

    private void add(Currency currency) {
        this.currencies.put(currency.getCode(), currency);
    }

    public List<Currency> getCurrencies() {
        return new ArrayList<>(this.currencies.values());
    }

    public Currency getByCode(String code) {
        return this.currencies.get(code);
    }

    public double convert(double amount, Currency from, Currency to) {
        if (from == null || to == null)
            throw new IllegalArgumentException("Currencies cannot be null!");

        double eur = amount * from.getRateToEuro();
        return eur / to.getRateToEuro();
    }
}
