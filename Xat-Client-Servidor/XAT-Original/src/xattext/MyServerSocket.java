/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xattext;

import java.net.*;
import java.io.*;

/**
 *
 * @author sergio
 */

public class MyServerSocket {

    protected ServerSocket serverSocket;

    public MyServerSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MySocket accept() {
        try {
            Socket cs = serverSocket.accept();
            return new MySocket(cs);
        } catch (IOException e) {
            return null;
        }
    }

}
