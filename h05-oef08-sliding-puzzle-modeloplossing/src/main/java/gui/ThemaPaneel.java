package gui;

import domein.DomeinController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import util.Thema;

public class ThemaPaneel extends GridPane {

	private DomeinController dc;
	private Thema thema;
	private SpelScherm mijnContainer;
	private InstellingenPaneel instellingenPaneel;

	public ThemaPaneel(DomeinController dc, SpelScherm spelScherm) {
		this.dc = dc;
		this.mijnContainer = spelScherm;
		toonDeVerschillendePuzzels();
	}

	private void toonDeVerschillendePuzzels() {
		for (int i = 0; i < Thema.values().length; i++) {
			getColumnConstraints().add(new ColumnConstraints(300));
		}
		getRowConstraints().add(new RowConstraints(300));
		getRowConstraints().add(new RowConstraints(50));
		setAlignment(Pos.CENTER);
		setHgap(10);
		setPadding(new Insets(50));
		int column = 0;
		for (Thema thema : Thema.values()) {
			String imagePath = String.format("/images/%s/original.jpg", thema.name().toLowerCase());
			ImageView ivCard = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
			ivCard.setFitWidth(300);
			ivCard.setPreserveRatio(true);
			ivCard.setCursor(Cursor.HAND);
			add(ivCard, column, 0);
			ivCard.setOnMouseClicked(evt -> kiesThema(thema));
			column++;
		}

		Label lblStart = new Label("Select your image to start the puzzle...");
		lblStart.getStyleClass().addAll("settingsText", "startText");
		GridPane.setHalignment(lblStart, HPos.CENTER);
		add(lblStart, 0, 1, 2, 2);
	}

	private void kiesThema(Thema thema) {
		this.thema = thema;
		mijnContainer.maakPuzzel();
	}

	public Thema getThema() {
		return thema;
	}

}
