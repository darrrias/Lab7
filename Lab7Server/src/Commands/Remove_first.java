package Commands;

import CityObject.City;
import Controller.CommandWithLogin;
import Controller.CommandWithoutArg;
import Controller.CollectionCity;

public class Remove_first implements CommandWithoutArg, CommandWithLogin {
    String username;

    /**
     * @param arg ignore this
     * @return
     */
    public Object execute(Object arg) {
        if (CollectionCity.cities.size() == 0) return ("Коллекция пустая.");
        for (City city: CollectionCity.cities) {
            if (city.getOwner().equals(username)) {
                CollectionCity.cities.remove(city);
				return ("Первый элемент успешно удален.");
            }
        }
        return "У вас нет своих элементов в коллекции.";
}

    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}