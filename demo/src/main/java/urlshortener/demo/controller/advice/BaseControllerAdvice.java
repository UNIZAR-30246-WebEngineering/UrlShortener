package urlshortener.demo.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import urlshortener.demo.exception.CannotAddEntityException;
import urlshortener.demo.exception.IncorrectHashPassException;
import urlshortener.demo.exception.InvalidRequestParametersException;
import urlshortener.demo.exception.UnknownEntityException;

@ControllerAdvice
public class BaseControllerAdvice {
    /**
     * Handles any InvalidWelcomeMessageException thrown by any controller method.
     * If any controller method throws that exception, this one is executed to return a message
     * 	with a BAD_REQUEST (400) error code.
     */
    @ExceptionHandler({CannotAddEntityException.class, InvalidRequestParametersException.class, IncorrectHashPassException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void cannotAddEntityHandler(Throwable e){
        //No implementation given due to Spring providing it
    }

    /*
     * Handles any InvalidWelcomeMessageException thrown by any controller method.
     * If any controller method throws that exception, this one is executed to return a message
     * 	with a BAD_REQUEST (400) error code.
     */
    @ExceptionHandler(UnknownEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void unknownEntityHandler(UnknownEntityException e){
        //No implementation given due to Spring providing it
    }
}
