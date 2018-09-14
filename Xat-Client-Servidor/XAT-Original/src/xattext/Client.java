package xattext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import xattext.utils.Comms;

public class Client {

    public static void main(String[] args) {
        final MySocket sc = new MySocket("localhost", Comms.portServidor);

        // Keyboard thread
        new Thread() {
            @Override
            public void run() {
                
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                String line;
                System.out.print("abans del read");
                try {
                    while ((line = kbd.readLine()) != null) {
                        sc.println(line);
                        System.out.print("despres del read");
                        // close for writing
                        // ...
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            }
        }.start();

        // Console thread 
        new Thread() {
            @Override
            public void run() {
                String line;
                while ((line = sc.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }.start();
    }
}
