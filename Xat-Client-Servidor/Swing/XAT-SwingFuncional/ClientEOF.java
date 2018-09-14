
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientEOF implements ActionListener {
	
	JFrame frame = new JFrame("Xat");
	JButton button;
	JTextField text;
	JTextArea messages;
	JPanel out, inp, users;
	MySocket sc;
	DefaultListModel<String> model;
	
	public void chatClient(String host, int port){
        // Set the look and feel
        try{
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) { e.printStackTrace(); } 
        
        //Make sure we hacve a nice window decoration
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        // Crete and set up thje window 
        //default BorderLayout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // CREate an output JPanel and add a JtextArea(20, 30) inside JScrollPane
        out = new JPanel(); // default FlowLayout
        messages = new JTextArea(20, 30);
        messages.setEditable(false);
        out.add(new JScrollPane(messages));
        
        //Crete an input JPanel and add a JTextField(25) and a JButton
        inp = new JPanel();
        text = new JTextField(25);
        text.requestFocusInWindow();
        button = new JButton("Enviar");
        inp.add(text);
        inp.add(button);
        
        //create JPanel for users
        users = new JPanel();
         model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        JScrollPane scroll = new JScrollPane(list);
        users.add(scroll);
        
        //Listen to events from button
        
        button.addActionListener(this);
        
        // add panels to main frame
        frame.add(out, BorderLayout.CENTER);
        frame.add(inp, BorderLayout.PAGE_END);
        frame.add(users, BorderLayout.EAST);
        
        //display the window centered (podem posarho com ho volguem)
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        sc = new MySocket(host, port);
    	//output thread
    	new Thread() {
    		@Override
    		public void run(){
    			String line;
    			while ((line = sc.readLine()) != null) {
					messages.append(line + "\n");
                }
                sc.close();
    		}
    	}.start();
    	
    	/*new Thread() {
    		@Override
    		public void run(){
    			while(true){
    				while(sc.rdTreeSet().iterator().hasNext()){
    					model.addElement(sc.rdTreeSet().iterator().next());
    				}
    			}
    		}
    	}.start();*/
    }
    
	public void actionPerformed(ActionEvent event){
		sc.println(text.getText());
		messages.append(text.getText() + "\n");
		text.setText("");
	}
    
    
    ////////////////////
    
    public static void main(final String[] args) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	final ClientEOF client = new ClientEOF();
                client.chatClient(args[0], Integer.parseInt(args[1]));
            }
        });
    }
}
