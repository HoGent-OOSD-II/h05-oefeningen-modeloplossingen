package gui;

import domein.DomeinController;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class WelkomScherm extends StackPane {

	private final DomeinController dc;

	public WelkomScherm(DomeinController dc) {
		this.dc = dc;
		bouwScherm();
	}

	private void bouwScherm() {
		Image imgWelkom = new Image(getClass().getResourceAsStream("/images/welkom.jpg"));
		ImageView ivWelkom = new ImageView(imgWelkom);
		ivWelkom.setFitWidth(1500);
		ivWelkom.setPreserveRatio(true);
		Label lblEnter = new Label("MEMORY\n   GAME");
		lblEnter.getStyleClass().addAll("styledText", "bigText");
		setCursor(Cursor.HAND);
		setOnMouseClicked(evt -> gaNaarSpelScherm());
		getChildren().addAll(ivWelkom, lblEnter);
	}

	private void gaNaarSpelScherm() {
		getScene().setRoot(new SpelScherm(dc));
	}

}
