package Commands;

import CityObject.City;
import Controller.CollectionCity;
import Controller.CommandWithoutArg;

public class Show implements CommandWithoutArg {
    @Override
    public String execute(Object arg) {
        return "";

    }

    @Override
    public String getName() {
        return "show";
    }
}
