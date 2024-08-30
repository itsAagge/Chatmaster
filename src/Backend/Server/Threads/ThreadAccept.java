package Backend.Server.Threads;

import Backend.Server.ServerConsole;
import Backend.Server.ServerInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadAccept extends Thread {
    ServerSocket serverSocket;
    ServerInfo serverInfo;
    ServerConsole serverConsole;

    public ThreadAccept(ServerSocket serverSocket, ServerInfo serverInfo, ServerConsole serverConsole) {
        this.serverSocket = serverSocket;
        this.serverInfo = serverInfo;
        this.serverConsole = serverConsole;
    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                serverInfo.addClient(socket);
                //serverConsole.sendSystemMessage(socket.getInetAddress().toString() + " has joined the chat.");
                ThreadServer threadServer = new ThreadServer(socket, serverInfo);
                threadServer.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
