package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chatmaster");
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane);

        initContent(pane);
        stage.setScene(scene);
        stage.setHeight(550);
        stage.setWidth(550);
        stage.show();
    }

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabSettings = new Tab("Settings");
        Tab tabChat = new Tab("Chat");

        SettingsPane settingsPane = new SettingsPane();
        tabSettings.setContent(settingsPane);
        ChatPane chatPane = new ChatPane();
        tabChat.setContent(chatPane);

        tabPane.getTabs().add(tabSettings);
        tabPane.getTabs().add(tabChat);
    }
}
