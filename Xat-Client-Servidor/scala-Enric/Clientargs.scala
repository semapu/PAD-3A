import java.net.{ServerSocket, Socket}
import java.io._

//Client amb llenguatge Scala

object Client {
	def main(args: Array[String]) {

		val socket = new Socket(args(0), args(1).toInt) //Introduïm els arguments per al socket(HOST,PORT)
		val nick = args(2)				//Introduïm el nick

		val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
		val out = new PrintWriter(socket.getOutputStream, true)

		out.println(nick)

		actors.Actor.actor {
			var lineaactual = ""
			val teclat = new BufferedReader(new InputStreamReader(System.in))
            
            while ( { lineaactual = teclat.readLine(); lineaactual != null } ) {
            	out.println(nick + ": " + lineaactual)
            }
            socket.shutdownOutput()
        }

		/*actors.Actor.actor {
			var lineaactual = ""
			while ( { lineaactual = in.readLine(); lineaactual != null } ) {
				System.out.println(lineaactual)
			}
            socket.shutdownInput()
		}*/
	}
}
