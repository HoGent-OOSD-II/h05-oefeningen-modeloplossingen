package domein;

import java.util.Objects;

public class Kaart {
	private static final int GEEN_PLAATS = -1;
	private final String image;
	private boolean faceUp;
	private int kolom;
	private int rij;

	public Kaart(String image) {
		this.image = image;
		faceUp = false;
		kolom = GEEN_PLAATS;
		rij = GEEN_PLAATS;
	}

	public void kenPlaatsToe(int kolom, int rij) {
		this.kolom = kolom;
		this.rij = rij;
	}

	public void draaiOm() {
		faceUp = !faceUp;
	}

	public String getImage() {
		return image;
	}

	public boolean isFaceUp() {
		return faceUp;
	}

	public int getKolom() {
		if (kolom == GEEN_PLAATS)
			throw new IllegalArgumentException("De kaart kreeg nog geen plaats toegewezen");
		return kolom;
	}

	public int getRij() {
		if (rij == GEEN_PLAATS)
			throw new IllegalArgumentException("De kaart kreeg nog geen plaats toegewezen");
		return rij;
	}

	public boolean heeftZelfdeAfbeeldingAls(Kaart kaart) {
		return kaart != null && this.image.equals(kaart.image);
	}

	@Override
	public int hashCode() {
		return Objects.hash(faceUp, image, kolom, rij);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kaart other = (Kaart) obj;
		return faceUp == other.faceUp && Objects.equals(image, other.image) && kolom == other.kolom && rij == other.rij;
	}

	@Override
	protected Kaart clone() {
		Kaart k = new Kaart(image);
		k.kenPlaatsToe(kolom, rij);
		return k;
	}
}
