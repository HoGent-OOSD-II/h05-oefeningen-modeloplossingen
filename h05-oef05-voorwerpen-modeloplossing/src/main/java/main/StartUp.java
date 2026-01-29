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
		Scene scene = new Scene(new OverzichtsScherm(dc), 600, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Wapens, sleutels en gebouwen");
		primaryStage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}
}
