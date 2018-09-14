/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xattext;

import java.io.*;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author sergio
 */
public class Worker implements Runnable {
    protected MySocket workerSocket;                        
    protected String id;                                                     
    protected TreeMap<String,MySocket> connected;
    
    public Worker(TreeMap<String,MySocket> map, String id) throws IOException {
        this.workerSocket = map.get(id);                     
        this.id = id;                                                     
        this.connected = map;

        
    } 

    @Override
    public void run() {
        while (true) {
            String dades = workerSocket.readLine();  
            String missatge = id + "> " + dades;
            for (MySocket socket: connected.values()) {                 
                socket.println(missatge);                  
            }            
        }
    }
}

