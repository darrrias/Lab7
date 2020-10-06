package Utilites;

import CityObject.*;

import java.io.*;
import java.time.LocalDate;
import java.util.PriorityQueue;

public class Reader {
    public static String file;

    public static String read(String filename) throws IOException {
        try {
            file = filename;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)));
            String data = "";
            String line = "";
            long startTime = System.currentTimeMillis();
            while ((line = bufferedReader.readLine()) != null) {
                data += line + "\n";
                if (startTime - System.currentTimeMillis() < -1000){
                    System.out.println("Файл слишком большой.");
                    return data;
                }
            }
            bufferedReader.close();
            return data;
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    public static PriorityQueue parse(String data) {
        try {
            PriorityQueue<City> priorityQueue = new PriorityQueue();
            String[] lines = data.split("\n|\r\n");
            for (int i = 0; i < lines.length; i++) {
                City city = new City();
                String[] params = lines[i].split(",");
                city.setId(Long.parseLong(params[0]));
                city.setName(params[1]);
                Coordinates coordinates = new Coordinates();
                coordinates.setX(Integer.parseInt(params[2]));
                coordinates.setY(Double.parseDouble(params[3]));
                city.setCoordinates(coordinates);
                city.setCreationDate(LocalDate.parse(params[4]));
                city.setArea(Double.parseDouble(params[5]));
                city.setPopulation(Long.parseLong(params[6]));
                city.setMetersAboveSeaLevel(Long.parseLong(params[7]));
                city.setCapital(Boolean.parseBoolean(params[8]));
                city.setClimate(Climate.valueOf(params[9]));
                city.setStandardOfLiving(StandardOfLiving.valueOf(params[10]));
                Human human = new Human();
                human.setName(params[11]);
                human.setHeight(Long.valueOf(Long.parseLong(params[12])));
                //human.setHeight(Long.parseLong(params[12]));
                city.setGovernor(human);
                priorityQueue.add(city);
            }
            return priorityQueue;
        } catch (Exception e) {
            System.out.println("Коллекция пустая");
            return new PriorityQueue();
        }
    }

}
