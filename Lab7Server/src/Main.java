
import Connection.App;
import Connection.ServerReceiver;
import Connection.ServerSender;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        try {
            ServerSender sender = new ServerSender(21932);
            int serverPort = 17932;
            ServerReceiver receiver = new ServerReceiver(serverPort);
            App app = new App(sender, receiver);
            receiver.receive();
            app.begin();
            app.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Прости сервер, но работает уже другой.");
        }

    }
}
