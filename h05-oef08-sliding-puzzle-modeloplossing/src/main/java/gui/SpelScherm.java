package gui;

import domein.DomeinController;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;

public class SpelScherm extends BorderPane {
	private final DomeinController dc;
	private final InstellingenPaneel instellingenPaneel;
	private final ThemaPaneel themaPaneel;

	public SpelScherm(DomeinController dc) {
		this.dc = dc;
		instellingenPaneel = new InstellingenPaneel();
		themaPaneel = new ThemaPaneel(dc, this);
		Image imgBackground = new Image(getClass().getResourceAsStream("/images/background.jpg"));
		setBackground(new Background(new BackgroundImage(imgBackground, null, null, null, null)));
		kiesThemaEnInstellingen();
	}

	public void kiesThemaEnInstellingen() {
		setCenter(themaPaneel);
		setLeft(instellingenPaneel);
		setBottom(null);
	}

	public void maakPuzzel() {
		dc.maakPuzzel(themaPaneel.getThema(), instellingenPaneel.geefAantalStukken(),
				instellingenPaneel.geefMoeilijkheidsgraad());
		PuzzelPaneel puzzelPaneel = new PuzzelPaneel(dc, this);
		setCenter(puzzelPaneel);
		setLeft(null);
	}
}
