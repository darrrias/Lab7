package CityObject;

import java.io.Serializable;
import java.time.LocalDate;

public class City implements Comparable, Serializable {
    private String owner;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double area; //Значение поля должно быть больше 0, Поле не может быть null
    private long population; //Значение поля должно быть больше 0
    private Long metersAboveSeaLevel;
    private Boolean capital; //Поле может быть null
    private Climate climate; //Поле не может быть null
    private StandardOfLiving standardOfLiving; //Поле не может быть null
    private Human governor; //Поле не может быть null

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public Long getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(Long metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Boolean getCapital() {
        return capital;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public void setStandardOfLiving(StandardOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

    public String getCity() {
        return "Город [id:" + getId() + "]:\n\t" + "Владелец: " + owner + "\n\t" + "Название: " + name +
                "\n\tКоординаты:\n\t\tx: " + coordinates.getX() +
        "\n\t\ty: " + coordinates.getY() + "\n\tМестное время : " + creationDate + "\n\tМестность : " + area +
                "\n\tЧисленность: " + population + "\n\tМетры над уровнем моря : " + metersAboveSeaLevel + "\n\tСтолица: "
                + capital + "\n\tКлимат: " + climate + "\n\tСтандарты жизни: " + standardOfLiving +
                "\n\tГубернатор: \n\t\t" + "Имя: " + governor.getName() + "\n\t\tРост: " + governor.getHeight();
    }

    public String getCSV() {
        return id + "," + name + "," + coordinates.getX() + "," + coordinates.getY() + "," + creationDate + "," + area
                + "," + population + "," + metersAboveSeaLevel + "," + capital + "," + climate + "," + standardOfLiving
                + "," + governor.getName() + "," + governor.getHeight() + "\n";
    }

    @Override
    public int compareTo(Object o) {
        return (int) (this.population - ((City) o).population);
    }
}
