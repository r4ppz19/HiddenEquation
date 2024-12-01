package com.r4ppz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/MainView.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Calculator");

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/ModernMinimalistStyle.css")).toExternalForm());

        Image imageIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/mainIcon.png")));
        primaryStage.getIcons().add(imageIcon);

        primaryStage.show();
    }
}