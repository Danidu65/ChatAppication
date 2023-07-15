package Server;

import Clinet.Clinet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private static Server server;
    private List<Clinet> clinets = new ArrayList<>();

    private Server() throws IOException {
        serverSocket = new ServerSocket(3002);
    }

    public static Server getInstance() throws IOException {
        return server!=null? server:(server=new Server());
    }

    public void  makeSocket(){
        while (!serverSocket.isClosed()){
            try {
                socket = serverSocket.accept();
                Clinet clinet = new Clinet(socket,clinets);
                clinets.add(clinet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
