package com.example.transactionengine.shared;

import javax.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * This class is responsible of overriding certain errors thrown by Spring boot to return custom
 * response instead of Spring's detailed response.
 */
@RestControllerAdvice
@Log4j2
public class SharedExceptionHandler {


  /**
   * Handle media type not supported exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<?> handleMediaTypeNotSupportedException(
      HttpMediaTypeNotSupportedException ex) {
    log.error("Handle Media Type Not Supported Exception", ex);
    /*
    Build Error
     */
    String errorMessage = "Unsupported media type";
    /*
    Return Response
     */
    ResponseBody<?> responseBody = ResponseBody
        .failedError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, errorMessage);
    return new ResponseEntity<>(responseBody, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  /**
   * Handle not found exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler({HttpMessageNotReadableException.class,
      MethodArgumentNotValidException.class,
      HttpRequestMethodNotSupportedException.class,
      IllegalArgumentException.class,
      UnsupportedOperationException.class,
      MissingServletRequestParameterException.class,
      MissingRequestHeaderException.class,
      NoHandlerFoundException.class,
      ConstraintViolationException.class,
      MethodArgumentTypeMismatchException.class,
      MissingPathVariableException.class,
      IllegalArgumentException.class})
  public ResponseEntity<?> handleNotFoundException(Exception ex) {
    log.error("Bad request ", ex);
    /*
    Return Response
     */
    ResponseBody<?> responseBody = ResponseBody.failedError(HttpStatus.BAD_REQUEST, "Bad request");
    return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
  }

  /**
   * Exception handler response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> exceptionHandler(Exception ex) {
    log.error("Handling General Exception ", ex);
    ResponseBody<?> responseBody = ResponseBody
        .failedError(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing the request");
    return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
