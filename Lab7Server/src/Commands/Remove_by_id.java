package Commands;

import CityObject.City;
import Controller.CommandWithLogin;
import Controller.Commandable;
import Controller.CollectionCity;

import java.util.Iterator;

/**
 * Removes element by its id
 *
 * @author Diana
 */
public class Remove_by_id implements Commandable, CommandWithLogin {
    String name = "remove_by_id";
    String username;

    public String getName() {
        return name;
    }

    /**
     * @param arg id
     * @return
     */
    public Object execute(Object arg) {
        CollectionCity humans = new CollectionCity();
        try {
            int id = Integer.parseInt((String) arg);
            //if (humans.findIndexOfElemById(id) != -1) return "Нет челика с таким id.";
            Iterator<City> it = (Iterator<City>) CollectionCity.cities.iterator();
            while (it.hasNext()) {
                City human = (City) it.next();
                if (id == human.getId()) {
                    if (human.getOwner().equals(username)) {
                        it.remove();
                        return "Элемент с id " + id + " удален";
                    }
                    else return "У вас нет прав на удаление этотого элемента.";
                }
            }
            return "Нет возможности удалить этот элемент";
        } catch (NumberFormatException exp) {
            return ("Значение аргумента должно быть типа:\"long\".");

        }

    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}
