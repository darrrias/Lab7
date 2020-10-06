package DataBase;


import CityObject.*;
import Controller.CollectionCity;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;

public class CitiesDataBase {
    Statement statement;
    private static Connection connection;

    public CitiesDataBase(Connection connection) throws SQLException {
        CitiesDataBase.connection = connection;
        this.statement = connection.createStatement();
        this.createCitiesDB();
        this.createDateCreation();
        CollectionCity.CreationDate = LocalDate.now();
    }


    private LocalDate getDateCreation() throws SQLException {
        String sql = " SELECT * FROM datecreation";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) return LocalDate.parse(rs.getString(1));
        else return LocalDate.now();
    }

    public static long getId() throws SQLException {
        String sql = " SELECT nextval('id_sequence')";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            long id = rs.getLong(1);
            System.out.println(id);
            return id;
        }
        return 0;
    }

    private void createDateCreation() throws SQLException {
        try {
            String sql = "CREATE TABLE datecreation (dateCreation text NOT NULL)";
            statement.execute(sql);
            String sql1 = "INSERT INTO datecreation (dateCreation) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, LocalDate.now().toString());
            preparedStatement.execute();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void createCitiesDB() throws SQLException {
        try {
            try {
                String createSequence = "CREATE SEQUENCE id_sequence start 1 increment 1;";
                statement.execute(createSequence);
            } catch (Exception e) {
            }
            String createTableSQL = "CREATE TABLE cities " +
                    "(id BIGINT PRIMARY KEY NOT NULL ," +
                    " owner TEXT NOT NULL , " +
                    " name TEXT NOT NULL , " +
                    " x BIGINT  NOT NULL , " +
                    " y DOUBLE PRECISION NOT NULL , " +
                    " creation_date TEXT NOT NULL , " +
                    " area DOUBLE PRECISION NOT NULL, " +
                    " population BIGINT, " +
                    " meters_above_sea_level BIGINT, " +
                    " capital BOOLEAN , " +
                    " climate TEXT NOT NULL , " +
                    " standard_of_living TEXT NOT NULL , " +
                    " governor_name TEXT NOT NULL , " +
                    " governor_height BIGINT NOT NULL )";
            statement.execute(createTableSQL);
        } catch (Exception e) {
           // e.printStackTrace();
        }

    }

    public void loadCollection(PriorityQueue<City> cities) throws SQLException {
        String sql = "TRUNCATE cities;";
        statement.execute(sql);
        for (City human : cities) {
            this.insertCity(human);
        }
    }

    public PriorityQueue<City> getCollection() {
        try {
            PriorityQueue<City> cities = new PriorityQueue<City>();
            String sql = " SELECT * FROM cities";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getLong("id"));
                city.setOwner(rs.getString("owner"));
                city.setName(rs.getString("name"));
                Coordinates coordinates = new Coordinates();
                coordinates.setX(rs.getInt("x"));
                coordinates.setY(rs.getDouble("y"));
                city.setCoordinates(coordinates);
                city.setCreationDate(LocalDate.parse(rs.getString("creation_date")));
                city.setArea(rs.getDouble("area"));
                city.setPopulation(rs.getLong("population"));
                city.setMetersAboveSeaLevel(rs.getLong("meters_above_sea_level"));
                city.setCapital(rs.getBoolean("capital"));
                city.setClimate(Climate.valueOf(rs.getString("climate")));
                city.setStandardOfLiving(StandardOfLiving.valueOf(rs.getString("standard_of_living")));
                Human human = new Human();
                human.setName(rs.getString("governor_name"));
                human.setHeight(rs.getLong("governor_height"));
                city.setGovernor(human);
                cities.add(city);
            }
            return cities;
        } catch (Exception e) {
            //e.printStackTrace();
            return new PriorityQueue<City>();
        }
    }

    public void insertCity(City city) throws SQLException {
        String sql = "INSERT INTO cities (id, owner, name, x, y, creation_date, area, population, meters_above_sea_level, capital, climate, " +
                "standard_of_living, governor_name, governor_height) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, city.getId());
        preparedStatement.setString(2, city.getOwner());
        preparedStatement.setString(3, city.getName());
        preparedStatement.setLong(4, city.getCoordinates().getX());
        preparedStatement.setDouble(5, city.getCoordinates().getY());
        LocalDate localDate = city.getCreationDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = localDate.format(formatter);
        preparedStatement.setString(6, formattedString);
        preparedStatement.setDouble(7, city.getArea());
        preparedStatement.setLong(8, city.getPopulation());
        preparedStatement.setDouble(9, city.getMetersAboveSeaLevel());
        preparedStatement.setBoolean(10, city.getCapital());
        preparedStatement.setString(11, city.getClimate().toString());
        preparedStatement.setString(12, city.getStandardOfLiving().toString());
        preparedStatement.setString(13, city.getGovernor().getName());
        preparedStatement.setLong(14, city.getGovernor().getHeight());
        preparedStatement.execute();
    }
}
