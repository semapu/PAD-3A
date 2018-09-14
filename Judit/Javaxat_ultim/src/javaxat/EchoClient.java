package javaxat;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Judit & Dani
 */

public class EchoClient{ 
    
    public static MySocket soc;
    static String nick;
    static String line;
    static int serverOrder;
    
    
    public EchoClient(){
        
        soc= new MySocket("localhost", 22222);
        
    }

    public static void main(String []args) {
		
		                                                                  
        EchoClient client = new EchoClient();            
         
        runKeyboardListener();
        login();
	
    }
    
    public static void login(){
        
        System.out.println("Entra el teu nom d'usuari: ");
        while((serverOrder=soc.read_int())!=11){
        
            switch(serverOrder){
                case 10:    showLoginError();
                            break;
                case 101:   showNoNickError();
                            break;
                default:    break;
            }
            System.out.println("Entra el teu nom d'usuari: ");
        }
        listen();
    }
    
    public static void listen(){
        //Server listener thread
                
		new Thread() {
                    @Override
                    public void run(){
			
                                
                        while((line=soc.read_str()) != null){
                            
                            System.out.println(line + "\n");
                            }
                             
                                        
                    }
				
	}.start();
    }
    
    // Keyboard thread
    public static void runKeyboardListener(){
        new Thread() {
            @Override        
            public void run(){ // 
                BufferedReader buff_in = new BufferedReader(new InputStreamReader(System.in));
		
		try{				
                     
                    while(((line=buff_in.readLine()) != null)){
                        
                        if(!(line.contentEquals("exit"))) {soc.write_str(line);}
                            
                        else { 
                            System.out.println("Vols Sortir Del Xat? (y/n)");
                            
                            if(buff_in.readLine().contentEquals("y")){
                                
                                soc.write_str("quit");
                                System.out.println("SORTINT DEL XAT");
                                soc.close();
                                
                            }
                        }
                    }
                                        
				//close for writing
				// ...
		} catch (IOException ex){}
            }
	
	}.start();
    }
    
    public static void exit(){
       
       

               
    }
    
    public static void showLoginError(){
        
        System.out.println("¡Aquest nom d'usuari ja està en us!");
        
    }
    public static void showNoNickError(){
        
        System.out.println("¡Has d'entroduir algun nom d'usuari per entrar al Xat!");
    }
    
}
