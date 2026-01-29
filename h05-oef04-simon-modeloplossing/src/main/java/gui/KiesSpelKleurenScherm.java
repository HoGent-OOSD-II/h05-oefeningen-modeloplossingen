package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import domein.Simon;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import util.Kleur;

public class KiesSpelKleurenScherm extends VBox {
	private final DomeinController dc;
	private Kleur[] gekozenKleuren;
	private Label lblFeedback;

	public KiesSpelKleurenScherm(DomeinController dc) {
		this.dc = dc;
		gekozenKleuren = new Kleur[Simon.AANTAL_KLEUREN];
		bouwScherm();
	}

	private void bouwScherm() {
		this.setPadding(new Insets(20));
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		Label lblKiesKleuren = new Label(String.format("Kies de %d kleuren", Simon.AANTAL_KLEUREN));
		lblKiesKleuren.setFont(Font.font("Montserrat", FontWeight.NORMAL, 20));
		this.getChildren().add(lblKiesKleuren);

		List<String> kleurenStrings = new ArrayList<>();
		for (Kleur k : Kleur.values()) {
			kleurenStrings.add(k.toString());
		}
		for (int i = 0; i < Simon.AANTAL_KLEUREN; i++) {
			final int kleurIndex = i;
			ComboBox<String> cmbKleuren = new ComboBox<>(FXCollections.observableList(kleurenStrings));
			cmbKleuren.setPromptText(String.format("Kies kleur %d", i + 1));
			this.getChildren().add(cmbKleuren);
			cmbKleuren
					.setOnAction(evt -> registreerKleur(kleurIndex, cmbKleuren.getSelectionModel().getSelectedItem()));

		}
		Button btnStartSpel = new Button("Start spel");
		btnStartSpel.setOnAction(evt -> startSpel());
		lblFeedback = new Label();
		this.getChildren().addAll(btnStartSpel, lblFeedback);
	}

	private void startSpel() {
		try {
			dc.startNieuwSpel(gekozenKleuren);
			this.getScene().setRoot(new ToonNieuweKleurScherm(dc));
		} catch (Exception e) {
			lblFeedback.setText(e.getMessage());
		}
	}

	private void registreerKleur(int kleurIndex, String selectedItem) {
		lblFeedback.setText("");
		gekozenKleuren[kleurIndex] = Kleur.valueOf(selectedItem);
	}
}
