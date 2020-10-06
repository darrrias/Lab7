package Commands;

import CityObject.City;
import Connection.DataBaseController;
import Controller.CommandWithLogin;
import Controller.CommandWithObject;
import Controller.CommandWithoutArg;
import Controller.CollectionCity;
import DataBase.CitiesDataBase;

import java.sql.SQLException;


public class Add implements CommandWithObject, CommandWithoutArg, CommandWithLogin {
    String username;

    public String getName() {
        return "add";
    }

    public Object execute(Object arg) {
        CitiesDataBase citiesDataBase = DataBaseController.getcitiesDataBase();
        if (!((City) arg).getName().equals(null)) {
            City city = (City) arg;
            city.setOwner(username);
            try {
                city.setId(DataBaseController.getcitiesDataBase().getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            CollectionCity.cities.add(city);
            try {
                citiesDataBase.loadCollection(CollectionCity.cities);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "Город успешно построен и добавлен в коллекцию";
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