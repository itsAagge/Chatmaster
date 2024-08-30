package Backend.Server.Threads;

import Backend.Server.ServerInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadAccept extends Thread {
    ServerSocket serverSocket;
    ServerInfo serverInfo;

    public ThreadAccept(ServerSocket serverSocket, ServerInfo serverInfo) {
        this.serverSocket = serverSocket;
        this.serverInfo = serverInfo;
    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                serverInfo.addClient(socket);
                ThreadServer threadServer = new ThreadServer(socket, serverInfo);
                threadServer.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
