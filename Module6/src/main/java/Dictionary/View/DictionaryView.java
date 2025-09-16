package Dictionary.View;

import Dictionary.Controller.DictionaryController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;

public class DictionaryView extends Application {
    private DictionaryController controller;
    private Label message = new Label();
    private TextField searchField = new TextField();

    public void init() {
        this.controller = new DictionaryController(this);
    }

    public void start(Stage stage) {
        // top container
        VBox pane = new VBox(10);

        // input wrapper
        HBox inputWrapper = new HBox(5);
        Button searchBtn = new Button("Search");
        Button addWordBtn = new Button("Add Word");
        this.searchField.setPromptText("Enter word to search");
        inputWrapper.getChildren().addAll(this.searchField, searchBtn, addWordBtn);


        pane.getChildren().addAll(
            inputWrapper,
            this.message
            // ...
        );

        Scene scene = new Scene(pane);
        stage.setTitle("Dictionary");
        stage.setScene(scene);

        // search btn event
        searchBtn.setOnAction((event) -> {
            String searchWord = this.searchField.getText();

            if (searchWord == null || searchWord.isEmpty()) {
                return;
            }

            String definition = this.controller.getDefinition(searchWord);
            String output = searchWord.toUpperCase();

            if (definition == null)
                output += " has no definition yet";
            else
                output += "; " + definition;

            this.message.setText(output);
        });

        // add word btn event
        addWordBtn.setOnAction((event) -> {
            this.wordAddForm();
        });

        stage.show();
    }

    public void wordAddForm() {
        Stage prompt = new Stage();
        prompt.initModality(Modality.APPLICATION_MODAL);
        prompt.setTitle("Add new word");

        // initialize the elements
        VBox formWrapper = new VBox(10);

        TextField wordField = new TextField();
        wordField.setPromptText("Enter new word");

        TextField definitionField = new TextField();
        definitionField.setPromptText("Enter word definition");

        Button submitBtn = new Button("Add Word");
        Label info = new Label(); // for status messages

        formWrapper.getChildren().addAll(
            new Label("Add a new word to the dictionary"),
            wordField,
            definitionField,
            submitBtn,
            info
        );

        Scene scene = new Scene(formWrapper);
        prompt.setScene(scene);

        submitBtn.setOnAction((event) -> {
            String newWord = wordField.getText();
            String definition = definitionField.getText();

            // invalid addition
            if (newWord == null || newWord.isEmpty() || definition == null || definition.isEmpty()) {
                info.setText("Both fields are required.");
                return;
            }

            System.out.println("haloo");
            // word already exists
            if (this.controller.wordExists(newWord)) {
                info.setText("Definition for word already exists.");
                return;
            }

            System.out.println("haloo2");

            this.controller.addWord(newWord, definition);
            prompt.close();
        });

        prompt.showAndWait();
    }
}
