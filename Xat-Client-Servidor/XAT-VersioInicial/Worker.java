
import java.io.*;
import java.util.*;


public class Worker implements Runnable {
    protected MySocket socket;                        
    protected String id;                                                     
    protected TreeMap<String,MySocket> connections;
    
    public Worker(String id, TreeMap<String,MySocket> connection) throws IOException {                   
        this.id = id;                                                     
        this.connections = connection;
        this.socket = connection.get(id); 
    } 

    @Override
    public void run() {
        while (true) {
            String line = socket.readLine();  
	    for (Map.Entry<String,MySocket> entry : connections.entrySet()){ 
		String idMap = entry.getKey();
		if (idMap != id){
		    //2º Paràmetre del TreeMap
		    entry.getValue().println(id + "-->" + line);
		}
	    }
        }
    }
}

