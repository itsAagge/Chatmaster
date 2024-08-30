package GUI;

import Backend.Client.ClientConsole;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ChatPane extends GridPane {
    private ClientConsole clientConsole;
    private static TextArea txaChat = new TextArea();
    private TextField txfChatbar = new TextField();
    private Button btnSendMessage = new Button("Send");

    public ChatPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(txaChat,0,0,2,1);
        txaChat.setPrefWidth(500);
        txaChat.setPrefHeight(500);
        txaChat.setEditable(false);
        this.add(txfChatbar,0,1);
        txfChatbar.setOnAction(event -> chatAction());
        txfChatbar.setPrefWidth(400);
        this.add(btnSendMessage,1,1);
        btnSendMessage.setOnAction(event -> chatAction());
        ChatPane.setHalignment(btnSendMessage, HPos.RIGHT);
    }

    public static TextArea getTxaChat() {
        return txaChat;
    }

    public void chatAction() {
        if (clientConsole == null) {
            clientConsole = ClientConsole.getClientConsole();
        }
        if (txfChatbar.getText().isEmpty()) {
            System.out.println("Empty chatbar");
        } else {
            String message = txfChatbar.getText().trim();
            clientConsole.sendMessageToServer(message);
            txfChatbar.clear();
        }
    }
}
