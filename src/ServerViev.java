import javax.swing.*;
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
    public ServerViev(){
        jFrame=new JFrame("SERVER");
        jFrame.getContentPane().setLayout(null);
        startButton=new JButton("Запустить сервер");
        startButton.setBounds(50,50,80,30);
        closeButton=new JButton("Остановить");
        server=new Server();
        closeButton.setBounds(50,100,80,30);
        jFrame.add(startButton);
        jFrame.add(closeButton);
        jFrame.setVisible(true);
        jFrame.setSize(500,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               Thread thread =new  Thread(new  Runnable()
                            {
                             public void run() {
                                 try {
                                     server.startServer();
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
                             }
                             }
               );
               thread.start();

            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    server.closeServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
