package CityObject;

import java.io.Serializable;

public class Human implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long height; //Значение поля должно быть больше 0

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }
}
