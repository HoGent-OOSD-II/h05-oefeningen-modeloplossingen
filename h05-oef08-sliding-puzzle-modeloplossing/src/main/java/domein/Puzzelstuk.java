package domein;

import java.util.Objects;

public class Puzzelstuk {
	private final String image;

	public Puzzelstuk() {
		this.image = null;
	}

	public Puzzelstuk(String image) {
		this.image = image;
	}

	public int geefCorrecteRij() {
		return Integer.parseInt(image.substring(4, 5));
	}

	public int geefCorrecteKolom() {
		return Integer.parseInt(image.substring(13, 14));
	}

	@Override
	public int hashCode() {
		return Objects.hash(image);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Puzzelstuk other = (Puzzelstuk) obj;
		return Objects.equals(image, other.image);
	}

	public String getImage() {
		return image;
	}

}
