package Controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * interface for all commands
 * @author Diana
 */
public interface Commandable extends Serializable {
	Object execute(Object object) throws IOException, SQLException, ParseException;
	String getName();
}
