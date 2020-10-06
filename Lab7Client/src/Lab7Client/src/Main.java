package Lab7Client.src;


import Connection.App;
import Connection.ClientReceiver;
import Connection.ClientSender;
import Controller.Commands;
import Utilites.Console;

import java.io.IOException;
import java.net.BindException;
import java.security.NoSuchAlgorithmException;


public class Main {//Client

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        int clientPort = -10;
        int serverPort =  17932;
        if (args.length != 0) {
            try {
                clientPort = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println("Неверно указан порт(целое положительное число, меньшее 65536).");
            }
        }
        if (clientPort < 0) clientPort = Console.getClientPort();
        Commands commands = new Commands();
        Help help = new Help();
        Add add = new Add();
        Info info = new Info();
        Clear clear = new Clear();
        Show show = new Show();
        Remove_first remove_first = new Remove_first();
        Print_descending print_descending = new Print_descending();
        Execute_script execute_script = new Execute_script();
        Remove_by_id remove_by_id = new Remove_by_id();
        Exit exit = new
        commands.regist(info, add, clear, show, filter_by_population,
        filter_greater_than_standard_of_living,  print_descending, remove_first,
        remove_by_id, update, help, add_if_max, execute_script);
        try {
            ClientReceiver receiver = new ClientReceiver(clientPort);
            ClientSender sender = new ClientSender(serverPort, clientPort);
            App app = new App(sender, receiver);
            app.begin();
            app.run();
        } catch (BindException e) {
            System.out.println("Этот порт уже занят=(");
        } catch (IllegalArgumentException e) {
            System.out.println("Этот порт слишком большой=(");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}


