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
import util.PaneelType;

public class OverzichtPaneel extends VBox {

	private final DomeinController dc;
	private final PaneelType paneelType;
	private ListView<String> lijstTitels;

	public OverzichtPaneel(DomeinController dc, PaneelType paneelType) {
		this.dc = dc;
		this.paneelType = paneelType;
		buildGui();
	}

	private void buildGui() {
		this.setPadding(new Insets(10, 10, 50, 10));
		this.setSpacing(20);

		Font lettertype = new Font("Arial", 20);

		Label lblOverzicht = new Label(String.format("Rekening %s", paneelType == PaneelType.VAN ? "-VAN-" : "-NAAR-"));
		lblOverzicht.setFont(lettertype);
		this.getChildren().add(lblOverzicht);
		lijstTitels = new ListView<>();
		List<BeheerskostDTO> beheerskosten = dc.geefAlleRekeningen();
		lijstTitels.setItems(FXCollections.observableArrayList(geefAlleTitels(beheerskosten)));
		lijstTitels.getSelectionModel().selectedIndexProperty().addListener((observedProp, oldValue, newValue) -> {
			int selected = newValue.intValue();
			if (selected != -1) {
				if (paneelType == PaneelType.VAN) {
					dc.selecteerRekeningVan(selected);
				} else {
					dc.selecteerRekeningNaar(selected);
				}
			}
		});
		this.getChildren().add(lijstTitels);
	}

	public void updateLijstRekeningen() {
		List<BeheerskostDTO> beheerskosten = dc.geefAlleRekeningen();
		lijstTitels.getItems().setAll(geefAlleTitels(beheerskosten));
		lijstTitels.getSelectionModel().select(-1);
	}

	private List<String> geefAlleTitels(List<BeheerskostDTO> dtos) {
		List<String> titels = new ArrayList<>();
		for (BeheerskostDTO dto : dtos) {
			String titel = switch (dto.soort()) {
			case 'Z' -> "Zichtrekening";
			case 'S' -> "Spaarrekening";
			default -> "Kluis";
			};
			titels.add(String.format("%s van %s: %.2f Euro", titel, dto.houder(), dto.saldo()));
		}
		return titels;
	}
}
