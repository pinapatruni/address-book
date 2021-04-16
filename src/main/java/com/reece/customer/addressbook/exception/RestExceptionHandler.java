package com.reece.customer.addressbook.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {

        Error errorDetails = new Error();
        errorDetails.setErrorCode(ErrorType.ERROR_VALIDATION.getCode());
        errorDetails.setErrorDesc(ErrorType.ERROR_VALIDATION.getDescription());

        ex.getBindingResult().getAllErrors().stream().forEach(objectError -> {
            DetailedErrorMessage detailedErrorMessage = new DetailedErrorMessage();
            errorDetails.addDetailedErrorMessagesItem(detailedErrorMessage);
            if ( objectError instanceof FieldError) {
                detailedErrorMessage.setAttributeName(((FieldError) objectError).getField());
            } else {
                detailedErrorMessage.setAttributeName(objectError.getObjectName());
            }
            detailedErrorMessage.setErrorDescription(objectError.getDefaultMessage());
        });

        logger.error("Validation error - details={}", errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Error> missingRequestHeaderExceptionResponse(MissingRequestHeaderException ex) {
        Error errorDetails = new Error();
        errorDetails.setErrorCode(ErrorType.ERROR_VALIDATION.getCode());
        errorDetails.setErrorDesc(ex.getMessage());
        DetailedErrorMessage detailedErrorMessage = new DetailedErrorMessage();
        errorDetails.addDetailedErrorMessagesItem(detailedErrorMessage);
        detailedErrorMessage.setAttributeName(ex.getHeaderName());
        detailedErrorMessage.setErrorDescription("missing header");
        detailedErrorMessage.setUserErrorDescription("missing header");

        logger.error("Validation error details={}", errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Error> serviceExceptionResponse(ServiceException ex) {
        Error errorDetails = new Error();
        errorDetails.setErrorCode(ErrorType.GENERIC_SYSTEM_ERROR.getCode());
        errorDetails.setErrorDesc(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> resourceNotFoundExceptionResponse(ResourceNotFoundException ex) {
        Error errorDetails = new Error();
        errorDetails.setErrorCode(ErrorType.ERROR_RESOURCE_NOT_FOUND.getCode());
        errorDetails.setErrorDesc(ex.getMessage());
        logger.error("ResourceNotFoundException details={}", errorDetails.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exceptionResponse(Exception ex) {
        return genericExceptionResponse(ex);
    }


    public ResponseEntity<Error> genericExceptionResponse(Exception ex) {
        logger.error("errorMessage={}", ex.getMessage(), ex);
        Error errorDetails = new Error();
        errorDetails.setErrorCode(ErrorType.GENERIC_SYSTEM_ERROR.getCode());
        errorDetails.setErrorDesc(ErrorType.GENERIC_SYSTEM_ERROR.getDescription());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

