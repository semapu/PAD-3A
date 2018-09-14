package xattext;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        MultithreadedServer ms = new MultithreadedServer();
        ms.start();
    }   
}