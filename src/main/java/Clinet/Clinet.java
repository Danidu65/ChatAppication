package Clinet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Clinet {
    private Socket socket;
    private List<Clinet> clinets;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String msg = "";
    public Clinet(Socket socket, List<Clinet> clinets) {
    try {
        this.socket = socket;
        this.clinets = clinets;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        while (socket.isConnected()) {
                            msg = dataInputStream.readUTF();
                            for (Clinet client : clinets) {
                                if (client.socket.getPort() != socket.getPort()) {
                                    client.dataOutputStream.writeUTF(msg);
                                    client.dataOutputStream.flush();
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        });
    }
}