package Backend.Client.Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadIn extends Thread {
    BufferedReader inFromServer;
    String messageFromServer;

    public ThreadIn(Socket clientSocket) throws IOException {
        this.inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void run() {
        while (true) {
            try {
                messageFromServer = inFromServer.readLine();
                System.out.println(messageFromServer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
