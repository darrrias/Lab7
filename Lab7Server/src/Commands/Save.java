package Commands;

import Controller.CommandWithoutArg;
import Controller.CollectionCity;
import Connection.DataBaseController;
import DataBase.CitiesDataBase;

import java.io.IOException;
import java.sql.SQLException;

public class Save implements CommandWithoutArg {

    @Override
    public Object execute(Object arg) throws IOException, SQLException {
        CitiesDataBase humansDataBase= DataBaseController.getcitiesDataBase();
        humansDataBase.loadCollection(CollectionCity.cities);
        System.out.println("Коллекция успешно сохранена.");
		return null;
    }

    @Override
    public String getName() {
        return "save";
    }
}