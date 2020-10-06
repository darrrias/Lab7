package CityObject;


import Controller.CollectionCity;
import Controller.ScriptException;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CityBuilder {
    public static boolean isInScript;
    private final static Scanner in = new Scanner(System.in);
    public static City cityFromFile;

    private static String read() {
        System.out.print("~ ");
        return in.nextLine();
    }

    public static City createCity() {
        City city = new City();
        //city.setId(CollectionCity.getFreeId());
        setName(city);
        Coordinates coordinates = new Coordinates();
        setX(coordinates);
        setY(coordinates);
        city.setCoordinates(coordinates);
        setArea(city);
        setPopulation(city);
        setMetersAboveSeaLevel(city);
        setCapital(city);
        setClimate(city);
        setStandardOfLiving(city);
        Human governor = new Human();
        setGovernorName(governor);
        setGovernorHeight(governor);
        city.setGovernor(governor);
        city.setCreationDate(LocalDate.now());
        return city;
    }

    public static void createFromFile(String[] params) {
        try {
            cityFromFile = new City();
            cityFromFile.setId(CollectionCity.getFreeId());
            setName(cityFromFile, params[0]);
            Coordinates coordinates = new Coordinates();
            setX(coordinates, params[1]);
            setY(coordinates, params[2]);
            cityFromFile.setCoordinates(coordinates);
            setArea(cityFromFile, params[3]);
            setPopulation(cityFromFile, params[4]);
            setMetersAboveSeaLevel(cityFromFile, params[5]);
            setCapital(cityFromFile, params[6]);
            setClimate(cityFromFile, params[7]);
            setStandardOfLiving(cityFromFile, params[8]);
            Human governor = new Human();
            setGovernorName(governor, params[9]);
            setGovernorHeight(governor, params[10]);
            cityFromFile.setGovernor(governor);
            cityFromFile.setCreationDate(LocalDate.now());
        } catch (ScriptException e) {
            System.out.println("Возникла ошибка: " + e.getMistake());
            cityFromFile = null;
        }
    }

    public static void setName(City city) {
        System.out.println("Имя города:");
        String name = read();
        if (name.equals("")) setName(city);
        city.setName(name);
    }

    public static void setX(Coordinates coordinates) {
        try {
            System.out.println("Координата x:");
            String x = read();
            if (x.equals("") || x.equals(null)) setX(coordinates);
            int xn = Integer.parseInt(x);
            coordinates.setX(xn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"int\".");
            setX(coordinates);
        }
    }

    public static void setY(Coordinates coordinates) {
        try {
            System.out.println("Координата y:");
            String y = read();
            if (y.equals("") || y.equals(null)) setY(coordinates);
            Double yn = Double.valueOf(y);
            if (yn > 192) {
                System.out.println("Максимальное значение поля \"у\" - 192");
                setY(coordinates);
            }
            coordinates.setY(yn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"Double\".");
            setY(coordinates);
        }
    }

    public static void setArea(City city) {
        try {
            System.out.println("Площадь:");
            String area = read();
            if (area.equals("") || area.equals(null)) setArea(city);
            Double yn = Double.valueOf(area);
            if (yn < 0) {
                System.out.println("Значение поля \"площадь\" не может быть отрицательным");
                setArea(city);
            }
            city.setArea(yn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"Double\".");
            setArea(city);
        }
    }

    public static void setPopulation(City city) {
        try {
            System.out.println("Численность населния:");
            String area = read();
            if (area.equals("") || area.equals(null)) setPopulation(city);
            long yn = Long.parseLong(area);
            if (yn < 0) {
                System.out.println("Значение поля \"население\" не может быть отрицательным");
                setPopulation(city);
            }
            city.setPopulation(yn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"long\".");
            setPopulation(city);
        }
    }

    public static void setMetersAboveSeaLevel(City city) {
        try {
            System.out.println("Высота над уровнем моря(в метрах):");
            String y = read();
            if (y.equals("")) setMetersAboveSeaLevel(city);
            Long yn = Long.valueOf(y);
            city.setMetersAboveSeaLevel(yn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"Long\".");
            setMetersAboveSeaLevel(city);
        }
    }

    public static void setCapital(City city) {
        try {
            System.out.println("Есть ли столица?(true/false):");
            String y = read();
            if (y.equals("")) city.setCapital(null);
            if (y.toLowerCase().equals("false") || y.toLowerCase().equals("true")) {
                Boolean yn = Boolean.valueOf(y);
                city.setCapital(yn);
            } else setCapital(city);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"Boolean\".");
            setCapital(city);
        }
    }

    public static void setClimate(City city) {
        System.out.println("Введите климат(OCEANIC, STEPPE, SUBARCTIC, TUNDRA, POLAR_ICECAP):");
        System.out.print("~ ");
        String climate = in.nextLine().toUpperCase();
        try {
            city.setClimate(Climate.valueOf(climate.toUpperCase()));
        } catch (Exception e) {
            System.out.println("Значение поля должно соответствовать перечислинным типам.");
            setClimate(city);
        }
    }

    public static void setStandardOfLiving(City city) {
        System.out.println("Введите уровень жизни(VERY_HIGH, HIGH, VERY_LOW):");
        System.out.print("~ ");
        String climate = in.nextLine().toUpperCase();
        try {
            city.setStandardOfLiving(StandardOfLiving.valueOf(climate.toUpperCase()));
        } catch (Exception e) {
            System.out.println("Значение поля должно соответствовать перечислинным типам.");
            setStandardOfLiving(city);
        }
    }

    public static void setGovernorName(Human governor) {
        System.out.println("Имя мэра города:");
        String name = read();
        if (name.equals("")) setGovernorName(governor);
        governor.setName(name);
    }

    public static void setGovernorHeight(Human governor) {
        try {
            System.out.println("Рост мэра(в сантиметрах):");
            String y = read();
            if (y.equals("")) setGovernorHeight(governor);
            Long yn = Long.valueOf(y);
            governor.setHeight(yn);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Значение должно быть типа:\"Long\".");
            setGovernorHeight(governor);
        }
    }

    public static void setName(City city, String name) throws ScriptException {
        if (name.equals("")) throw new ScriptException("Нет имени");
        city.setName(name);
    }

    public static void setX(Coordinates coordinates, String x) throws ScriptException {
        try {
            if (x.equals("") || x.equals(null)) throw new ScriptException("Нет координаты х");
            int xn = Integer.parseInt(x);
            coordinates.setX(xn);
        } catch (InputMismatchException | NumberFormatException e) {
            throw new ScriptException("Значение x должно быть типа:\"int\".");
        }
    }

    public static void setY(Coordinates coordinates, String y) throws ScriptException {
        try {
            if (y.equals("") || y.equals(null)) throw new ScriptException("Нет координаты y");
            Double yn = Double.valueOf(y);
            if (yn > 192) {
                throw new ScriptException("Максимальное значение поля \"у\" - 192");
            }
            coordinates.setY(yn);
        } catch (InputMismatchException | NumberFormatException e) {
            throw new ScriptException("Значение y должно быть типа:\"Double\".");
        }
    }

    public static void setArea(City city, String area) throws ScriptException {
        try {
            if (area.equals("") || area.equals(null)) throw new ScriptException("Нет площади");
            Double yn = Double.valueOf(area);
            if (yn < 0) {
                throw new ScriptException("Значение поля \"площадь\" не может быть отрицательным");
            }
            city.setArea(yn);
        } catch (InputMismatchException | NumberFormatException e) {
            throw new ScriptException("Значение поля \"площадь\" должно быть типа:\"Double\".");
        }
    }

    public static void setPopulation(City city, String area) throws ScriptException {
        try {
            if (area.equals("") || area.equals(null)) throw new ScriptException("Нет численности населения");
            long yn = Long.parseLong(area);
            if (yn < 0) {
                throw new ScriptException("Значение поля \"население\" не может быть отрицательным");
            }
            city.setPopulation(yn);
        } catch (InputMismatchException | NumberFormatException e) {
            throw new ScriptException("Значение должно быть типа:\"long\".");
        }
    }

    public static void setMetersAboveSeaLevel(City city, String y) throws ScriptException {
        try {
            if (y.equals("")) throw new ScriptException("Нет высоты над уровнем моря");
            Long yn = Long.valueOf(y);
            city.setMetersAboveSeaLevel(yn);
        } catch (InputMismatchException | NumberFormatException e) {
            throw new ScriptException("Значение поля \"высота над уровнем моря\" должно быть типа:\"Long\".");
        }
    }

    public static void setCapital(City city, String y) throws ScriptException {
        try {
            if (y.equals("")) city.setCapital(null);
            if (!y.toLowerCase().equals("false") || !y.toLowerCase().equals("true")) {
                Boolean yn = Boolean.valueOf(y);
                city.setCapital(yn);
            } else throw new ScriptException("Значение поля \"есть ли столица?\" должно быть типа:\"Boolean\".");
        } catch (InputMismatchException | NumberFormatException e) {
            throw new ScriptException("Значение поля \"есть ли столица?\" должно быть типа:\"Boolean\".");
        }
    }

    public static void setClimate(City city, String climate) throws ScriptException {
        climate = climate.toUpperCase();
        try {
            city.setClimate(Climate.valueOf(climate.toUpperCase()));
        } catch (Exception e) {
            throw new ScriptException("Значение поля \"климат\" должно соответствовать перечислинным типам(OCEANIC, STEPPE, SUBARCTIC, TUNDRA, POLAR_ICECAP).");
        }
    }

    public static void setStandardOfLiving(City city, String climate) throws ScriptException {
        climate = climate.toUpperCase();
        try {
            city.setStandardOfLiving(StandardOfLiving.valueOf(climate.toUpperCase()));
        } catch (Exception e) {
            throw new ScriptException("Значение поля \"уровень жизни\" должно соответствовать перечислинным типам(VERY_HIGH, HIGH, VERY_LOW).");
        }
    }

    public static void setGovernorName(Human governor, String name) throws ScriptException {
        if (name.equals("")) throw new ScriptException("Нет имени мэра города");
        governor.setName(name);
    }

    public static void setGovernorHeight(Human governor, String y) {
        try {
            if (y.equals("")) throw new ScriptException("Нет роста мэра города");
            Long yn = Long.valueOf(y);
            governor.setHeight(yn);
        } catch (InputMismatchException | NumberFormatException | ScriptException e) {
            System.out.println("Значение поля \"Рост мэра(в сантиметрах)\" должно быть типа:\"Long\".");
            setGovernorHeight(governor);
        }
    }

}
