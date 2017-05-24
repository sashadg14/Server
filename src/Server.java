import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Server implements Runnable{
    BufferedReader in = null;
    PrintWriter    out= null;
    ServerSocket servers = null;
    Socket       fromclient = null;
    ServerViev serverViev;
    String comand;
    private static final Logger log = Logger.getLogger(Server.class);
    Server(ServerViev serverViev){
        this.serverViev=serverViev;
    }
    public void startServer() throws IOException {

    }

    public  void closeServer() throws IOException {
        //out.println("CLOSE SERVER");
        JOptionPane.showMessageDialog(new JFrame(), "CLOSE SERVER");
        if(fromclient!=null)
        {out.close();
        in.close();
        fromclient.close();}
        servers.close();

    }

    @Override
    public void run() {
        serverViev.addComandInArea("SERVER STARTED");
        System.out.println("Welcome to Server side");
        //jTextArea.setText("\n"+"fddsfsdfsfd");
        // create server socket
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e) {
            log.error(e.getMessage());
            //System.out.println("Couldn't listen to port 4444");
            //System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            fromclient= servers.accept();
            serverViev.addComandInArea("CLIENT CONNECT");
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            //System.exit(-1);
            e.printStackTrace();
            //log.error(e.getMessage());
        }
        try {
            in = new BufferedReader(new
                    InputStreamReader(fromclient.getInputStream()));
            out = new PrintWriter(fromclient.getOutputStream(), true);
        }catch (NullPointerException n){
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReqestHandler reqestHandler = null;
        try {
            reqestHandler = new ReqestHandler(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Wait for messages");
        try {
            while (true) {
                if ((comand = in.readLine()) != null) {
                    if (comand.equals("ADD")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.addRequest();
                    }
                    if (comand.equals("GET PAGE")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.getPageRequest();
                    }
                    if (comand.equals("SAVE")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.saveRequest();
                    }
                    if (comand.equals("LOAD")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.loadRequest();
                    }
                    if (comand.equals("NEXT PAGE")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.nextPageRequest();
                    }
                    if (comand.equals("PREVIOUS PAGE")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.previousPageRequest();
                    }

                    if (comand.equals("FIRST PAGE")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.firstPageRequest();
                    }

                    if (comand.equals("LAST PAGE")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.lastPageRequest();
                    }
                    if (comand.equals("FIND BY NAME & GROUP")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.findByNameAndGroupRequest();
                    }
                    if (comand.equals("FIND BY NAME & WORK")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.findByNameAndWorkRequest();
                    }
                    if (comand.equals("FIND BY NAME & NUMBER OF WORKS")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.findByNameAndNumberOfWorkRequest();
                    }
                    if (comand.equals("DELETE BY NAME & GROUP")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.deleteByNameAndGroupRequest();
                    }
                    if (comand.equals("DELETE BY NAME & WORK")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.deleteByNameAndWorkRequest();
                    }
                    if (comand.equals("DELETE BY NAME & NUMBER OF WORKS")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.deleteByNameAndNumberOfWorkRequest();
                    }
                    if (comand.equals("END FINDING")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.endFindingRequest();
                    }
                    if (comand.equals("RESIZE")) {
                        System.out.println("COMAND " + comand);
                        reqestHandler.setNumberOFStudentsOnPageRequest();
                    }
                    if (comand.equals("EXIT")) break;
                    serverViev.addComandInArea(comand);
                }
            }
        }catch (SocketException s) {
            serverViev.addComandInArea("CLIENT DISCONNECT");
            try {
                closeServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}