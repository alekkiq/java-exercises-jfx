package CurrencyConverter.controller;

import CurrencyConverter.model.Converter;
import CurrencyConverter.model.Currency;
import CurrencyConverter.view.ConverterView;

import java.util.List;

public class ConverterController {
    private Converter model = new Converter();
    private ConverterView view;

    public ConverterController(ConverterView view) {
        this.view = view;
    }

    public void initialize() {
        List<Currency> currencies = this.model.getCurrencies();
        this.view.populateCurrencies(currencies);
        this.view.showInstructions("Enter an amount, choose source and target currencies, then press Convert.");
    }

    public void onConvert(double amount, Currency from, Currency to) {
        this.view.clearMessage();

        if (from == null || to == null) {
            this.view.showError("Please select both source and target currencies.");
            return;
        }

        double result = this.model.convert(amount, from, to);
        this.view.showResult(result);
    }
}
