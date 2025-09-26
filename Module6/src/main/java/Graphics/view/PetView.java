package Graphics.view;

import Graphics.controller.PetController;
import Graphics.model.Pet;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PetView extends Application {
    private PetController controller;
    private Canvas canvas;
    private Image img;
    private GraphicsContext gc;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    @Override
    public void init() {
        this.controller = new PetController(this);
    }

    @Override
    public void start(Stage stage) {
        this.canvas = new Canvas(WIDTH, HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        this.img = new Image("/cat.png", 80, 0, true, true);

        this.canvas.setOnMouseMoved(this::handleMouseMoved);
        this.canvas.setOnMouseExited(e -> this.controller.stop());

        StackPane root = new StackPane(this.canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Virtual Pet");
        stage.setScene(scene);
        stage.show();

        this.controller.initialDraw();
    }

    private void handleMouseMoved(MouseEvent e) {
        this.controller.movePetTo(e.getX(), e.getY());
    }

    public void updateCanvas(double petX, double petY) {
        this.gc.clearRect(0,0, WIDTH, HEIGHT);
        this.gc.drawImage(this.img, petX - this.img.getWidth() / 2, petY - this.img.getHeight() / 2);

    }
}
