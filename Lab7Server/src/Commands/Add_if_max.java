package Commands;

import CityObject.City;
import Connection.DataBaseController;
import Controller.CollectionCity;
import Controller.CommandWithLogin;
import Controller.CommandWithObject;
import Controller.CommandWithoutArg;
import DataBase.CitiesDataBase;

import java.sql.SQLException;


public class Add_if_max implements CommandWithObject, CommandWithoutArg, CommandWithLogin {
    String username;

    public String getName() {
        return "add_if_max";
    }

    public Object execute(Object arg) {
        boolean tumb = true;
        CitiesDataBase citiesDataBase = DataBaseController.getcitiesDataBase();
        City sample = (City) arg;
        if (!((City) arg).getName().equals(null)) {
            for (City city : CollectionCity.cities)
                if (city.compareTo(sample) > 0) tumb = false;
            if (tumb) {
                sample.setOwner(username);
                try {
                    sample.setId(DataBaseController.getcitiesDataBase().getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                CollectionCity.cities.add(sample);
                try {
                    citiesDataBase.loadCollection(CollectionCity.cities);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return ("Город оказался максимальным добавлен в коллекцию");
            } else return ("Нашлись города и побольше, Ваш город не был добавлен в коллекцию=(");
        } else return "";
    }

    @Override
    public boolean check(String arg) {
        return true;
    }

    @Override
    public String whyFailed() {
        return null;
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}