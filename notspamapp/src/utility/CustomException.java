/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author rakeshv
 */
public class CustomException extends Exception {

    private String errorCode;

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
