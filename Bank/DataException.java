package Bank;

public class DataException extends Exception {
    public String message;

    public DataException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
