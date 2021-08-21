package Exceptions;

public class ConversionFromJsonException extends RuntimeException {

    public ConversionFromJsonException(String messege) {
        super(messege);
    }

    public ConversionFromJsonException(String messege, Throwable cause) {
        super(messege, cause);
    }
}
