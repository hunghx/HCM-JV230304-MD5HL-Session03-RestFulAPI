package ra.demoapi.exception;

public class DupplicateException  extends RuntimeException{
    public DupplicateException(String message) {
        super(message);
    }
}
