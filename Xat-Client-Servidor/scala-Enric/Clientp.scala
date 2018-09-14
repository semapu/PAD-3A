import java.net.{ServerSocket, Socket}
import java.io._


object Client {
	def main(args: Array[String]) {
		val socket = new Socket(args(0), args(1).toInt)
		val nick = args(2)

		val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
		val out = new PrintWriter(socket.getOutputStream, true)

		out.println(nick)

		actors.Actor.actor {
			val kbd = new BufferedReader(new InputStreamReader(System.in))
            var line = ""
            while ( { line = kbd.readLine(); line != null } ) {
            	out.println(nick + ": " + line)
            }
            socket.shutdownOutput()
        }

		actors.Actor.actor {
			var line = ""
			while ( { line = in.readLine(); line != null } ) {
				System.out.println(line)
			}
            socket.shutdownInput()
		}
	}
}