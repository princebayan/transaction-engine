package com.example.transactionengine.shared;

import org.springframework.http.HttpStatus;

/**
 * The type Base response.
 *
 * @param <T> the type parameter
 */
public class ResponseBody<T> {
    private int status;
    private String message;
    private T response;

    private ResponseBody(int status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    /**
     * Instantiates a new Response body.
     */
    public ResponseBody() {

    }

    /**
     * Success base response.
     *
     * @param <T>      the type parameter
     * @param response the response
     * @return the base response
     */
    public static <T> ResponseBody<T> success(T response) {
        return new ResponseBody<>(HttpStatus.OK.value(), "Success", response);
    }

    public static <T> ResponseBody<T> failedError(T apiError, int httpStatusCode, String httpMessage) {
        return new ResponseBody<>(httpStatusCode, httpMessage, apiError);
    }

    /**
     * Failed bad request base response.
     *
     * @param <T> the type parameter
     * @return the base response
     */
    public static <T> ResponseBody<T> failedBadRequest() {
        return new ResponseBody<>(HttpStatus.BAD_REQUEST.value(), "Bad request", null);
    }

    /**
     * Failed bad request base response.
     *
     * @param <T>      the type parameter
     * @param apiError the api error
     * @return the base response
     */
    public static <T> ResponseBody<T> failedBadRequest(T apiError) {
        return new ResponseBody<>(HttpStatus.BAD_REQUEST.value(), "Bad request", apiError);
    }

    /**
     * Failed Un processable Entity error response
     *
     * @param <T>      the type parameter
     * @param apiError the api error
     * @return response body
     */
    public static <T> ResponseBody<T> failedUnprocessableEntity(T apiError) {
        return new ResponseBody<>(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Un processable entity error", apiError);
    }

    /**
     * Failed internal error base response.
     *
     * @param <T> the type parameter
     * @return the base response
     */
    public static <T> ResponseBody<T> failedInternalError() {
        return new ResponseBody<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal error", null);
    }

    /**
     * Failed error base response.
     *
     * @param <T>        the type parameter
     * @param httpStatus the http status
     * @param message    the message
     * @return the base response
     */
    public static <T> ResponseBody<T> failedError(HttpStatus httpStatus, String message) {
        return new ResponseBody<>(httpStatus.value(), message, null);
    }

    /**
     * Failed error response body.
     *
     * @param <T>                  the type parameter
     * @param customHttpStatusCode the custom http status code
     * @param message              the message
     * @return the response body
     */
    public static <T> ResponseBody<T> failedError(int customHttpStatusCode, String message) {
        return new ResponseBody<>(customHttpStatusCode, message, null);
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets response.
     *
     * @return the response
     */
    public T getResponse() {
        return response;
    }
}
