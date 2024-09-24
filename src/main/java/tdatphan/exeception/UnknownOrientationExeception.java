package tdatphan.exeception;

public class UnknownOrientationExeception extends RuntimeException {

    public UnknownOrientationExeception(){
        super("ERROR: Unknown orientation");
    }
    public UnknownOrientationExeception(char orientation){
        super("ERROR: Unknown orientation '" + orientation + "'");
    }
}
