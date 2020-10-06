package Commands;

import CityObject.City;
import Controller.CollectionCity;
import Controller.Commandable;

public class Filter_by_population implements Commandable {
    @Override
    public String execute(Object arg) {
        boolean tumb = true;
        String res="";
        for (City city : CollectionCity.cities)
            if (city.getPopulation() == Long.parseLong((String) arg)) {
                res += (city.getCity());
                tumb = false;
            }
        if (tumb) return ("Ни один элемент не удовлетворяет условию");
        else return res;
    }

    @Override
    public String getName() {
        return "filter_by_population";
    }
}
