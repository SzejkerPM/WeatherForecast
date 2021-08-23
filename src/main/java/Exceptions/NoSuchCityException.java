package Exceptions;

public class NoSuchCityException extends RuntimeException{

    public NoSuchCityException(String messege) {
        super(messege);
    }

    public NoSuchCityException(String messege, Throwable cause) {
        super(messege, cause);
    }
}
