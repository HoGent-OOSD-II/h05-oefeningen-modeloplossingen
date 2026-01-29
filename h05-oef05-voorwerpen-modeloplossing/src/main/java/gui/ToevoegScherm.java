package gui;

import domein.DomeinController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import util.DraagbaarType;

public class ToevoegScherm extends GridPane {
	private DomeinController dc;
	private TextField[] txtKenmerken;
	private DraagbaarType draagbaarType;
	private OverzichtsScherm overzichtsScherm;

	public ToevoegScherm(DomeinController dc, DraagbaarType dt, OverzichtsScherm overzichtsScherm) {
		this.dc = dc;
		this.overzichtsScherm = overzichtsScherm;
		this.draagbaarType = dt;
		buildGui(dt.getKenmerken());
	}

	private void buildGui(String[] kenmerken) {
		setPadding(new Insets(20));
		setVgap(10);
		setHgap(10);
		setAlignment(Pos.TOP_CENTER);
		Label lblTitel = new Label(String.format("%s toevoegen", draagbaarType.name()));
		setHalignment(lblTitel, HPos.CENTER);
		lblTitel.setFont(Font.font("Montserrat", FontWeight.NORMAL, 20));
		add(lblTitel, 0, 0, 2, 1);
		txtKenmerken = new TextField[kenmerken.length];
		for (int rij = 0; rij < kenmerken.length; rij++) {
			Label lblAttribuut = new Label(kenmerken[rij]);
			txtKenmerken[rij] = new TextField();
			add(lblAttribuut, 0, rij + 1);
			add(txtKenmerken[rij], 1, rij + 1);
		}

		Button btnVoegToe = new Button("Voeg toe");
		btnVoegToe.setPrefWidth(120);
		setHalignment(btnVoegToe, HPos.RIGHT);
		btnVoegToe.setOnAction(evt -> voegToe());

		Button btnCancel = new Button("Annuleer");
		btnCancel.setPrefWidth(120);
		setHalignment(btnCancel, HPos.LEFT);
		btnCancel.setOnAction(evt -> gaTerugNaarOverzichtsScherm());

		add(btnVoegToe, 0, kenmerken.length + 1);
		add(btnCancel, 1, kenmerken.length + 1);
		GridPane.setHalignment(btnVoegToe, HPos.LEFT);
		GridPane.setHalignment(btnCancel, HPos.RIGHT);
	}

	private void gaTerugNaarOverzichtsScherm() {
		this.getScene().setRoot(overzichtsScherm);
	}

	private void voegToe() {
		try {
			switch (draagbaarType) {
			case WAPEN -> dc.voegWapenToe(txtKenmerken[0].getText(), Double.parseDouble(txtKenmerken[1].getText()),
					Integer.parseInt(txtKenmerken[2].getText()), Integer.parseInt(txtKenmerken[3].getText()),
					txtKenmerken[4].getText().equalsIgnoreCase("ja"));
			case SLEUTEL -> dc.voegSleutelToe(txtKenmerken[0].getText(), Double.parseDouble(txtKenmerken[1].getText()),
					Integer.parseInt(txtKenmerken[2].getText()), Integer.parseInt(txtKenmerken[3].getText()));
			default -> dc.voegGebouwToe(txtKenmerken[0].getText(), Integer.parseInt(txtKenmerken[1].getText()));
			}
			overzichtsScherm.refresh();
			gaTerugNaarOverzichtsScherm();
		} catch (NumberFormatException e) {
			signaliseerFout("Niet alle invoervelden zijn juist ingevuld");
		} catch (IllegalArgumentException e) {
			signaliseerFout(e.getMessage());
		}
	}

	private void signaliseerFout(String melding) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Foutieve invoer");
		alert.setContentText(melding);
		alert.showAndWait();
	}
}
