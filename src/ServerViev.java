import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by alex o n 27.04.2017.
 */
public class ServerViev {
    JFrame jFrame;
    JButton startButton;
    JButton closeButton;
    Server server;
    TextArea jTextArea;
    private static final Logger log = Logger.getLogger(ServerViev.class);
    public ServerViev(){

        jTextArea=new TextArea();
        jFrame=new JFrame("SERVER");
        jFrame.getContentPane().setLayout(null);
        startButton=new JButton("Запустить сервер");
        startButton.setBounds(50,50,80,30);
        closeButton=new JButton("Остановить");
        server=new Server(this);
        closeButton.setBounds(50,100,80,30);
        jTextArea.setBounds(150,150,150,250);
        jFrame.add(jTextArea);
        jFrame.add(startButton);
        jFrame.add(closeButton);
        jFrame.setVisible(true);
        jFrame.setSize(500,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Thread(server).start();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    server.closeServer();
                    addComandInArea("CLOSE SERVER");
                    //thread[0].interrupt();
                } catch (IOException e) {
                    log.error(e.getMessage());
                    //e.printStackTrace();
                }
            }
        });
    }

    public void addComandInArea(String string){
        jTextArea.append(string+"\n");
        jTextArea.update(jTextArea.getGraphics());
    }
}
