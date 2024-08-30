package Backend.Client;

import Backend.Client.Threads.ThreadIn;
import GUI.ChatPane;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConsole {
    private static ClientConsole clientConsole;
    private Socket socket;
    private String clientName;

    public static ClientConsole getClientConsole() {
        if (clientConsole != null) {
            return clientConsole;
        } else {
            clientConsole = new ClientConsole();
            return clientConsole;
        }
    }

    public boolean connectClientToServer(String clientName, String ip, int port) {
        try {
            socket = new Socket(ip, port);
            if (socket.getInetAddress() != null) {
                this.clientName = clientName;
                sendConfirmationMessage();
                ThreadIn threadIn = new ThreadIn(socket, this);
                threadIn.start();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMessageToChat(String message) {
        ChatPane.getTxaChat().appendText(message + "\n");
    }

    public void sendMessageToServer(String messageToSend) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes(clientName + ": " + messageToSend + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendConfirmationMessage() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes("System: " + clientName + " has joined the chat\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
