package tdatphan.exeception;

public class OutOfMapException extends IndexOutOfBoundsException {
    public OutOfMapException(int x, int y) {
        super("ERROR: Out of map at coordinates (" + x + ", " + y + ")");
    }
}
