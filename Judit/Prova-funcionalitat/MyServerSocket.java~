/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaxat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.*;


/**
 *
 * @author Dani
 */

public class MyServerSocket{
    
    protected ServerSocket socket;
    
    public MyServerSocket (int port){
        try{
            socket = new ServerSocket(port);
        }catch (IOException e){}
    }
    
    
    public MySocket accept(){
        try{
            Socket cs=socket.accept();
            return new MySocket(cs);
        }catch(IOException e){
            return null;
        }
    }
    
}
