import akka.actor._
import java.net._
import java.io._
import scala.io._

object ProvaClient extends App{
        val client = ActorSystem("Client")
        val socket = new Socket("localhost", args(0).toInt)
        val write = client.actorOf(Props(new ProvaClient(socket, client)))
        val read = client.actorOf(Props(new ProvaClient(socket, client)))
        write ! "write"
        read ! "read"
        client.awaitTermination()
}

class ProvaClient(socket: Socket, client: ActorSystem) extends Actor {
    var line = new String()
    line = null
    val in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    val out = new PrintWriter(socket.getOutputStream(), true)
    def receive = {
        case "write" =>
            while({line = StdIn.readLine(); line != null})
                out.println(line)
            out.println("\u0000")
            self ! PoisonPill
        case "read" =>
            while({line = in.readLine(); line != "\u0000"})
                println(line)
            socket.close()
            println("Server-->Bye!")
            self ! PoisonPill
    }
    client.shutdown()
}
