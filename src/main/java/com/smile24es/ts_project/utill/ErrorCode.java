package com.smile24es.ts_project.utill;

/**
 * The class to hold error codes for Permission API
 */
public final class ErrorCode {

    private ErrorCode() {
        //Disallow instantiation
    }

    public static final String UNEXPECTED_ERROR = "10000";
    public static final String INVALID_PARAMETERS = "10100";
    public static final String EMPTY_RESULT = "10200";
    
    public static final String ERROR_IN_CONNECTING_NAIVE = "20000";
    
}
