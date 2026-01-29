package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import util.PaneelType;

public class HoofdPaneel extends HBox {
	private final DomeinController dc;

	public HoofdPaneel(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		this.setPadding(new Insets(20));
		OverzichtPaneel overzichtPaneel1 = new OverzichtPaneel(dc, PaneelType.VAN);
		OverzichtPaneel overzichtPaneel2 = new OverzichtPaneel(dc, PaneelType.NAAR);
		OverschrijfPaneel overschrijfPaneel = new OverschrijfPaneel(dc, overzichtPaneel1, overzichtPaneel2);
		this.getChildren().addAll(overzichtPaneel1, overschrijfPaneel, overzichtPaneel2);
	}
}
