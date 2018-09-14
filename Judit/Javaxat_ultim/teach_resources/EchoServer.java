public class EchoServer{ //Servidor Echo va enviant les linies que reb

	public static void main(String []args) {
		MyServerSocket ss = new MyServerSocket(Integer.parseInt(args[0]));
		// MySocket s; quí no , s'ha de declarar com a final.	
	
		while(true){
			final MySocket s=ss.accept();
			new Thread() { //Classe anònima	
				public void run();
				String line;
				while (s.readLine()!= null){
					s.println(line);
				s.close();				
				}
			}.start();	
		}
	}
}
