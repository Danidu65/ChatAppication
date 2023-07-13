package Client;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public JFXTextField mgTxt;
    public Button sendBtn;
    public TextArea displayTxtArea;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message="";

    @FXML
    void initialize(){

        new Thread(()-> {
                try {

                    // accept request and move to new socket (local socket);
                    socket = new Socket("localhost" , 3002);
                    displayTxtArea.appendText("client connect" + "\n");

                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataInputStream = new DataInputStream(socket.getInputStream());

                    while (!message.equals("finish")){
                        message = dataInputStream.readUTF();
                        displayTxtArea.appendText("server :" +message + "\n");


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
            displayTxtArea.appendText("client :" + mgTxt.getText() +"\n" );
            dataOutputStream.writeUTF(mgTxt.getText());
            mgTxt.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
