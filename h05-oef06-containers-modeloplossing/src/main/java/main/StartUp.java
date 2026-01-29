package main;

import domein.DomeinController;
import gui.OverzichtsScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DomeinController dc = new DomeinController();
        // Het OverzichtsScherm is niet met SceneBuilder gemaakt
        // We gebruiken een klassieke constructor aanroep
        Scene scene = new Scene(new OverzichtsScherm(dc), 650, 350);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("ContainerApp");
        primaryStage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}