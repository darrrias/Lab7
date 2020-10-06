package Commands;

import CityObject.City;
import Controller.CommandWithLogin;
import Controller.CommandWithoutArg;
import Controller.CollectionCity;

import java.util.Iterator;

public class Clear implements CommandWithoutArg, CommandWithLogin {
    String username;

    public String getName() {
        return "clear";
    }
    public Object execute(Object arg) {
        int count = 0;
        Iterator<City> itr = CollectionCity.cities.iterator();
        if( CollectionCity.cities.size() > 0) {
            while (itr.hasNext()) {
                City city = itr.next();
                if (city.getOwner().equals(username)) {
                    itr.remove();
                    count++;
                }
            }
            if (count > 0) return "Все ваши города успешно удалены.";
            else return "У вас нет своих элементов в коллекции.";
        } else return "Коллекция итак пустая.";
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}