package domein;

public class Kluis implements Beheerskost {
	private final String houder;
	private final int kluisnummer;
	private final static double JAARLIJKSE_KOST = 29;

	public Kluis(String houder, int kluisnummer) {
		// geen controle op houder en kluisnummer
		this.houder = houder;
		this.kluisnummer = kluisnummer;
	}

	@Override
	public double geefJaarlijkseKost() {
		return JAARLIJKSE_KOST;
	}

	public String getHouder() {
		return houder;
	}

	public int getKluisnummer() {
		return kluisnummer;
	}
}
