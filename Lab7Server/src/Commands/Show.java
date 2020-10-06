package Commands;

import CityObject.City;
import Controller.CollectionCity;
import Controller.CommandWithoutArg;

public class Show implements CommandWithoutArg {
    @Override
    public String execute(Object arg) {
        try {
            String res = "";
            if (CollectionCity.size() > 0) {
                for (City city : CollectionCity.cities)
                    res += (city.getCity());
                return res;
            } else return ("Коллекция пустая");
        } catch (Exception e) {
            return "Произошел эксепшен";
        }
    }

    @Override
    public String getName() {
        return "show";
    }
}

