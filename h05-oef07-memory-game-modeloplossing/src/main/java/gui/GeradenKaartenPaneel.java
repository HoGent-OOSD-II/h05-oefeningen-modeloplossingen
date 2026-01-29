package gui;

import java.util.List;

import domein.DomeinController;
import dto.KaartDTO;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GeradenKaartenPaneel extends HBox {

	private DomeinController dc;

	public void voegKaartjeToe(ImageView ivKaartje) {
		this.getChildren().add(ivKaartje);
	}

	public GeradenKaartenPaneel(DomeinController dc) {
		this.dc = dc;
		setPrefHeight(73);
		setSpacing(10);
		setId("geradenKaartjes");
		setPadding(new Insets(10));
		toonGeradenKaarten();
	}

	public void toonGeradenKaarten() {
		getChildren().clear();
		List<KaartDTO> kaarten = dc.geefGeradenKaarten();
		for (KaartDTO kaart : kaarten) {
			Image imgCard = new Image(getClass().getResourceAsStream(KaartenPaneel.PAD_NAAR_IMAGES + kaart.image()));
			ImageView ivCard = new ImageView(imgCard);
			ivCard.setFitWidth(50);
			ivCard.setPreserveRatio(true);
			getChildren().add(ivCard);
		}
	}
}
