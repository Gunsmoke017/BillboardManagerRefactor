package exceptions;

public class InvalidKeyEnteredException extends RuntimeException{
    public InvalidKeyEnteredException(String message){
        super(message);
    }
}
