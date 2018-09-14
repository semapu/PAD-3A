
import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;


public class MultiClientServer extends Thread {
    protected MyServerSocket serverSocket;
    protected TreeMap<String,MySocket> workers;
    protected String id;
    final ReentrantReadWriteLock lo;

    public MultiClientServer(int ServerPort) throws IOException {
        serverSocket = new MyServerSocket(ServerPort);
        workers = new TreeMap<String,MySocket>();
        lo = new ReentrantReadWriteLock();
    }

    @Override
    public void run() {
        while (true) {                                                                         
            System.out.println("JavaServer ready");
            lo.readLock().lock();
            MySocket workerSocket = serverSocket.accept();     
            workerSocket.println("Please enter an identification:"); 
            id = workerSocket.readLine();
            System.out.println("Client "+id+" online");
            for (MySocket socket: workers.values()) {            
                socket.println(id + " is online");                        
            }
            try {              
                workers.put(id, workerSocket);                
                Worker worker = new Worker(id, workers);
                new Thread(worker).start();
            	//sendList(worker.updateList(), workerSocket);
            	
            } catch (IOException ex) {
                Logger.getLogger(MultiClientServer.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                lo.readLock().unlock();
            }
        }       
    }
    
    /*public void sendList(Set<String> listUsers, MySocket socket){
    	socket.wrtTreeSet(listUsers);
    }*/
}
