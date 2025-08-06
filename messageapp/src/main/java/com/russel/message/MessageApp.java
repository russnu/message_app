package com.russel.message;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MessageApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Subscribe.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setWidth(400);
            stage.setHeight(500);
            stage.setScene(scene);
            stage.setTitle("Chat - Subscribe to a Topic");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
