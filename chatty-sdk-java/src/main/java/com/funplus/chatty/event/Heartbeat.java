/**
 * 
 */
package com.funplus.chatty.event;

/**
 * @author Weihua Fan
 *
 */
public class Heartbeat {

    private String error;

    public Heartbeat() {
        
    }
    
    public Heartbeat(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
