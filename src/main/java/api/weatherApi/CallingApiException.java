package api.weatherApi;

public class CallingApiException extends RuntimeException {

    public CallingApiException(String messege) {
        super(messege);
    }

    public CallingApiException(String messege, Throwable cause) {
        super(messege, cause);
    }
}
