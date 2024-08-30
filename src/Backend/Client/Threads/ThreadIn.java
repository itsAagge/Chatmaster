package Backend.Client.Threads;

import Backend.Client.ClientConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadIn extends Thread {
    BufferedReader inFromServer;
    String messageFromServer;
    ClientConsole clientConsole;

    public ThreadIn(Socket clientSocket, ClientConsole clientConsole) throws IOException {
        this.inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.clientConsole = clientConsole;
    }

    public void run() {
        while (true) {
            try {
                messageFromServer = inFromServer.readLine();
                clientConsole.addMessageToChat(messageFromServer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
