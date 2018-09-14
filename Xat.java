import java.awt.*;
import javax.swing.*;

public class Xat {
    private static void createAndShowGUI(){
        // SEt the look and feel
        try{
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) { e.printStackTrace(); } 
        
        //Make sure we hacve a nice window decoration
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        // Crete and set up thje window
        JFrame frame = new JFrame("Xat"); //default BorderLayout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // CREate an output JPanel and add a JtextArea(20, 30) inside JScrollPane
        JPanel out = new JPanel(); // default FlowLayout
        JTextArea messages = new JTextArea(20, 30);
        messages.setEditable(false);
        out.add(new JScrollPane(messages));
        
        //Crete an input JPanel and add a JTextField(25) and a JButton
        JPanel inp = new JPanel();
        JTextField text = new JTextField(25);
        JButton button = new JButton("Enviar");
        inp.add(text);
        inp.add(button);
        
        // add panels to main frame
        frame.add(out, BorderLayout.CENTER);
        frame.add(inp, BorderLayout.PAGE_END);
        
        //display the window centered (podem posarho com ho volguem)
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
