package basepackage.exceptionhandling;

import basepackage.errorresponse.ErrorResponse;
import basepackage.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = DatabaseDuplicateEntryException.class)
    public final ResponseEntity<Object> handleDatabaseDuplicateEntryException(DatabaseDuplicateEntryException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        String path = "Class : " + ex.getStackTrace()[0].getClassName();
        String line = " Line : " + Integer.toString(ex.getStackTrace()[0].getLineNumber());
        String location = path + line;
        ErrorResponse error = new ErrorResponse("Database conflict..",location,HttpStatus.BAD_REQUEST.value(),details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidEntryException.class)
    public final ResponseEntity<Object> handleInvalidEntryException(InvalidEntryException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        String path = "Class : " + ex.getStackTrace()[0].getClassName();
        String line = " Line : " + Integer.toString(ex.getStackTrace()[0].getLineNumber());
        String location = path + line;
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Invalid entry...",location,HttpStatus.BAD_REQUEST.value(),details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntryNotFoundException.class)
    public final ResponseEntity<Object> handleEntryNotFoundException(EntryNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        String path = "Class : " + ex.getStackTrace()[0].getClassName();
        String line = " Line : " + Integer.toString(ex.getStackTrace()[0].getLineNumber());
        String location = path + line;
        ErrorResponse error = new ErrorResponse("Entry not found..",location,HttpStatus.NOT_FOUND.value(),details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AuthorizationFailedException.class)
    public final ResponseEntity<Object> handleAuthorizationFailedException(AuthorizationFailedException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        String path = "Class : " + ex.getStackTrace()[0].getClassName();
        String line = " Line : " + Integer.toString(ex.getStackTrace()[0].getLineNumber());
        String location = path + line;
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Authorization failed...",location,HttpStatus.UNAUTHORIZED.value(),details);
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = NoActiveSessionFoundException.class)
    public final ResponseEntity<Object> handleNoActiveSessionFoundException(NoActiveSessionFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        String path = "Class : " + ex.getStackTrace()[0].getClassName();
        String line = " Line : " + Integer.toString(ex.getStackTrace()[0].getLineNumber());
        String location = path + line;
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("No active session found...",location,HttpStatus.NOT_FOUND.value(),details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ForbiddenRequestException.class)
    public final ResponseEntity<Object> handleForbiddenRequestException(ForbiddenRequestException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        String path = "Class : " + ex.getStackTrace()[0].getClassName();
        String line = " Line : " + Integer.toString(ex.getStackTrace()[0].getLineNumber());
        String location = path + line;
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("This is not a legit request...",location,HttpStatus.FORBIDDEN.value(),details);
        return new ResponseEntity(error, HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        String path = "Class : " + ex.getStackTrace()[0].getClassName();
        String line = " Line : " + Integer.toString(ex.getStackTrace()[0].getLineNumber());
        String location = path + line;

        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed",location,HttpStatus.BAD_REQUEST.value(),details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
