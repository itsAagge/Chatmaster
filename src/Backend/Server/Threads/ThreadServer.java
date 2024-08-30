package Backend.Server.Threads;

import Backend.Server.ServerInfo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadServer extends Thread {
    ServerInfo serverInfo;
    BufferedReader inFromClients;
    DataOutputStream forwardMessage;
    String message;

    public ThreadServer(Socket socket, ServerInfo serverInfo) throws IOException {
        this.inFromClients = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.serverInfo = serverInfo;
    }

    public void run() {
        while (true) {
            try {
                message = inFromClients.readLine();
                for (Socket client : serverInfo.getClients()) {
                    this.forwardMessage = new DataOutputStream(client.getOutputStream());
                    forwardMessage.writeBytes(message + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
