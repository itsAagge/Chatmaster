package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class SettingsPane extends GridPane {
    private TextField txfUsername = new TextField();
    private TextField txfIP = new TextField();
    private TextField txfPort = new TextField();
    private Button btnConnect = new Button("Connect to Server");
    private Label lblConnectionStatus = new Label("NOT CONNECTED");
    private CheckBox chkBoxSelfHost = new CheckBox("I host the server");
    private Button btnHost = new Button("Host server");

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

        Label lblPort = new Label("Server Port");
        this.add(lblPort, 0, 2);
        this.add(txfPort,1,2);

        this.add(btnConnect,0,3);
        this.add(lblConnectionStatus,1,3);
        lblConnectionStatus.setTextFill(Color.RED);
    }

    public void setConnectionStatus(String message) {
        this.lblConnectionStatus.setText(message);
        if (lblConnectionStatus.getText().equals("CONNECTED")) {
            lblConnectionStatus.setTextFill(Color.GREEN);
        } else {
            lblConnectionStatus.setTextFill(Color.RED);
        }
    }
}
