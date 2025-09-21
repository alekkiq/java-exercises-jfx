package CurrencyConverter.view;

import CurrencyConverter.controller.ConverterController;

import CurrencyConverter.model.Currency;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ConverterView extends Application {
    private ConverterController controller;

    private Label message = new Label();
    private TextField amountField = new TextField();
    private TextField resultField = new TextField();
    private ComboBox<Currency> fromCurrency = new ComboBox<>();
    private ComboBox<Currency> toCurrency = new ComboBox<>();
    private Button convertBtn = new Button("Convert");

    public void init() {
        this.controller = new ConverterController(this);
    }

    public void start(Stage stage) {
        // labels
        Label amountLabel = new Label("Amount");
        Label resultLabel = new Label("Result");
        Label fromLabel = new Label("From currency");
        Label toLabel = new Label("To currency");
        this.message.setWrapText(true);
        this.message.getStyleClass().add("heading");

        // fields
        this.amountField.setPromptText("e.g. 50.00");

        this.resultField.setEditable(false);
        this.resultField.setFocusTraversable(false);

        // layout
        VBox left = new VBox(6, amountLabel, this.amountField, fromLabel, this.fromCurrency);
        VBox right = new VBox(6, resultLabel, this.resultField, toLabel, this.toCurrency);

        HBox form = new HBox(16, left, right);
        form.setAlignment(Pos.CENTER);

        HBox actions = new HBox(16, this.convertBtn);
        actions.setAlignment(Pos.CENTER);

        VBox rootBox = new VBox(16, message, form, actions);
        rootBox.setPadding(new Insets(24));
        rootBox.setMaxWidth(400);
        rootBox.setAlignment(Pos.TOP_CENTER);

        StackPane root = new StackPane(rootBox);
        StackPane.setAlignment(rootBox, Pos.TOP_CENTER);

        Scene scene = new Scene(root, 500, 340);
        scene.getStylesheets().add("currency-converter.css");

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();

        // events
        this.convertBtn.setOnAction(event -> {
            String raw = this.amountField.getText().trim();

            if (raw == null || raw.isBlank()) {
                this.showError("Please enter a valid amount, e.g. 50.00");
                return;
            }

            final double amount;

            try {
                amount = Double.parseDouble(raw.replace(',', '.'));
            } catch (NumberFormatException e) {
                this.showError("Please enter a valid amount, e.g. 50.00");
                return;
            }

            this.controller.onConvert(
                amount,
                this.fromCurrency.getValue(),
                this.toCurrency.getValue()
            );
        });

        this.controller.initialize();
    }

    public void populateCurrencies(List<Currency> currencies) {
        this.fromCurrency.getItems().setAll(currencies);
        this.toCurrency.getItems().setAll(currencies);

        if (!currencies.isEmpty()) {
            this.fromCurrency.getSelectionModel().selectFirst();
            this.toCurrency.getSelectionModel().selectFirst();
        }
    }

    public void showResult(double value) {
        NumberFormat formatter = new DecimalFormat("#,##0.00");
        this.resultField.setText(formatter.format(value));
        this.message.getStyleClass().remove("err");
        this.message.setText("Converted successfully");
    }

    public void showError(String text) {
        this.resultField.setText("");
        this.message.getStyleClass().add("err");
        this.message.setText(text);
    }

    public void clearMessage() {
        this.message.getStyleClass().remove("err");
        this.message.setText("");
    }

    public void showInstructions(String text) {
        this.message.getStyleClass().remove("err");
        this.message.setText(text);
    }
}
