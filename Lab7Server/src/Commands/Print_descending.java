package Commands;

import CityObject.City;
import Controller.CollectionCity;
import Controller.CommandWithoutArg;

import java.util.PriorityQueue;

public class Print_descending implements CommandWithoutArg {
    @Override
    public String execute(Object arg) {
        String res = "";
        if (CollectionCity.cities.size() > 0) {
            PriorityQueue<City> queue = new PriorityQueue<>(CollectionCity.cities);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                City city = queue.peek();
                for (City city2 : queue) {
                    if (city.compareTo(city2) < 0) {
                        city = city2;
                    }
                }
                res += (city.getCity());
                queue.remove(city);
            }
        return res;
        }
        else return "Коллекция пустая";
    }

    @Override
    public String getName() {
        return "print_descending";
    }
}
