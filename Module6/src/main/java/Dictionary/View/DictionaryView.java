package Dictionary.View;

import Dictionary.Controller.DictionaryController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;

public class DictionaryView extends Application {
    private DictionaryController controller;
    private TextField searchField = new TextField();

    public void start(Stage stage) {
        FlowPane pane = new FlowPane();
        Button searchBtn = new Button("Search");

        stage.setTitle("Dictionary");

        pane.getChildren().addAll(
            this.searchField,
            searchBtn
            // ...
        );

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        searchBtn.setOnAction((event) -> {
            String searchWord = this.searchField.getText();

            if (searchWord != null && !searchWord.isEmpty()) {
                controller.
            }
        });
    }

    public void init() {
        this.controller = new DictionaryController(this);
    }
}
