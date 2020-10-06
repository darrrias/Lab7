package Commands;

import CityObject.City;
import Controller.CollectionCity;
import Controller.CommandWithLogin;
import Controller.CommandWithoutArg;

import java.util.Iterator;

public class Clear implements CommandWithoutArg, CommandWithLogin {
    String username;

    public String getName() {
        return "clear";
    }
    public Object execute(Object arg) {
       return "";
    }

    @Override
    public void setUsername(String login) {
        username = login;
    }
}