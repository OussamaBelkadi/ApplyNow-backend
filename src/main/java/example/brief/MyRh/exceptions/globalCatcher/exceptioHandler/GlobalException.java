package example.brief.MyRh.exceptions.globalCatcher.exceptioHandler;

import example.brief.MyRh.exceptions.costumeHeaders.ErrorDetails;
import example.brief.MyRh.exceptions.costumeHeaders.Headerdesign;
import example.brief.MyRh.exceptions.exception.ExpiredAttempt;
import example.brief.MyRh.exceptions.exception.LoginSocieteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.exceptions.exception.OffreCreateException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(OffreCreateException.class)
    public ResponseEntity<String> handlePostSaveException(OffreCreateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredAttempt.class)
    public ResponseEntity<String> handleExpiredException(ExpiredAttempt ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(NotExist.class)
    public ResponseEntity<String> handleTheExisting(NotExist ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoginSocieteException.class)
    public ResponseEntity<String> HandleLogin(LoginSocieteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
