package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class SpelScherm extends BorderPane {
	private final DomeinController dc;

	public SpelScherm(DomeinController dc) {
		this.dc = dc;
		startSpel();
	}

	public void startSpel() {
		dc.startNieuwSpel();
		GeradenKaartenPaneel gkPaneel = new GeradenKaartenPaneel(dc);
		KaartenPaneel kaartPaneel = new KaartenPaneel(dc, gkPaneel, this);
		setCenter(kaartPaneel);
		setBottom(gkPaneel);
		setMargin(gkPaneel, new Insets(10, 10, 10, 10));
	}

}
