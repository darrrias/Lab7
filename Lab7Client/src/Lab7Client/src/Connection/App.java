package Connection;
import CityObject.*;
import Controller.Commands;
import Utilites.Console;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    private static ClientReceiver receiver;
    private static ClientSender sender;
    boolean was = false;
    private Commands command = new Commands();
    private Scanner in = new Scanner(System.in);
    private CommandsToSend commandsToSend = new CommandsToSend();
    private HashMap loginAndPassword = null;
    private Console console = new Console();

    public App(ClientSender sender, ClientReceiver receiver) throws NoSuchAlgorithmException {
        this.receiver = receiver;
        this.sender = sender;
    }


    public static ClientReceiver getReceiver() {
        return receiver;
    }

    public static ClientSender getSender() {
        return sender;
    }

    public void begin() throws IOException {
        try {
            String ans = "notValid";
            if (loginAndPassword == null) {
                while (ans.equals("notValid")) {
                    loginAndPassword = console.register();
                    loginAndPassword.put("port", String.valueOf(sender.getClientPort()));
                    sender.send("-1");
                    sender.send(loginAndPassword);
                    ans = receiver.receive();
                    if (ans.equals("notValid"))
                        if (loginAndPassword.get("reg").equals("log")) {
                            System.out.println("Проверьте логин или пароль.");
                            receiver.receive();
                        } else {
                            System.out.println("Пользователь с таким логином уже зарегистрирован.");
                            receiver.receive();
                        }
                }
                if (loginAndPassword.get("reg").equals("log"))
                    System.out.println("Пользователь успешно авторизирован.");
                else System.out.println("Пользователь успешно зарегистрирован.");
//                String received = receiver.receive();
//                if (received != null) {
//                    System.out.print(received);
//                }
                was = true;
                sender.setPortLoginAndPassword(loginAndPassword);
            } else {
                sender.send("-1");
                sender.send(loginAndPassword);
            }
        } catch (SocketTimeoutException | NoSuchAlgorithmException e) {
            loginAndPassword = null;
            System.out.println("Сервер недоступен =(");
            this.begin();
            was = false;
        }
    }

    public void run() throws IOException {
        while (true) {
            System.out.print("$ ");
            String commandName = in.nextLine();
            command.setCommand(commandName);
            if (command.getMessage() != (null))
                System.out.println(command.getMessage());
            if (command.getCommand() != null) {
                try {
                    if (!was) this.begin();
                    if (!(commandsToSend.getCommandsToSend().equals("")))
                        this.begin();
                    if (command.getCommand().getName().equals("execute_script"))
                        command.getCommand().execute(command.getArg());
                    if (command.getCommand().getName().equals("exit"))
                        command.getCommand().execute(command.getArg());
                    else {
                        sender.sendCommand(command);
                        if (sender.isCommandWithObject())
                            if (receiver.receive().equals("newCity")) {
                                HashMap packedCity = new HashMap();
//                                City city = new City();
//                                city.setId(1);
//                                city.setOwner("owner");
//                                city.setName(("name"));
//                                Coordinates coordinates = new Coordinates();
//                                coordinates.setX(4);
//                                coordinates.setY(6.0);
//                                city.setCoordinates(coordinates);
//                                city.setCreationDate(LocalDate.now());
//                                city.setArea(2.0);
//                                city.setPopulation(4);
//                                city.setMetersAboveSeaLevel((long) 312);
//                                city.setCapital(true);
//                                city.setClimate(Climate.OCEANIC);
//                                city.setStandardOfLiving(StandardOfLiving.HIGH);
//                                Human human = new Human();
//                                human.setName("governor_name");
//                                human.setHeight((long) 88);
//                                city.setGovernor(human);
//                                packedCity.put("city", city);
                                packedCity.put("city", CityBuilder.createCity());
                                packedCity.put("commandName", command.getCommand().getName());
                                packedCity.put("portLoginAndPassword", loginAndPassword);
                                sender.send(packedCity);
                            }
                        String received = receiver.receive();
                        System.out.println(received);
                    }
                    if (!(commandsToSend.getCommandsToSend().equals(""))) {
                        System.out.println("\nКоманды ранее отправленные на сервер:");
                        commandsToSend.addCommandsToSend(commandName);
                        this.begin();
                        commandsToSend.sendCommands();
                        commandsToSend.removeCommandsToSend();
                    }
                } catch (ClassNotFoundException e) {
                    //e.printStackTrace();

                } catch (SocketTimeoutException e) {

                   // e.printStackTrace();
                    System.out.println("Сервер недоступен");
                    commandsToSend.addCommandsToSend(commandName + "\n");
                    //if (was == false)
                    this.run();
                }
            }
        }
    }
}

