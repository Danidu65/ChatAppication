package Server;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public JFXTextField mgTxt;
    public Button sendBtn;
    public TextArea displayTxtArea;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message="";

    @FXML
    void initialize() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(3002);
                displayTxtArea.appendText("Server.Server Is Started"+"\n");

                socket = serverSocket.accept();
                displayTxtArea.appendText("Client.Client Accept"+"\n");

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("finish")){
                    message = dataInputStream.readUTF();
                    displayTxtArea.appendText("client : " + message+"\n");

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void mgTxtOnAction(ActionEvent actionEvent) {
        sendBtnOnAction(actionEvent);
    }

    public void sendBtnOnAction(ActionEvent actionEvent) {
        try {
            displayTxtArea.appendText("Server.Server :" + mgTxt.getText()+"\n");
            dataOutputStream.writeUTF(mgTxt.getText());
            dataOutputStream.flush();
            mgTxt.setText("");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
