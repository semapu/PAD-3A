package javaxat;

import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;


/**
 *
 * @author Judit & Dani
 */


 /*   
   
    
class Client{
    def nick;
    def line;
    


    Client(){
         
        socket= new Socket("localhost", 22222)
        InputStream sin = socket.inputStream
        OutputStream sout = socket.outputStream
        
    }

    public static void main(String []args) 
    {	                                                                  
        def client = new Client() 
        metode()
        runKeyboardListener(client.soc)
        login(client.soc)
        println "ok! ja ets dins"
    }
	
    
    
    def metode()
    {
        def runKeyboardListener= 
        {socket
            -> start("runkeyboard", {->   
                //buff_in = new BufferedReader();
		try{				
                     
                    while((line=sin.read()) != null)
                    {
                        if(line=="exit") {sout.write(line);}
                            
                        else 
                        { 
                            println "Vols Sortir Del Xat? (y/n)"
                            
                            if(buff_in.getText().contentEquals("y"))
                            {
                                println "SORTINT DEL XAT"
                                this.finalize()
                                socket.close()
                                break;
                            }
                        }
                    }
		} catch (IOException ex){}          })
        }
    
        def listener= 
        {socket-> start("listener",{->      
                        while((line=socket.read_str()) != null)
                        {
                            println line
                        }                    })
        }
        def login=
        {socket->
            println "Entra el teu nom d'usuari: "
            def x
            while(x=(socket.read_int()) != 11)
            {
                    if(x==10)  
                    {
                        println "¡Aquest nom d'usuari ja està en us!" 
                        break
                    }

                    if(x==101) 
                    {
                        println "¡Has d'entroduir algun nom d'usuari per entrar al Xat!"
                        break
                    }
            }
            listener(soc)  
        }
    }
    }   
    
*/

import java.net.*  
import java.net.Socket
import java.awt.BorderLayout as BL
import javax.swing.WindowConstants as WC
import java.io.*
import java.lang.*



class Client {
    

  
 //def swing = new SwingBuilder()
 //def static content = new StringBuffer()
 
    
 def static soc, serverOrder, nick, line 

 
 //def static Thread teclat,listener
 //def remote = new Proxy('http://localhost:22222/')
 
    
    static void main(String[] args) {
        
        soc = new Socket("localhost",22222)
        BufferedReader buff_in = new BufferedReader(new InputStreamReader(System.in));
        
            
            teclat(buff_in)
            println "3"
            login()
            
        soc.close()
    }
    
    
    def static teclat = { 
        buff_in ->
        Thread.start{
        println "1"
        while((line=buff_in.readLine()) != null){
             soc<< "$line"
             println "2"
        }
    
        }
    }
    
    def static listen(){ 
        println "5"
        Thread.start{
            println "Thread Usuari Escoltant INCIAT"    
         
            
            while((soc<<line) != null){
                println "$line"
            
            }       
        }
    }
    def static login = { 
        println "Entra el teu nom d'usuari:b "
        
        
            while((serverOrder=soc.read_int()) != 11){
                println "4"
                switch(serverOrder){
                case 10:    println "¡Aquest nom d'usuari ja està en us!"
                            break;
                case 101:   "¡Has d'introduir algun nom d'usuari per entrar al Xat!"
                            break;
                default:    break;
            }
            println "Entra el teu nom d'usuari:a "
        }
            
           
        listen()
        
    }

}