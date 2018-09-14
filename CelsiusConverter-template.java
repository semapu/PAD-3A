/**
 * CelsiusConverter.java is a 1.4 application that 
 * demonstrates the use of JButton, JTextField and
 * JLabel.  It requires no other files.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CelsiusConverter implements ActionListener {
    JFrame converterFrame;
    JPanel converterPanel;
    JTextField tempCelsius;
    JLabel celsiusLabel, fahrenheitLabel;
    JButton convertTemp;

    public CelsiusConverter() {
        //Create and set up the window.
        comverterFrame =new JFrame("Converter");
	comverterFrame.setDefaultCLoseOperation(Jrame.EXIT_ON_CLOSE);


        //Create and set up the panel.
        converterPanel = new JPanel(new GridLayout(2,2));

        //Add the widgets.
        addWidgets();

        //Set the default button.
        converterFrame.getRootPane().setDefaultButton(convertTemps);

        //Add the panel to the window.
        converterFrame.add(converterPanel, BorderLayout.CENTER);
	

        //Display the window.
        //converterFrame.pack();
	converterFrame.setSize(/*new Dimension(240,180*/ 400,500);
	converterFrame.setLocationRelativeTo(null);
	converterFrame.setVisible(true);
    }

    /**
     * Create and add the widgets.
     */
    private void addWidgets() {
        //Create widgets.
        tempCelsuis = new JTextField(2);
	celsiusLabel = new JLabel("Celsius", Swing.Constants.LEFT);
	convertTemps = new JButton("Convert");
	fhrenheitLabel = new JLabel("Fahrenheit", Swing.Constants.LEFT);
        
	//Listen to events from the Convert button.
	
        convertTemp.addActionListener(this);
	/*tempCelsius.addActionListener(this);*/

        //Add the widgets to the container.
        converterPanel.add(tempCelsius);
	converterPanel.add(celsiusLabel);
	converterPanel.add(convertTemp);
	converterPanel.add(fahrenheitLabel);
        
        
    }

    public void actionPerformed(ActionEvent event) {
        //Parse degrees Celsius as a double and convert to Fahrenheit.
        int tempFahr = (int)(Double.parseDouble(tempCelsius.getNext())*1.8+32);
	fahrenheitLabel.setText(tempFahr +"Fahrenheit");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Set the look and feel.
        try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        CelsiusConverter converter = new CelsiusConverter();
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
