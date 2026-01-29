package gui;

import domein.DomeinController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ToonNieuweKleurScherm extends VBox {
	private final DomeinController dc;
	private final SimonScherm simonScherm;
	private Label lblNieuweKleur;

	public ToonNieuweKleurScherm(DomeinController dc) {
		super();
		this.dc = dc;
		simonScherm = new SimonScherm(dc, this);
		bouwScherm();
	}

	private void bouwScherm() {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);

		Label lblKies = new Label("De nieuwe kleur is");
		lblKies.setFont(Font.font("Montserrat", FontWeight.NORMAL, 18));

		lblNieuweKleur = new Label();
		lblNieuweKleur.setFont(Font.font("Montserrat", FontWeight.BOLD, 24));

		Button btnOK = new Button("OK");
		btnOK.setOnAction((evt) -> springNaarSimonScherm());

		this.getChildren().addAll(lblKies, lblNieuweKleur, btnOK);

		toonVolgendeKleur();
	}

	public void toonVolgendeKleur() {
		dc.voegNieuweKleurAanKleurenreeksToe();
		lblNieuweKleur.setText(dc.geefNieuweKleurUitKleurenreeks().toString());
	}

	public void springNaarSimonScherm() {
		this.getScene().setRoot(simonScherm);
	}
}
