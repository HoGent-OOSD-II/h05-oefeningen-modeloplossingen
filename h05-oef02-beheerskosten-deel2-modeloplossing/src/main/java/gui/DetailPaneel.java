package gui;

import domein.DomeinController;
import dto.BeheerskostDTO;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import util.TransactieType;

public class DetailPaneel extends GridPane {
	private final DomeinController dc;
	private TextField txfRekeningNr;
	private TextField txfSaldo;
	private TextField txfHouder;
	private TextField txfBedrag;

	public DetailPaneel(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPrefWidth(100);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPrefWidth(150);
		this.getColumnConstraints().addAll(col1, col2, col1);

		Font lettertype = new Font("Arial", 20);
		Label lblRekeningNr, lblSaldo, lblHouder, lblDetails;
		lblRekeningNr = new Label("Rekeningnr:");
		lblSaldo = new Label("Saldo:");
		lblHouder = new Label("Houder:");
		lblDetails = new Label("Details:");
		lblDetails.setFont(lettertype);

		txfRekeningNr = new TextField();
		txfRekeningNr.setEditable(false);
		txfSaldo = new TextField();
		txfSaldo.setEditable(false);
		txfHouder = new TextField();
		txfHouder.setEditable(false);
		txfBedrag = new TextField();
		txfBedrag.setPromptText("bedrag");

		Button btnStort = new Button("storten");
		Button btnHaalAf = new Button("afhalen");

		this.add(lblDetails, 0, 0, 2, 1);
		this.add(lblRekeningNr, 0, 1);
		this.add(txfRekeningNr, 1, 1, 2, 1);
		this.add(lblSaldo, 0, 2);
		this.add(txfSaldo, 1, 2, 2, 1);
		this.add(lblHouder, 0, 3);
		this.add(txfHouder, 1, 3, 2, 1);
		this.add(txfBedrag, 0, 4, 3, 1);
		this.add(btnStort, 0, 5);
		this.add(btnHaalAf, 2, 5);
		GridPane.setMargin(lblDetails, new Insets(0, 0, 10, 0));
		GridPane.setMargin(txfBedrag, new Insets(10, 0, 0, 0));
		GridPane.setHalignment(btnHaalAf, HPos.RIGHT);

		this.setPadding(new Insets(10));
		this.setVgap(10);
		this.setHgap(10);

		btnStort.setOnAction((evt) -> verwerkBedrag(TransactieType.STORTEN));
		btnHaalAf.setOnAction((evt) -> verwerkBedrag(TransactieType.AFHALEN));
	}

	private void verwerkBedrag(TransactieType transactieType) {
		try {
			double bedrag = Double.parseDouble(txfBedrag.getText());
			switch (transactieType) {
			case STORTEN -> dc.stortOpGeselecteerdeRekening(bedrag);
			case AFHALEN -> dc.haalAfVanGeselecteerdeRekening(bedrag);
			}
			geefFeedback(AlertType.INFORMATION, "Het saldo van deze rekening werd aangepast.");
		} catch (NumberFormatException nfe) {
			geefFeedback(AlertType.WARNING, "Dit is geen geldig bedrag!");
		} catch (IllegalArgumentException iae) {
			geefFeedback(AlertType.WARNING, iae.getMessage());
		}
	}

	public void updateScherm() {
		BeheerskostDTO dto = dc.geefGeslecteerdeBeheerskost();
		if (dto != null) {
			txfRekeningNr.setText(geefRekeningnummer(dto));
			txfSaldo.setText(String.format("%.2f Euro", dto.saldo()));
			txfHouder.setText(dto.houder());
			txfBedrag.setText("");
		}
	}

	private void geefFeedback(AlertType alertType, String message) {
		updateScherm();
		Alert boodschap = new Alert(alertType);
		boodschap.setTitle(String.format("Transactie %sgeslaagd", alertType == AlertType.WARNING ? "niet " : ""));
		boodschap.setContentText(message);
		boodschap.showAndWait();
		txfBedrag.requestFocus();
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
