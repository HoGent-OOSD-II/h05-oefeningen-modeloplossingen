package util;

public enum DraagbaarType {

	SLEUTEL(new String[] { "Naam", "Gewicht", "Niveau", "Deur" }),
	WAPEN(new String[] { "Naam", "Gewicht", "Niveau", "Kracht", "Gebruikt" }),
	GEBOUW(new String[] { "Naam", "Hoogte" });

	private final String[] kenmerken;

	DraagbaarType(String[] kenmerken) {
		this.kenmerken = kenmerken;
	}

	public String[] getKenmerken() {
		return kenmerken;
	}
}
