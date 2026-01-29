package main;

import domein.DomeinController;
import gui.BeheersKostenScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
	@Override
	public void start(Stage primaryStage) {
		DomeinController dc = new DomeinController();
		BeheersKostenScherm bs = new BeheersKostenScherm(dc);
		Scene scene = new Scene(bs, 500, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Beheerskosten");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
