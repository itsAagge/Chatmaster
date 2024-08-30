package GUI;

import Backend.Client.ClientConsole;
import Backend.Server.ServerConsole;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class SettingsPane extends GridPane {
    private ServerConsole serverConsole;
    private ClientConsole clientConsole;
    private TextField txfUsername = new TextField();
    private TextField txfIP = new TextField();
    private TextField txfPort = new TextField();
    private Button btnConnect = new Button("Connect to Server");
    private static Label lblConnectionStatus = new Label("NOT CONNECTED");
    private CheckBox chkBoxSelfHost = new CheckBox("I am the host\n(for connecting)");
    private Button btnHost = new Button("Host server");
    private Label lblHostStatus = new Label("Choose a port to host");

    public SettingsPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblUsername = new Label("Username");
        this.add(lblUsername, 0, 0);
        this.add(txfUsername,1,0);

        Label lblIP = new Label("Server IP");
        this.add(lblIP, 0, 1);
        this.add(txfIP,1,1);
        this.add(chkBoxSelfHost,2,1);
        ChangeListener<Boolean> changeListener = (observableValue, s, t1) -> this.selfHostStrategy();
        chkBoxSelfHost.selectedProperty().addListener(changeListener);

        Label lblPort = new Label("Server Port");
        this.add(lblPort, 0, 2);
        this.add(txfPort,1,2);

        this.add(btnConnect,0,3);
        this.add(lblConnectionStatus,1,3);
        lblConnectionStatus.setTextFill(Color.RED);
        btnConnect.setOnAction(event -> connectToServerAction());

        Label lblBlank = new Label();
        this.add(lblBlank,0,4);

        this.add(btnHost,0,5);
        this.add(lblHostStatus,1,5,2,1);
        btnHost.setOnAction(event -> hostServerAction());
    }

    public void setConnectionStatus(String message) {
        this.lblConnectionStatus.setText(message);
        if (lblConnectionStatus.getText().equals("CONNECTED")) {
            lblConnectionStatus.setTextFill(Color.GREEN);
        } else {
            lblConnectionStatus.setTextFill(Color.RED);
        }
    }

    public static String getConnectionStatus() {
        return lblConnectionStatus.getText().trim();
    }

    public void hostServerAction() {
        if (serverConsole == null) {
            serverConsole = ServerConsole.getServerConsole();
        }
        if (txfPort.getText().isEmpty()) {
            System.out.println("Missing port");
        } else {
            int port = Integer.parseInt(txfPort.getText().trim());
            serverConsole.createServer(port);
            String ip = serverConsole.getServerIP();
            lblHostStatus.setText("Server created on IP: " + ip + ", port: " + port);
        }
    }

    public void connectToServerAction() {
        if (clientConsole == null) {
            clientConsole = ClientConsole.getClientConsole();
        }
        if (txfIP.getText().isEmpty()) {
            System.out.println("Missing IP");
        } else if (txfPort.getText().isEmpty()) {
            System.out.println("Missing port");
        } else if (txfUsername.getText().isEmpty()) {
            System.out.println("Missing username");
        } else {
            Boolean connected = clientConsole.connectClientToServer(txfUsername.getText().trim(), txfIP.getText().trim(), Integer.parseInt(txfPort.getText().trim()));
            if (connected) {
                setConnectionStatus("CONNECTED");
            }
        }
    }

    public void selfHostStrategy() {
        if (chkBoxSelfHost.isSelected()) {
            if (serverConsole == null) {
                System.out.println("No server is up");
                chkBoxSelfHost.setSelected(false);
            } else {
                txfIP.setText(serverConsole.getServerIP());
                txfIP.setEditable(false);
            }
        } else {
            txfIP.setText("");
            txfIP.setEditable(true);
        }
    }
}
