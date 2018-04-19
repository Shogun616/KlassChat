package övningsuppgift.pkg9;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

public class Klasschat extends JFrame implements ActionListener {
    private final JPanel p = new JPanel();
    private final JTextField namn  = new JTextField("Philip: "); 
    private final JPanel buttonPanel = new JPanel();
    private final JTextArea area = new JTextArea(10, 60);
    private final JButton disconnectbutton = new JButton("Disconnect");
    String name = "";
    
public Klasschat() throws SocketException, IOException{
    name = JOptionPane.showInputDialog(null, "Ange ditt namn");
    setTitle("KlassChat "+name);
    area.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
    p.setLayout(new BorderLayout());
    p.add(area, BorderLayout.CENTER); 
    namn.addActionListener(this);
    buttonPanel.add(disconnectbutton); 
    p.add(buttonPanel, BorderLayout.NORTH);
    add(namn,BorderLayout.SOUTH);
    disconnectbutton.addActionListener(this);
    add(p); 
    pack(); 
    setVisible(true); 
    setDefaultCloseOperation(EXIT_ON_CLOSE);   
    ChatLyssnaren l = new ChatLyssnaren(area);
    l.start();
    
}

public void actionPerformed(ActionEvent e){
    try{
        System.out.println(area +":" + namn.getText());
        area.setText(namn.getText());
    }
    catch (Exception e1){
        e1.printStackTrace();
    }
      
      if(e.getSource() == disconnectbutton){
          System.out.println(area +":" + namn.getText());
          area.setText(namn.getText() +"Disconnected");
          System.exit(0);
        }
 //todo, sänd meddelande
}
    public static void main(String[] args) throws IOException {
        Klasschat e = new Klasschat();
        
    }
}


