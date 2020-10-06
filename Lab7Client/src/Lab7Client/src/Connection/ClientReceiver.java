package Connection;

import CityObject.City;
import Controller.CollectionCity;
import Utilites.Deserializator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.PriorityQueue;


public class ClientReceiver {
    Deserializator deserializator = new Deserializator();
    static DatagramSocket clientSocket = null;
    byte[] data = new byte[2056];
    private int clientPort;

    public ClientReceiver(int clientPort) throws SocketException {
        this.clientSocket = new DatagramSocket(clientPort);
        this.clientPort = clientPort;
    }


    public int getClientPort() {
        return clientPort;
    }

    public String receive() throws IOException {
        DatagramPacket packet = new DatagramPacket(data, data.length);
        clientSocket.setSoTimeout(2000);
        clientSocket.receive(packet);
        clientSocket.setSoTimeout(0);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void receiveCollection() throws IOException, ClassNotFoundException {
        DatagramPacket packet = new DatagramPacket(data, data.length);
        clientSocket.setSoTimeout(2000);
        clientSocket.receive(packet);
        clientSocket.setSoTimeout(0);
        CollectionCity.cities=((PriorityQueue<City>) Deserializator.toDeserialize(packet.getData()));
    }
}
//    public void innerSend(HumanBeing humanBeing) throws IOException {
//        try {
//            Serializator serializator = new Serializator();
//            data = serializator.toSerialize(humanBeing);
//            DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), ClientSender.getServerPort());
//            clientSocket.send(sendPacket);
//        } catch (Exception e) {
//            e.printStackTrace();



