package Commands;

import CityObject.City;
import Controller.CollectionCity;
import Controller.CommandWithLogin;
import Controller.CommandWithObject;


public class Update implements CommandWithObject, CommandWithLogin {
    static long arg;
    String whyFailed;
    static City updated;
    String username;

    public String getName() {
        return "update";
    }

    public Object execute(Object hum) {
        City city = (City) hum;
        city.setId(arg);
        city.setOwner(username);
        CollectionCity humans = new CollectionCity();
        if (city.getName() != null) {
            CollectionCity.cities.remove(updated);
            CollectionCity.cities.add(city);
            new Show().execute(null);
            return ("Город [id:" + arg + "] успешно обновлен.");
        }
        return "";
    }


    @Override
    public boolean check(String arg) {
        int id = Integer.parseInt(arg);
        Update.arg = id;
        if (!CollectionCity.checkId(id)) {
            for (City city : CollectionCity.cities)
                if (city.getId() == id)
                    if (city.getOwner().equals(username)) {
                        updated = city;
                        return true;
                    }
                    else {
                        whyFailed = "Элемент с таким id принадлежит не вам";
                        return false;
                    }
        } else {
            whyFailed = "Города с таким id не существует";
            return false;
        }
        return false;
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}