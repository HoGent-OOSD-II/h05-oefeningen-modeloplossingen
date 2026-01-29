package gui;

import domein.DomeinController;
import javafx.scene.layout.HBox;

public class HoofdPaneel extends HBox {
	private final DomeinController dc;

	public HoofdPaneel(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		DetailPaneel detailPaneel = new DetailPaneel(dc);
		OverzichtPaneel overzichtsPaneel = new OverzichtPaneel(dc, detailPaneel);
		this.getChildren().addAll(overzichtsPaneel, detailPaneel);
	}
}
