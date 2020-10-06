package Commands;

import Controller.CommandWithoutArg;
import Controller.CollectionCity;

/**
 * show info about collection
 *
 * @author Polina
 */
public class Info implements CommandWithoutArg {
    public String getName() {
        return "info";
    }

    /**
     * @param arg ignore this
     * @return
     */
    public Object execute(Object arg) {
        return "";
    }
}