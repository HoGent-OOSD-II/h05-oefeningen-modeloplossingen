package gui;

import domein.DomeinController;
import domein.Simon;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import util.Kleur;

public class SimonScherm extends GridPane {
	private final static int AANTAL_RIJEN = Simon.AANTAL_KLEUREN / 2 + Simon.AANTAL_KLEUREN % 2;
	private final DomeinController dc;
	private ToonNieuweKleurScherm toonNieuweKleurScherm;

	public SimonScherm(DomeinController dc, ToonNieuweKleurScherm toonNieuweKleurScherm) {
		this.dc = dc;
		this.toonNieuweKleurScherm = toonNieuweKleurScherm;
		bouwScherm();
	}

	private void bouwScherm() {
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(50);
		this.getColumnConstraints().addAll(col, col);
		RowConstraints row = new RowConstraints();
		row.setPercentHeight(100 / AANTAL_RIJEN);
		for (int i = 0; i < AANTAL_RIJEN; i++) {
			this.getRowConstraints().add(row);
		}
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5));
		voegButtonsToe();
	}

	private void voegButtonsToe() {
		Kleur[] spelKleuren = dc.geefSpelKleuren();
		for (int rij = 0; rij < AANTAL_RIJEN; rij++) {
			for (int kol = 0; kol < 2; kol++) {
				if (rij * 2 + kol < Simon.AANTAL_KLEUREN) {
					final Kleur kleur = spelKleuren[rij * 2 + kol];
					int[] rgb = kleur.getRgb();
					Button btn = new Button();
					btn.setBackground(new Background(
							new BackgroundFill(Color.rgb(rgb[0], rgb[1], rgb[2]), new CornerRadii(20), null)));
					btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					btn.setOnAction((evt) -> verwerkGekozenKleurVanSpeler(kleur));
					btn.setCursor(Cursor.HAND);
					this.add(btn, kol, rij);
				}
			}

		}
	}

	private void verwerkGekozenKleurVanSpeler(Kleur kleur) {
		dc.kiesKleur(kleur);
		if (dc.isGoedGeraden()) {
			if (dc.isReeksVolledigGeraden()) {
				alertKleurJuistGeraden();
				toonNieuweKleurScherm.toonVolgendeKleur();
				this.getScene().setRoot(toonNieuweKleurScherm);
			}
		} else
			alertEindeSpel(kleur);
	}

	private void alertEindeSpel(Kleur gekozenKleur) {
		Kleur gemisteKleur = dc.geefGemisteKleur();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Fout in reeks!");
		alert.setHeaderText(String.format("Voor kleur %d klikte je op %s in plaats van %s",
				dc.geefAantalgeradenKleuren() + 1, gekozenKleur, gemisteKleur));
		alert.setContentText(String.format("Dit was de reeks: %s", dc.geefKleurenreeks()));
		alert.showAndWait();
		Platform.exit();
	}

	private void alertKleurJuistGeraden() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Succes!");
		int aantalGeraden = dc.geefAantalgeradenKleuren();
		alert.setHeaderText(String.format("Je bent reeds %d kleur%s ver.", aantalGeraden,
				aantalGeraden > 1 || aantalGeraden == 0 ? "en" : ""));
		alert.setContentText("We voegen nog een kleur toe aan de reeks...");
		alert.showAndWait();
	}
}
