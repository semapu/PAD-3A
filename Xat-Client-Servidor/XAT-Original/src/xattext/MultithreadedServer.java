/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xattext;

import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import xattext.utils.Comms;

/**
 *
 * @author sergio
 */
public class MultithreadedServer extends Thread {
    protected MyServerSocket welcomeSocket;
    protected TreeMap<String,MySocket> workers;
    protected String id;
    final ReentrantReadWriteLock lo;

    public MultithreadedServer() throws IOException {
        welcomeSocket = new MyServerSocket(Comms.portServidor);
        workers = new TreeMap<String,MySocket>();
        lo = new ReentrantReadWriteLock();
    }

    @Override
    public void run() {
        while (true) {                                                                         
            System.out.println("Server ready");
            lo.readLock().lock();
            MySocket workerSocket = welcomeSocket.accept();      
            workerSocket.println("Enter an identification:"); 
            id = workerSocket.readLine();
            System.out.println("Client "+id+" connected");
            for (MySocket socket: workers.values()) {            
                socket.println(id + " is now connected!");                        
            }
            try {              
                workers.put(id, workerSocket);                
                Worker worker = new Worker(workers, id);
                new Thread(worker).start();
            
            } catch (IOException ex) {
                Logger.getLogger(MultithreadedServer.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                lo.readLock().unlock();
            }

        }
    }
}
