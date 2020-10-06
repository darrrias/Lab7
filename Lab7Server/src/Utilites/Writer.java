package Utilites;

import CityObject.City;
import Controller.CollectionCity;

import java.io.FileOutputStream;
import java.util.Scanner;

public class Writer {
    public static Scanner in = new Scanner(System.in);
    public static String filename = "";

    public static void writeCollection() {
        try {

            FileOutputStream outputStream = new FileOutputStream(filename);
            for (City city : CollectionCity.cities) {
                outputStream.write(city.getCSV().getBytes());
            }
        } catch (Exception e) {
        }

    }
}
