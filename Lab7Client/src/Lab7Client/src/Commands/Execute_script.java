package Commands;

import CityObject.City;
import CityObject.CityBuilder;
import Connection.App;
import Connection.ClientReceiver;
import Connection.ClientSender;
import Controller.CommandWithoutArg;
import Controller.Commandable;
import Controller.Commands;
import Utilites.ReaderFromFile;

import java.io.IOException;

public class Execute_script implements Commandable {
    String name = "execute_script";
    public static String result = "";
    public String getName() {
        return name;
    }

    /**
     * @param arg of file
     */
    public Object execute(Object arg) throws IOException {
        try {
            try {
                ClientSender sender = App.getSender();
                ClientReceiver receiver = App.getReceiver();
                String filename = (String) arg;
                String data = ReaderFromFile.read(filename);
                Commands command = new Commands();
                String res = "";
                //System.out.println("data " + data);
                if (data != null) {
                    String[] commands = data.split("\n|\r\n");
                    //System.out.println("command.lenght " + commands.length);
                    for (int i = 0; i < commands.length; i++) {
                        if (!(commands[i].equals("execute_script " + filename))) {
                            command.setCommand(commands[i]);
                            if (command.getCommand() != null) {
                                String tempCommandName = commands[i];
                                String received = "";
                                sender.sendCommand(command);
                                String whyFailed = "";
                                receiver.receiveCollection();
                                received = receiver.receive();
                                if (received != null) {
                                    res += "Выполнение команды \"" + tempCommandName + "\":\n" + received + whyFailed + "\n\n";
                                    ;
                                }
                            }
                            if (command.getMessage() != (null)) {
                                String[] sentence = command.getMessage().split(",");
                                res += "Командa \"" + commands[i] + "\": " + sentence[0] + ".\n\n";
                            }
                        } else res += "Командa \"" + commands[i] + "\": невыполнима.\n";
                    }
                }
                result = (res);
                return "";
            } catch (NullPointerException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "";
    }
}