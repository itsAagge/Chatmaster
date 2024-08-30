package Backend.Server;

import java.net.Socket;
import java.util.ArrayList;

public class ServerInfo {
    ArrayList<Socket> clients = new ArrayList<>();

    public ServerInfo() {
    }

    public void addClient(Socket socket) {
        clients.add(socket);
    }

    public ArrayList<Socket> getClients() {
        return clients;
    }
}
