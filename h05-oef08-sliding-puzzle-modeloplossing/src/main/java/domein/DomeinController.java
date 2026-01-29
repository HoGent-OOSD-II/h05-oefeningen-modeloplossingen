package domein;

import util.Moeilijkheidsgraad;
import util.Thema;

public class DomeinController {
	private Puzzel puzzel;

	public void maakPuzzel(Thema thema, int aantalStukken, Moeilijkheidsgraad moeilijkheidsgraad) {
		this.puzzel = new Puzzel(thema, aantalStukken, moeilijkheidsgraad);
	}

	public String[][] geefPuzzel() {
		String[][] puzzelstukken = new String[puzzel.getDimensie()][puzzel.getDimensie()];
		for (int rij = 0; rij < puzzel.getDimensie(); rij++)
			for (int kolom = 0; kolom < puzzel.getDimensie(); kolom++) {
				Puzzelstuk puzzelstuk = puzzel.geefPuzzelstuk(kolom, rij);
				puzzelstukken[kolom][rij] = puzzelstuk == null ? null : puzzelstuk.getImage();
			}
		return puzzelstukken;
	}

	public void verschuifPuzzelstuk(int kolom, int rij) {
		puzzel.verschuifPuzzelstuk(kolom, rij);
	}

	public int geefAantalClicks() {
		return puzzel.getAantalClicks();
	}

	public boolean isPuzzelOpgelost() {
		return puzzel.isOpgelost();
	}

	public Thema geefThema() {
		return puzzel.getThema();
	}

	public int geefAantalStukken() {
		return puzzel.geefAantalStukken();
	}

	public int geefDimensie() {
		return puzzel.getDimensie();
	}
}
