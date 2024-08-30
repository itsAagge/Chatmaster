package Backend.Server;

public class ServerConsole {
    private static ServerConsole serverConsole;

    public static ServerConsole getServerConsole() {
        if (serverConsole != null) {
            return serverConsole;
        } else {
            serverConsole = new ServerConsole();
            return serverConsole;
        }
    }


}
