package tn.esprit.spring.exceptions;

public class InvalidDatabaseOperationException extends Exception{
    public InvalidDatabaseOperationException(Exception e){
        super(e);
    }
}
