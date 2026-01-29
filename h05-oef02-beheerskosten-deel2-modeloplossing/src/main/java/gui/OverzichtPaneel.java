package gui;

import java.util.ArrayList;
import java.util.List;

import domein.DomeinController;
import dto.BeheerskostDTO;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class OverzichtPaneel extends VBox {

	private final DetailPaneel detailPaneel;
	private final DomeinController dc;

	public OverzichtPaneel(DomeinController dc, DetailPaneel detailPaneel) {
		this.dc = dc;
		this.detailPaneel = detailPaneel;
		buildGui();
	}

	private void buildGui() {
		this.setPadding(new Insets(10, 10, 50, 10));
		this.setSpacing(20);

		Font lettertype = new Font("Arial", 20);

		Label lblOverzicht = new Label("Overzicht:");
		lblOverzicht.setFont(lettertype);
		this.getChildren().add(lblOverzicht);

		List<BeheerskostDTO> beheerskosten = dc.geefAlleRekeningen();
		ListView<String> lijst = new ListView<>();
		lijst.setItems(FXCollections.observableArrayList(geefAlleTitels(beheerskosten)));
		lijst.getSelectionModel().selectedIndexProperty().addListener((observedProp, oldValue, newValue) -> {
			dc.selecteerRekening(newValue.intValue());
			detailPaneel.updateScherm();
		});
		this.getChildren().add(lijst);
	}

	private List<String> geefAlleTitels(List<BeheerskostDTO> dtos) {
		List<String> titels = new ArrayList<>();
		for (BeheerskostDTO dto : dtos) {
			String titel = switch (dto.soort()) {
			case 'Z' -> "Zichtrekening";
			case 'S' -> "Spaarrekening";
			default -> "Kluis";
			};
			titels.add(String.format("%s van %s", titel, dto.houder()));
		}
		return titels;
	}

}
