public class EchoClient{ //Servidor Echo va enviant les linies que reb

	public static void main(String []args) {
		
		/*l'atibut "final" ens l'estalviem pk hem afegit el void start(MySocket sc) */ 
		MySocket ss = new MySocket(args[0], Integer.parseInt(args[1]));
		// MySocket s; quí no , s'ha de declarar com a final.	
		
		// Keyboard thread
		new Thread() {
			MySocket sc;
			public void run(){
				BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
				String line;
				try{				
					while((line=kdb.readLine == null))
					sc.println(line);
				//close for writing
				// ...
				} catch (IOException ex){
					ex.printStackTrace();
				}
			}

			void start(MySocket sc) {
				this.sc =sc
				start();
			}
				
		}.start(sc);

		//Console thread
		new Thread() {
			public void run(){
				// ACABAR EL CONSOLE THREAD
				String line;
				while((line=s.readLine == null))
					System.out.println(line);
			}
				
		}.start();

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
