package Connection;

import CityObject.City;
import Controller.CommandWithLogin;
import Controller.CommandWithObject;
import Controller.Commandable;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User extends Thread {
    private String login;
    private ServerReceiver receiver;
    private ServerSender sender;
    private App app;
    private HashMap portLoginPassword;
    private ArrayList commandAndArgument;

    public User(String login, App app, ServerReceiver receiver, ServerSender serverSender) {
        this.login = login;
        this.sender = serverSender;
        this.receiver = receiver;
        this.app = app;
    }

    public void setPortLoginPassword(HashMap thisPortLoginPassword) {
        this.portLoginPassword = thisPortLoginPassword;
    }

    public void setCommandAndArgument(ArrayList commandAndArgument) {
        this.commandAndArgument = commandAndArgument;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public void run() {
        int port = Integer.parseInt((String) portLoginPassword.get("port"));
        if (port != -1)
            sender.setPort(port);
        else {
            this.interrupt();
        }
        ArrayList commandAndArgument = null;
        try {
            commandAndArgument = receiver.receiveCommand();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        Commandable command = (Commandable) commandAndArgument.get(0);
        String arg = (String) commandAndArgument.get(1);
        System.out.println("Получена команда \"" + command.getName() + "\" от пользователя \""+ portLoginPassword.get("login") +"\"");
        String commandResult = "";
        try {
            CommandWithLogin commandWithLogin = (CommandWithLogin) command;
            commandWithLogin.setUsername((String) portLoginPassword.get("login"));
        } catch (
                Exception e) {
        }
        try {
            CommandWithObject commandWithObject = (CommandWithObject) command;
            if (commandWithObject.check(arg)) {
                sender.send("newCity");
                City city;
                boolean tumb = true;
                HashMap packedHuman = null;
                List<HashMap> receivedHumans;
                while (tumb) {
                    receivedHumans = ServerReceiver.getReceivedCities();
                    for (int i = 0; i < receivedHumans.size(); i++) {
                        packedHuman = receivedHumans.get(i);
                        if ((packedHuman.get("city") != null) || packedHuman.get("commandName").equals(commandWithObject.getName())
                                || packedHuman.get("portLoginAndPassword") == portLoginPassword) {
                            ServerReceiver.removeReceivedCities(i);
                            tumb = false;
                        }
                    }
                }
                commandResult = (String) command.execute(packedHuman.get("city"));
            } else {
                sender.send("nope");
                commandResult = commandWithObject.whyFailed();
            }
        } catch (Exception e) {
            try {
                commandResult = (String) command.execute(arg);
            } catch (IOException | SQLException | ParseException ex) {
            }
        }
        try {
            sender.send(commandResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!commandResult.isEmpty())
            System.out.println("Команда успешно выполнена!");
        else System.out.println("");
        this.interrupt();

    }
}
