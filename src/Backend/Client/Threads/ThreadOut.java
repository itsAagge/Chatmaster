package Backend.Client.Threads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadOut extends Thread {
    BufferedReader inFromUser;
    DataOutputStream outToServer;
    String messageFromUser;
    String name;

    public ThreadOut(Socket clientSocket, String name) throws IOException {
        this.inFromUser = new BufferedReader(new InputStreamReader(System.in));
        this.outToServer = new DataOutputStream(clientSocket.getOutputStream());
        this.name = name;
    }

    public void run() {
        while (true) {
            try {
                messageFromUser = inFromUser.readLine();
                outToServer.writeBytes(name + ": " + messageFromUser + '\n');
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
