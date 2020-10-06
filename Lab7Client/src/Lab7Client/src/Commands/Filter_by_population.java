package Commands;

import CityObject.City;
import Controller.CollectionCity;
import Controller.Commandable;

public class Filter_by_population implements Commandable {
    @Override
    public String execute(Object arg) {
        return "";
    }

    @Override
    public String getName() {
        return "filter_by_population";
    }
}
