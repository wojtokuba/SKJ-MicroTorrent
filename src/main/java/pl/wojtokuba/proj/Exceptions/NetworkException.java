package pl.wojtokuba.proj.Exceptions;

public class NetworkException extends Exception{
    String message;
    public NetworkException(String msg) {
        this.message = msg;
    }

    public NetworkException() {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
