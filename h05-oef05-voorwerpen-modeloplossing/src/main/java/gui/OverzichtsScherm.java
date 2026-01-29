/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;

import domein.DomeinController;
import dto.DraagbaarDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import util.DraagbaarType;

public class OverzichtsScherm extends VBox {
	private final DomeinController dc;
	private TextArea txaOverzicht;

	public OverzichtsScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		setPadding(new Insets(20));
		setAlignment(Pos.TOP_CENTER);
		setSpacing(15);
		Label lblTitel = new Label("Overzicht alle items");
		lblTitel.setFont(Font.font("Montserrat", FontWeight.NORMAL, 20));
		HBox hboxButtons = new HBox();
		for (DraagbaarType type : DraagbaarType.values()) {
			Button btn = new Button(String.format("Voeg %s toe", type.toString().toLowerCase()));
			btn.setOnAction(evt -> voegDraagbaarToe(type));
			hboxButtons.getChildren().add(btn);
		}
		hboxButtons.setAlignment(Pos.CENTER);
		hboxButtons.setSpacing(10);
		txaOverzicht = new TextArea(geefOverzichtVanAlleItems());
		getChildren().addAll(lblTitel, txaOverzicht, hboxButtons);
	}

	private void voegDraagbaarToe(DraagbaarType dt) {
		this.getScene().setRoot(new ToevoegScherm(dc, dt, this));
	}

	public void refresh() {
		txaOverzicht.setText(geefOverzichtVanAlleItems());
	}

	private String geefOverzichtVanAlleItems() {
		List<DraagbaarDTO> draagbaarDTOs = dc.geefOverzicht();
		if (draagbaarDTOs.isEmpty())
			return String.format("Er zijn geen items...");
		String overzicht = "";
		for (DraagbaarDTO dto : draagbaarDTOs) {
			overzicht += switch (dto.soort()) {
			case 'W' -> String.format(
					"Wapen \"%s\" met gewicht %.3f kg uit niveau %d, is %s draagbaar.%nDit wapen heeft kracht %d en is %s gebruikt.%n%n",
					dto.naam(), dto.gewicht(), dto.niveau(), dto.draagbaar() ? "" : "niet ", dto.kracht(),
					dto.gebruikt() ? "al" : "nog niet");
			case 'S' ->
				String.format("Sleutel \"%s\" met gewicht %.3f kg uit niveau %d past op deur %d en is %sdraagbaar.%n%n",
						dto.naam(), dto.gewicht(), dto.niveau(), dto.deur(), dto.draagbaar() ? "" : "niet ");
			default -> String.format("Gebouw \"%s\" is %.2f meter hoog en is %s draagbaar.%n%n", dto.naam(),
					dto.hoogte(), dto.draagbaar() ? "" : "niet ");
			};
		}
		return overzicht;
	}
}
