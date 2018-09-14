import java.io._
import java.net.{InetAddress,ServerSocket,Socket,SocketException}
import java.util.Random

/** 
 * Simple client/server application using Java sockets. 
 * 
 * The server simply generates random integer values and 
 * the clients provide a filter function to the server 
 * to get only values they interested in (eg. even or 
 * odd values, and so on). 
 */
object Client {
     

    try {
      val ia = InetAddress.getByName("localhost")
      val socket = new Socket(ia, 1234)
      val out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()))
      val in = new DataInputStream(socket.getInputStream())

      out.writeObject("Benvingut")
      out.flush()

      while (true) {
        val x = in.readInt()
        println("x = " + x)
      }
      out.close()
      in.close()
      socket.close()
    }
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
}


