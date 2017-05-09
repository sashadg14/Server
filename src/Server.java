import java.io.*;
import java.net.*;

public class Server {
    BufferedReader in = null;
    PrintWriter    out= null;
    ServerSocket servers = null;
    Socket       fromclient = null;
    public void startServer() throws IOException {
        System.out.println("Welcome to Server side");


        // create server socket
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            fromclient= servers.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

        in  = new BufferedReader(new
                InputStreamReader(fromclient.getInputStream()));
        out = new PrintWriter(fromclient.getOutputStream(),true);
        ReqestHandler reqestHandler= new ReqestHandler(in,out);

        System.out.println("Wait for messages");
        String comand;
        while (true){
            if ((comand = in.readLine()) != null) {
                if(comand.equals("ADD")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.addRequest();
                }
                if(comand.equals("GET PAGE")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.getPageRequest();
                }
                if(comand.equals("DELETE BY NAME & GROUP")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.deleteByNameAndGroupRequest();
                }
                if(comand.equals("DELETE BY NAME & WORK")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.deleteByNameAndWorkRequest();
                }
                if(comand.equals("DELETE BY NAME & NUMBER OF WORKS")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.deleteByNameAndNumberOfWorkRequest();
                }
                if(comand.equals("SAVE")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.saveRequest();
                }
                if(comand.equals("LOAD")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.loadRequest();
                }
                if(comand.equals("NEXT PAGE")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.nextPageRequest();
                }
                if(comand.equals("PREVIOUS PAGE")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.previousPageRequest();
                }

                if(comand.equals("FIRST PAGE")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.firstPageRequest();
                }

                if(comand.equals("LAST PAGE")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.lastPageRequest();
                }
                if(comand.equals("FIND BY NAME & GROUP")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.findByNameAndGroupRequest();
                }
                if(comand.equals("FIND BY NAME & WORK")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.findByNameAndWorkRequest();
                }
                if(comand.equals("FIND BY NAME & NUMBER OF WORKS")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.findByNameAndNumberOfWorkRequest();
                }
                if(comand.equals("END FINDING")) {
                    System.out.println("COMAND " + comand);
                    reqestHandler.endFindingRequest();
                }
                if (comand.equals("EXIT")) break;
            }
        }
        closeServer();
    }

    public  void closeServer() throws IOException {
        out.println("CLOSE SERVER");
        out.close();
        in.close();
        fromclient.close();
        servers.close();
    }
}