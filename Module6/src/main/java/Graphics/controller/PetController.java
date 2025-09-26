package Graphics.controller;

import Graphics.model.Pet;
import Graphics.view.PetView;
import javafx.application.Platform;

public class PetController {
    private Pet pet = new Pet(200, 200);
    private PetView view;

    private volatile double targetX, targetY;
    private volatile boolean moving = false;
    private Thread movementThread;

    private static double SPEED = 2.0;
    private static final long UPDATE_INTERVAL_MS = 16;

    public PetController(PetView view) {
        this.view = view;
    }

    public void initialDraw() {
        this.view.updateCanvas(pet.getX(), pet.getY());
    }

    public void movePetTo(double x, double y) {
        this.targetX = x;
        this.targetY = y;
        this.moving = true;
        this.startMovementThread();
    }

    public void stop() {
        this.moving = false;
    }

    public void startMovementThread() {
        if (this.movementThread != null && this.movementThread.isAlive())
            return; // movement thread is already running, do nothing

        this.movementThread = new Thread(this::movementLoop);
        this.movementThread.setDaemon(true);
        this.movementThread.start();
    }

    private void movementLoop() {
        while (this.moving) {
            double dx = targetX - pet.getX();
            double dy = targetY - pet.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (!this.moving) break;

            if (distance < SPEED) {
                pet.setPos(targetX, targetY);
                this.moving = false;
            } else {
                double nx = pet.getX() + SPEED * dx / distance;
                double ny = pet.getY() + SPEED * dy / distance;
                pet.setPos(nx, ny);
            }

            Platform.runLater(() -> this.view.updateCanvas(pet.getX(), pet.getY()));

            try {
                Thread.sleep(UPDATE_INTERVAL_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
