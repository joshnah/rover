package tdatphan.exeception;

public class UnknownCommandException extends RuntimeException {

    public UnknownCommandException(char command) {
        super("ERROR: Unknown command '" + command + "'");
    }
    
}