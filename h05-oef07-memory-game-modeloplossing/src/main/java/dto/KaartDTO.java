package dto;

import domein.Kaart;

public record KaartDTO(String image, int posX, int posY, boolean faceUp) {

	public KaartDTO(Kaart kaart) {
		this(kaart.getImage(), kaart.getKolom(), kaart.getRij(), kaart.isFaceUp());
	}
}
