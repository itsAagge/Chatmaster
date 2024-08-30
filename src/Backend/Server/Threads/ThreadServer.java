package Backend.Server.Threads;

import Backend.Server.ServerInfo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadServer {
    ServerInfo serverInfo;
    BufferedReader inFromClients;
    DataOutputStream forwardMessage;
    String message;

    public ThreadServer(Socket socket, ServerInfo serverInfo) {
        this.inFromClients = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
