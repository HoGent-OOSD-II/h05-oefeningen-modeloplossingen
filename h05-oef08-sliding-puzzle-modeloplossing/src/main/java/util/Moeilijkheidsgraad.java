package util;

public enum Moeilijkheidsgraad {

	EASY(3), MEDIUM(10), HARD(250);

	private int aantalVerschuivingen;

	Moeilijkheidsgraad(int aantalVerschuivingen) {
		this.aantalVerschuivingen = aantalVerschuivingen;
	}

	public int getAantalVerschuivingen() {
		return aantalVerschuivingen;
	}
}
