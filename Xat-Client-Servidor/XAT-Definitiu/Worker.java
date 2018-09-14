
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
    	String line;
    	boolean on = true;
        while (on) {
            line = socket.readLine();           
	    for (Map.Entry<String,MySocket> entry : connections.entrySet()){ 
		//1ª parameter TreeMap
		String idMap = entry.getKey();
		if(line.equals("\u0000") && (idMap != id)){
		    on = false;
		    entry.getValue().println(id + " Disconneted!!");
		}else if (idMap != id){
		    //2º parameter TreeMap
		    entry.getValue().println(id + "-->" + line);
		}
	    }
        }
	socket.println("\u0000");
	socket.close();
	connections.remove(id);
    }
}

