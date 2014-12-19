/**
 * 
 */
package com.funplus.chatty.test;

import com.funplus.chatty.ChatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Weihua Fan
 *
 */
public class Main {
    
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient("192.168.21.49", 8080);
        LoginHandler loginHandler = new LoginHandler(client);
        client.addSubscriber(loginHandler);
        
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            String[] req = line.split(":");
            switch(req[0]) {
            case "login":
                client.login(req[1], req[2]);
                break;
            case "prm":
                client.sendPrivateMessage(req[2], Integer.valueOf(req[1]));
                break;
            case "pum":
                client.sendPublicMessage(req[1]);
                break;
            default:
                System.err.println("Invalid command");
            }
            
        }
    }

}
