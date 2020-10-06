package CityObject;

import java.io.Serializable;

public enum StandardOfLiving implements Serializable {
    VERY_HIGH(2),
    HIGH(1),
    VERY_LOW(0);
    int choice;
    StandardOfLiving(int i) {
        choice = i;
    }
    public int getLevel(){
        return choice;
    }
}
