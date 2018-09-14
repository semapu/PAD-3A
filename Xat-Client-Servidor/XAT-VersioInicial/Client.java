
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Client {

    public static void main(String[] args) {	
        final MySocket sc = new MySocket(args[0], Integer.parseInt(args[1]));
        // Input Thread
        new Thread() {
            @Override
     
     public void run() {                
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String line;
                try {
                    while ((line = br.readLine()) != null) {
                        sc.println(line);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }                
            }
        }.start();

        // Output thread 
        new Thread() {
            @Override
            public void run() {
                String line;
                while ((line = sc.readLine()) != null) {
			System.out.println("\033[1;34m"+line);
                }
            }
        }.start();
    }
}
