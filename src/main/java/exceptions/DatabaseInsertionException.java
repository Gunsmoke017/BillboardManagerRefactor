package exceptions;

public class DatabaseInsertionException extends RuntimeException{
    public DatabaseInsertionException(String message){
        super(message);
    }
}
