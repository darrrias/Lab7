package Commands;

import Controller.CommandWithLogin;
import Controller.CommandWithObject;
import Controller.CommandWithoutArg;


public class Add_if_max implements CommandWithObject, CommandWithoutArg, CommandWithLogin {
    String username;

    public String getName() {
        return "add_if_max";
    }

    public Object execute(Object arg) {
        return "";
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