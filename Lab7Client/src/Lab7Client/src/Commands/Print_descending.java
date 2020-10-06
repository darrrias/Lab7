package Commands;

import CityObject.City;
import Controller.CollectionCity;
import Controller.CommandWithoutArg;

import java.util.PriorityQueue;

public class Print_descending implements CommandWithoutArg {
    @Override
    public String execute(Object arg) {
        return "";
    }

    @Override
    public String getName() {
        return "print_descending";
    }
}
