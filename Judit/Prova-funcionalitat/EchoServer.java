
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 *
 * @author Judit & Dani
 */

public class EchoServer{ //Servidor Echo va enviant les linies que reb
    
    final int port = 22222;
    static ConcurrentHashMap <String, MySocket> users;
    final MyServerSocket ss;
    static SynchronousQueue<String> users_list;
    
    public EchoServer(int port){
    
        ss= new MyServerSocket(port);
        users = new ConcurrentHashMap<>();
        users_list= new SynchronousQueue<>();
    }

	public static void main(String []args) {
		
               	
                final EchoServer server= new EchoServer(22222);
                
                
                System.out.println("Servidor Arrancado");
		while(true){
			
                final MySocket s=server.ss.accept();
                
			new Thread() { //Classe anònima	
                            String line=null;
                            String id;
                            @Override
				public void run(){
                                
                                System.out.println("THREAD INICIAT");    
				whois();
                                //refreshUserList();
                                    while ((line=s.read_str()) != null){
                                    
					broadcastMessage(line,s);
                                    }
                                       
                                disconect(); 
                                }
                                
                                public void whois(){
                                    
                                     while(((users.containsKey (id=s.read_str()))==true)||(id.isEmpty())){
                                        
                                        if(id.isEmpty()) s.write_int(101); //on 101 error no user writed
                                        
                                        else s.write_int(10); // 10 error user exixt
                                        
                                     }
                                        s.write_int(11); //on 11 es OK
                                        
                                        server.users.put(id, s);
                                        
                                        System.out.println("S'ha conectat l'usuari " + id);
                                        //users_list.add(id);
                                        
                                     
                                }
                                
                                public void refreshUserList(){
                                    
                                    s.write_int(20);
                                    //s.write_str(users);
                                    
                                    
                                }
                                
                                public void broadcastMessage(String message, MySocket soc){
                                MySocket tempSoc;
                                
                                    
                                    for(Map.Entry<String, MySocket> entry : users.entrySet()) {
                                        if ((tempSoc = entry.getValue()) != soc) {
                                          tempSoc.write_str(id +": "+ message);
                                         }
                                      }
                                }
                                
                                public void disconect(){
                                    
                                    System.out.println("Socket tancat = Client "+ id +" desconectat");
                                    server.users.remove(id);
                                    s.close();
                                }
			}.start();	
		}
	}

}
