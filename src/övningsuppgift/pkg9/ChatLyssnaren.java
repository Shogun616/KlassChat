package övningsuppgift.pkg9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JTextArea;


public class ChatLyssnaren extends Thread{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String ip = "234.235.236.237";
    InetAddress iadr = InetAddress.getByName(ip);
    int port = 12540;
    MulticastSocket socket = new MulticastSocket(port);
    
    DatagramPacket packet;
    byte[] data;
    String message;
    private String text;
    private long interval;
    JTextArea area;

    public ChatLyssnaren(JTextArea area) throws UnknownHostException, SocketException, IOException{
        this.area = area;
        socket.joinGroup(iadr);
        while((message = in.readLine()) != null){
            if (message.equals("Disconnected"))  System.exit(0);
            data = message.getBytes();
            packet = new DatagramPacket(data, data.length, iadr, port);
          //  socket.send(packet);
            socket.receive(packet);
            //todo receive
           
            while ((message = in.readLine()) != null) {
                if (socket.equals("")){
                    area.append(iadr.getHostName() + "\n");
                }
            }
            //todo append to area
            
        } System.exit(0);      
    }
    
    @Override
    public void run()  {   
        while (!Thread.interrupted()){
            try {
                Thread.sleep(interval);
                System.out.println(text);
                System.out.println(area);
            }
            catch (InterruptedException e){
                break;
            }
        }
    }

   
 }
   
