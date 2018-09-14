import akka.actor._
import java.net._
import java.io._
import scala.io._


object ProvaClient extends App{
	
        val client = ActorSystem("Client")
        val socket = new Socket(args(0), args(1).toInt)
        val write = client.actorOf(Props(new ProvaClient(socket)))
        val read = client.actorOf(Props(new ProvaClient(socket)))
        write ! "write"
        read ! "read"
}

class ProvaClient(socket: Socket) extends Actor {
    var line = new String()
    val in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    val out = new PrintWriter(socket.getOutputStream(), true)
    def receive = {
        case "write" =>
            while({line = StdIn.readLine(); line != null})
                out.println(line)
        case "read" =>
            while({line = in.readLine(); line != null})
                println(line)
    }
}
