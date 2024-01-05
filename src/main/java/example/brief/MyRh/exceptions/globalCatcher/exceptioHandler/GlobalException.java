package example.brief.MyRh.exceptions.globalCatcher.exceptioHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.exceptions.exception.OffreCreateException;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(OffreCreateException.class)
    public ResponseEntity<String> handlePostSaveException(OffreCreateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotExist.class)
    public ResponseEntity<String> handleTheExisting(NotExist ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
