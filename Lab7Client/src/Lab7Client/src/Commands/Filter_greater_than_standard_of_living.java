package Commands;

import CityObject.City;
import CityObject.StandardOfLiving;
import Controller.CollectionCity;
import Controller.Commandable;

public class Filter_greater_than_standard_of_living implements Commandable {
    @Override
    public String execute(Object arg) {
        return "";
    }

    @Override
    public String getName() {
        return "filter_greater_than_standard_of_living";
    }
}
