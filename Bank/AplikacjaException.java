package Bank;

public class AplikacjaException extends Exception {
    public String message;

    public AplikacjaException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
