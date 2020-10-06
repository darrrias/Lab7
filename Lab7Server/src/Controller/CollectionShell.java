package Controller;


import CityObject.City;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class CollectionShell implements Serializable {
    PriorityQueue<City> cities;
    LocalDate dateCreation;
    HashMap<String, Long> colors;

    public CollectionShell(PriorityQueue<City> cities, LocalDate dateCreation) {
        this.cities = cities;
        this.dateCreation = dateCreation;
    }

    public PriorityQueue<City> getCities() {
        return cities;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

}
