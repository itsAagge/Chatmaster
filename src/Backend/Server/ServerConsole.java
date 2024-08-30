package Backend.Server;

import Backend.Server.Threads.ThreadAccept;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConsole {
    private static ServerConsole serverConsole;
    private ServerInfo serverInfo;
    private ServerSocket serverSocket;

    public static ServerConsole getServerConsole() {
        if (serverConsole != null) {
            return serverConsole;
        } else {
            serverConsole = new ServerConsole();
            return serverConsole;
        }
    }

    public void createServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.serverInfo = new ServerInfo();
            ThreadAccept threadAccept = new ThreadAccept(serverSocket, serverInfo, this);
            threadAccept.start();
            System.out.println("Server started with IP: " + serverSocket.getInetAddress().getLocalHost().getHostAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSystemMessage(String message) {
        for (Socket client : serverInfo.getClients()) {
            try {
                DataOutputStream sendMessage = new DataOutputStream(client.getOutputStream());
                sendMessage.writeBytes(message + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getServerIP() {
        try {
            return serverSocket.getInetAddress().getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
