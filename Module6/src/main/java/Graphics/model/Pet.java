package Graphics.model;

public class Pet {
    private double x, y;

    public Pet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
