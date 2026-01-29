package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import dto.BeheerskostDTO;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BeheersKostenScherm extends VBox {
	private final DomeinController dc;
	private ComboBox<String> cboTitels;
	private Label lblDetails, lblKost;
	private TextArea txaDetails;
	private TextField txfKost;

	public BeheersKostenScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		this.setPadding(new Insets(20));

		// ComboBox met titels van alle beheerskosten, prompt instellen en event handler
		// instellen
		cboTitels = new ComboBox<>();
		cboTitels.setItems(FXCollections.observableList(geefAlleTitels(dc.geefAlleBeheerskosten())));
		cboTitels.setPromptText("Over welk item wil je meer weten?");
		cboTitels.setOnAction(evt -> toonDetailsBeheerskost(cboTitels.getSelectionModel().getSelectedIndex()));

		// Label & niet editeerbaar textArea om de details te tonen
		lblDetails = new Label("Details");
		lblDetails.setPadding(new Insets(15, 0, 0, 0));
		txaDetails = new TextArea();
		txaDetails.setEditable(false);
		txaDetails.setWrapText(true);

		// Label & niet editeerbaar textField voor de kost te tonen
		lblKost = new Label("Kost");
		lblKost.setPadding(new Insets(15, 0, 0, 0));
		txfKost = new TextField();
		txfKost.setEditable(false);

		this.getChildren().addAll(cboTitels, lblDetails, txaDetails, lblKost, txfKost);
	}

	// Aangeroepen door de event handler van de combobox
	private void toonDetailsBeheerskost(int index) {
		BeheerskostDTO dto = dc.geefAlleBeheerskosten().get(index);
		String details = geefDetails(dto);
		txaDetails.setText(details);
		txfKost.setText(String.format("De kost bedraagt € %.2f", dto.jaarlijkseKost()));
	}

	// Deze methode wordt gebruikt om de titels van de beheerskosten op te halen (om
	// in de Combobox te zetten)
	private List<String> geefAlleTitels(List<BeheerskostDTO> dtos) {
		List<String> titels = new ArrayList<>();
		for (BeheerskostDTO dto : dc.geefAlleBeheerskosten()) {
			String titel = switch (dto.soort()) {
			case 'Z' -> "Zichtrekening";
			case 'S' -> "Spaarrekening";
			default -> "Kluis";
			};
			titels.add(String.format("%s van %s", titel, dto.houder()));
		}
		return titels;
	}

	// Deze methode wordt gebruikt om de details van een Beheerskost te bepalen
	private String geefDetails(BeheerskostDTO dto) {
		return switch (dto.soort()) {
		case 'S' -> String.format(
				"De spaarrekening met rekeningnummer %s van %s bevat %.2f Euro.%nDe aangroeiintrest bedraagt %.2f%%",
				geefRekeningnummer(dto), dto.houder(), dto.saldo(), dc.geefAangroeiIntrestSpaarRekening());
		case 'Z' -> String.format(
				"De zichtrekening met rekeningnummer %s van %s bevat %.2f Euro.%nOp deze rekening kan tot %.2f Euro onder nul gegaan worden.",
				geefRekeningnummer(dto), dto.houder(), dto.saldo(), Math.abs(dto.maxKredietOnderNul()));
		default -> String.format("De kluis van %s heeft kluisnummer %d", dto.houder(), dto.kluisnr());
		};
	}

	private String geefRekeningnummer(BeheerskostDTO dto) {
		long reknr = dto.rekeningnr();
		long eerste = reknr / 1000000000;
		long middenste = reknr % 1000000000;
		long laatste = middenste % 100;
		middenste = middenste / 100;
		return String.format("%03d-%07d-%02d", eerste, middenste, laatste);
	}
}