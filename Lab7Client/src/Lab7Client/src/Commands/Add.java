package Commands;

import Controller.CommandWithLogin;
import Controller.CommandWithObject;
import Controller.CommandWithoutArg;


public class Add implements CommandWithObject, CommandWithoutArg, CommandWithLogin {
    String username;

    public String getName() {
        return "add";
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