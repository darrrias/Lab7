package CityObject;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private int x;
    private Double y; //Максимальное значение поля: 192, Поле не может быть null

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
