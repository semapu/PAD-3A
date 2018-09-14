import java.awt.*;
import javax.swing.*;

/**
 * general purpose powerful free layouts:
 * JGoodies' FormLayout
 * MigLayout-->aquest(GridBagLayout)
 * DesignGridLayout
 */

public class Xat {
    private static void createAndShowGUI() {
        //Set the look and feel.
        try {
		UIManager.setLookAndFeel(
			//mateixes finetres que en el sistema
			UIManager.getSystemLookAndFeelClassName()
		);
	} catch (Exception e) { e.printStackTrace(); }
        
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame = new JFrame("Xat"); //default BorderLayout
	frame.getRootPane().setBorder(BorderFactory.creatEmptyBorder(5,5,5,5)); //Marc en blanc extern
	//BoxLayout valid per redimen. S'expandeix
	out.setLayout(new BoxLayout(out, /*orientació*/ BoxLayout.PAGE_AXIS));
	frame.getContentPane().setLayout(new BorderLayout(5, 5)); //Valors de separació entre regions
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an output JPanel and add a JTextArea(20, 30) inside a JScrollPane
        JPanel out = new JPanel(); //default FlowLayout
        JTextArea messages = new JTextArea(/*20, 30*/);
	messages.setEditable(false);
	out.add(new JScrollPane(messages));
	
        // Create an input JPanel and add a JTextField(25) and a JButton
        Jpanel inp = new JPanel();
	inp.setLayout(new BoxLayout(inp, BoxLayout.LINE_AXIS));
	inp.add(Box.createRigidArea(new Dimension (5,0)));
	JTextField text = new JTextField(/*25*/);
	JButton button = new JButton("Enviar");
	//ordre d'esquerra a dreta, odre intraduits
	inp.add(text);
	inp.add(button);
        
        // add panels to main frame
        frame.add(out, BorderLayout.CENTER);
	frame.add(Box.createRigidArea(new Dimension (0,5))); //Marc extern de 5
	frame.add(inp, BorderLayout.PAGE_END);
        
        //Display the window centered.
        //frame.pack();
	frame.setSize(400,500); //Definim externament, els paràmetres interns no són necessaris, els interns s'adabten
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


 
