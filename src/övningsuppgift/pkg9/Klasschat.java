package övningsuppgift.pkg9;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Klasschat extends JFrame implements ActionListener {

    String namn;
    InetAddress iadr;
    int port;
    MulticastSocket so;
    JTextArea txt = new JTextArea();
    JScrollPane sp = new JScrollPane(txt);
    JTextField skriv = new JTextField();
    JButton sluta = new JButton("Disconnected");
    
    public Klasschat(String användarnamn, String gruppadr, int portNr) throws IOException{
        namn = användarnamn;
        iadr = InetAddress.getByName(gruppadr);
        port = portNr;
        
        so = new MulticastSocket(port);
        so.joinGroup(iadr);
        new Mottagare(so, txt);
        sendMsg("Connected");
        
        setTitle("Chat "+namn);
        txt.setEditable(false);
        add(sluta, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(skriv, BorderLayout.SOUTH);
        sluta.addActionListener(this);
        skriv.addActionListener(this);
        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void sendMsg(String s){
        
        byte[] data = (namn + ": " +s).getBytes();
        DatagramPacket packet= new DatagramPacket(data, data.length, iadr, port);
        try{
            so.send(packet);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == skriv){
            sendMsg(skriv.getText());
            skriv.setText("");
        }
        else if(e.getSource() == sluta){
            sendMsg("Disconnected");
            try {
                so.leaveGroup(iadr);
            }
            catch (IOException ie){
                so.close();
                dispose();
                System.exit(0);
            }
                
        }
    }
    
    public static void main (String[] args) throws IOException{
        String namn = "Philip";
        Klasschat c = new Klasschat(namn, "234.235.236.237", 12540);
    }
}


