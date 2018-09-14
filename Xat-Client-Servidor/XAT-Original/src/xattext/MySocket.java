/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xattext;

import java.io.*;
import java.net.*;

/**
 *
 * @author sergio
 */

public class MySocket {

    protected Socket socket;
    protected BufferedReader in;
    protected PrintWriter out;

    public MySocket(String host, int port) {
        try {
            socket = new Socket(host, port);
            initStreams();
        } catch (IOException e) {
        }
    }

    public MySocket(Socket s) {
        socket = s;
        initStreams();
    }

    private void initStreams() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
        }
    }

    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public int readInt() {
        return Integer.parseInt(readLine());
    }

    public boolean readBoolean() {
        return Boolean.parseBoolean(readLine());
    }

    public char readChar() {
        return readLine().charAt(0);
    }

    public void println(String s) {
        out.println(s);
    }

    public void writeInt(int i) {
        println(Integer.toString(i));
    }

    public void writeBoolean(boolean b) {
        println(Boolean.toString(b));
    }

    public void writeChar(char c) {
        println(new Character(c).toString());
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
        }
    }

}
