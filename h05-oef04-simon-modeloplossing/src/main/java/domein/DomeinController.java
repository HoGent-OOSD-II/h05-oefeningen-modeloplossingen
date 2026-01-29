package domein;

import util.Kleur;

public class DomeinController {
	private Simon simon;

	public void startNieuwSpel(Kleur[] spelkleuren) {
		simon = new Simon(spelkleuren);
	}

	public void voegNieuweKleurAanKleurenreeksToe() {
		simon.voegNieuweKleurAanKleurenreeksToe();
	}

	public Kleur geefNieuweKleurUitKleurenreeks() {
		return simon.geefNieuweKleurUitKleurenreeks();
	}

	public void kiesKleur(Kleur kleur) {
		simon.verwerkGekozenKleur(kleur);
	}

	public boolean isGoedGeraden() {
		return simon.isKleurGoedGeraden();
	}

	public boolean isReeksVolledigGeraden() {
		return simon.isReeksVolledigGeraden();
	}

	public Kleur geefGemisteKleur() {
		return simon.geefGemisteKleur();
	}

	public int geefAantalgeradenKleuren() {
		return simon.getAantalGoedGeraden();
	}

	public String geefKleurenreeks() {
		String resultaat = "";
		for (Kleur kleur : simon.getKleurenReeks()) {
			resultaat += String.format("%s ", kleur);
		}
		return resultaat;
	}

	public Kleur[] geefSpelKleuren() {
		return simon.getSpelKleuren();
	}
}
