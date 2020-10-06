package Controller;

public class ScriptException extends Exception{
    public String mistake;
    public ScriptException(String mistake){
        this.mistake = mistake;
    }

    public String getMistake() {
        return mistake;
    }
}
