/**
 * 
 */
package com.funplus.chatty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Weihua Fan
 *
 */
public class Bootstrap {
    
    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);
    
    public static void main(String[] args) {
        ChatServer cs = new ChatServer(8080);
        try {
            cs.run();
        } catch (Exception e) {
            log.error("An error occurred during the Server boot, preventing it to start.");
        }
    }

}
