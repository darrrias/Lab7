package Commands;

import Controller.CommandWithoutArg;

import java.io.IOException;

public class Exit implements CommandWithoutArg {
    @Override
    public Object execute(Object object) throws IOException {
        System.exit(0);
        return null;
    }

    @Override
    public String getName() {
        return "exit";
    }
}
