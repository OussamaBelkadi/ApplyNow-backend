package example.brief.MyRh.exceptions.exception;

public class ExpiredAttempt extends RuntimeException{
    public ExpiredAttempt(String message){
        super(message);
    }
}
