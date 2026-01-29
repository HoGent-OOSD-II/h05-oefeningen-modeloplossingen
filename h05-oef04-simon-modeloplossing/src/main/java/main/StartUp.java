package main;

import domein.DomeinController;
import gui.KiesSpelKleurenScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {

	@Override
	public void start(Stage primaryStage) {
		DomeinController dc = new DomeinController();
		KiesSpelKleurenScherm root = new KiesSpelKleurenScherm(dc);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setWidth(400);
		primaryStage.setHeight(400);
		primaryStage.setTitle("Simon says...");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
