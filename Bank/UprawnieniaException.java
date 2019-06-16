package Bank;

public class UprawnieniaException extends Exception {
    public String message;

    public UprawnieniaException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
