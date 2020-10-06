package Commands;

import CityObject.City;
import CityObject.StandardOfLiving;
import Controller.CollectionCity;
import Controller.Commandable;

public class Filter_greater_than_standard_of_living implements Commandable {
    @Override
    public String execute(Object arg) {
        try {
            String res = "";
            boolean tumb = true;
            for (City city : CollectionCity.cities)
                if (city.getStandardOfLiving().getLevel() >
                        StandardOfLiving.valueOf(((String) arg).toUpperCase()).getLevel()) {
                    res += (city.getCity());
                    tumb = false;
                }
            if (tumb) return ("Ни один элемент не удовлетворяет условию");
            else return res;
        } catch (IllegalArgumentException e) {
            return ("Аргументами этой команды могут быть только значения: VERY_HIGH, HIGH, VERY_LOW");
        }
    }

    @Override
    public String getName() {
        return "filter_greater_than_standard_of_living";
    }
}
