
import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException {
        MultiClientServer multiServer = new MultiClientServer(Integer.parseInt(args[0]));
        multiServer.start();
    }   
}
