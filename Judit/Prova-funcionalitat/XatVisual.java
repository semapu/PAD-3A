
import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Judit & Dani
 */
public class XatVisual implements ActionListener {
    
    private final MySocket soc;
    private String nick;
    
    
    private JFrame frame;
    private StyledDocument messages;
    private JPanel loginBox;
    private JPanel xatPage;
    
    private JTextField text;
    private JTextField nickField;
    private JButton button;
    private JButton login_button;
    private JLabel errorLoginText;
    private JLabel errorNoNickText;
    
    private SimpleAttributeSet left;
    private SimpleAttributeSet right;
    
    private enum Action{
        sendCommand,
        loginCommand
    }

    public XatVisual(String localhost, int port) {
        soc = new MySocket(localhost, port);
      
        createAndShowLoginGUI();
     
    }
    
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader buff_in = new BufferedReader(new InputStreamReader(System.in));
        XatVisual client;
        
        client = new XatVisual("localhost", 22222);
       
    }
    
    
    public void createAndShowLoginGUI() {
            //Set the look and feel of t windows
        try{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){}
        JFrame.setDefaultLookAndFeelDecorated(true);

            // Create and set up the window.
        frame = new JFrame("WhatsXAT by 'Judit and Dani'");
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        
            //frame.setLayout(new BorderLayout(1000,5));
	frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
        // ----- LOGIN BOX INTERFACE -------------------------------------------
        
        loginBox = new JPanel();
	loginBox.setLayout(new BoxLayout(loginBox, BoxLayout.Y_AXIS));
            //Anar a mirar com esta feta la linia de introduir text i enviarlo
        JLabel loginText = new JLabel("Posa el teu nick:");
        errorLoginText = new JLabel("¡Aquest nick ja està en us! Intanta-ho amb un altre.");
        errorLoginText.setForeground(Color.RED);
        errorNoNickText = new JLabel("Siusplau, introdueix un Nick per a conectarte.");
        errorNoNickText.setForeground(Color.ORANGE);
        
        
        JPanel inp_login = new JPanel();
	inp_login.setLayout(new BoxLayout(inp_login, BoxLayout.X_AXIS));
	nickField = new JTextField(25);
        login_button = new JButton("ENTRAR");
	inp_login.add(nickField);
	inp_login.add(login_button);
        login_button.setActionCommand(Action.loginCommand.name());
        login_button.addActionListener(this);
        
	inp_login.setMaximumSize(new Dimension(inp_login.getMaximumSize().width, inp_login.getMinimumSize().height));
        
           
        frame.getRootPane().setDefaultButton(login_button);
 
            // Afegim el contingut al LOGINBOX
        loginBox.add(loginText, BorderLayout.WEST);
        loginBox.add(inp_login, BorderLayout.CENTER);
        loginBox.add(errorLoginText, BorderLayout.SOUTH);
        errorLoginText.setVisible(false);
        loginBox.add(errorNoNickText, BorderLayout.SOUTH);
        errorNoNickText.setVisible(false);
        
        
        loginBox.setLocation(0,250);
	frame.add(loginBox);
        
        
        loginBox.setVisible(true);
        // ---------------------------
        
        
        // ----- XAT INTERFACE -----------------------------------------------
        
        xatPage = new JPanel();
        xatPage.setLayout(new BoxLayout(xatPage, BoxLayout.Y_AXIS));
        
        
        JPanel out = new JPanel();
	out.setLayout(new BoxLayout(out, BoxLayout.X_AXIS));
	JTextPane pane = new JTextPane(/*20,30*/);
        messages = pane.getStyledDocument();
	pane.setEditable(false);
        
        out.setSize(400, 600);
        pane.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5,ORANGE));
        JScrollPane pane_scroll= new JScrollPane(pane); 
        pane_scroll.setBorder(BorderFactory.createTitledBorder("Conversa"));
        pane_scroll.setAutoscrolls(true);
        out.add(pane_scroll); 
        
	
        JPanel users = new JPanel();
        JTextPane users_list = new JTextPane();
        
        //users.setLayout(new BoxLayout(users, BoxLayout.X_AXIS));
        JScrollPane users_list_scroll= new JScrollPane(users_list);
        users_list_scroll.setBorder(BorderFactory.createTitledBorder("On-Line"));
        users_list_scroll.setAutoscrolls(true);
        out.add(users_list_scroll);
        
        
            // Define attributes for the text pane
        right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setRightIndent(right, 3);
        left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        StyleConstants.setLeftIndent(left, 3);
        
            // Create an input JPanel and add a JTextField(25) and a JButton
        JPanel inp = new JPanel();
	inp.setLayout(new BoxLayout(inp, BoxLayout.X_AXIS));
        inp.setSize(300, 30);
	text = new JTextField();
	button = new JButton("Enviar");
	inp.add(text);
	inp.add(button);
        inp.setMaximumSize(new Dimension(inp.getMaximumSize().width, inp.getMinimumSize().height));
        
        button.setActionCommand(Action.sendCommand.name());
        button.addActionListener(this);
	
        
        xatPage.add(out/*, BorderLayout.CENTER*/);
        xatPage.add(inp);
        frame.add(xatPage,BorderLayout.PAGE_START);
        
        xatPage.setVisible(false);
        
        // -----------------------------
        
        
        //Display the window centered.
        //frame.pack();
        frame.getRootPane().setDefaultButton(login_button);
	frame.setSize(700,500);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
        
    }
    
    public void showChatGUI(String nick){
                
        frame.getRootPane().setDefaultButton(button);
        frame.setTitle("WhatsXAT by 'Judit and Dani' :: ¡ Wellcome "+nick+" !");

        loginBox.setVisible(false);
	xatPage.setVisible(true);
    }
    
    
    private void writeOnTextPane(String line, SimpleAttributeSet attribute) {
        int length = messages.getLength();
        try {
            messages.insertString(messages.getLength(), line, null);
        } catch (BadLocationException ex) {
            System.out.println(ex);
        }
        messages.setParagraphAttributes(length + 1, 1, attribute, false);
        }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "sendCommand": 
                soc.write_str(text.getText());
                writeOnTextPane(text.getText() + "\n", right);
                text.setText("");
                break;
                
            case "loginCommand": 
                soc.write_str(nick=nickField.getText());
                
                switch(soc.read_int()){
                    case 10:    showLoginError();
                                nickField.setText("");
                                break;
                    case 101:   showNoNickError();
                                nickField.setText("");
                                break;
                    default:
                                showChatGUI(nick);
                                listen();
                                break;
                }
                break;
                
            default:
                break;
        }
    }
    public void showLoginError(){
        
        errorNoNickText.setVisible(false);
        errorLoginText.setVisible(true);
        
    }
    public void showNoNickError(){
        
        errorLoginText.setVisible(false);
        errorNoNickText.setVisible(true);
    }
    
    
    public void listen() {
        new Thread(){
            
            @Override
            public void run(){
        
                String line;
        
                while ((line = soc.read_str()) != null) {
                    writeOnTextPane(line + "\n", left);
                }
                soc.close();
            }
        }.start();
    }

    

    
}
