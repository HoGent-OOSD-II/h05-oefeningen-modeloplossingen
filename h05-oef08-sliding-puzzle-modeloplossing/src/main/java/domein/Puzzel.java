package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import util.Moeilijkheidsgraad;
import util.Richting;
import util.Thema;

public class Puzzel {
	private final Thema thema;
	private final Moeilijkheidsgraad moeilijkheidsgraad;
	private final int dimensie;
	private Puzzelstuk[][] bord;
	private int aantalClicks;

	public Puzzel(Thema thema, int aantalStukken, Moeilijkheidsgraad moeilijkheidsgraad) {
		controleerThema(thema);
		this.thema = thema;
		controleerAantalStukken(aantalStukken);
		dimensie = aantalStukken == 16 ? 4 : 5;
		controleerMoeilijkheidsgraad(moeilijkheidsgraad);
		this.moeilijkheidsgraad = moeilijkheidsgraad;
		maakPuzzel();
		aantalClicks = 0;
	}

	public Puzzelstuk geefPuzzelstuk(int kolom, int rij) {
		return bord[kolom][rij];
	}

	public boolean verschuifPuzzelstuk(int kolom, int rij) {
		for (Richting richting : Richting.values())
			if (isVerschuivingMogelijk(kolom, rij, richting)) {
				bord[kolom + richting.getX()][rij + richting.getY()] = geefPuzzelstuk(kolom, rij);
				bord[kolom][rij] = null;
				aantalClicks++;
				return true;
			}
		return false;
	}

	public boolean isOpgelost() {
		if (bord[dimensie - 1][dimensie - 1] != null)
			return false;
		for (int rij = 0; rij < dimensie; rij++)
			for (int kolom = 0; kolom < dimensie; kolom++)
				if (bord[kolom][rij] != null && (bord[kolom][rij].geefCorrecteKolom() != kolom + 1
						|| bord[kolom][rij].geefCorrecteRij() != rij + 1))
					return false;
		return true;
	}

	public int getAantalClicks() {
		return aantalClicks;
	}

	public int getDimensie() {
		return dimensie;
	}

	public int geefAantalStukken() {
		return dimensie * dimensie;
	}

	public Thema getThema() {
		return thema;
	}

	private void maakPuzzel() {
		bord = new Puzzelstuk[dimensie][dimensie];
		maakPuzzelMetAllePuzzelstukkenOpCorrectePlaats();
		gooiPuzzelstukkenDoorElkaar(moeilijkheidsgraad);
	}

	private void maakPuzzelMetAllePuzzelstukkenOpCorrectePlaats() {
		for (int rij = 0; rij < dimensie; rij++)
			for (int kolom = 0; kolom < dimensie; kolom++)
				bord[kolom][rij] = new Puzzelstuk(String.format("row-%d-column-%d.jpg", rij + 1, kolom + 1));
		bord[dimensie - 1][dimensie - 1] = null;
	}

	private void gooiPuzzelstukkenDoorElkaar(Moeilijkheidsgraad moeilijkheidsgraad) {
		var sr = new SecureRandom();
		for (int i = 0; i < moeilijkheidsgraad.getAantalVerschuivingen(); i++) {
			int[] vrijePlaats = geefCoordinatenVanVrijePlaatsOpBord();
			List<Richting> mogelijkeRichtingen = new ArrayList<>();
			for (Richting richting : Richting.values()) {
				if (bevatSteen(vrijePlaats[0], vrijePlaats[1], richting))
					mogelijkeRichtingen.add(richting);
			}
			verschuifLegePlaats(mogelijkeRichtingen.get(sr.nextInt(mogelijkeRichtingen.size())));
		}
	}

	private void verschuifLegePlaats(Richting richting) {
		int[] vrijePlaats = geefCoordinatenVanVrijePlaatsOpBord();
		bord[vrijePlaats[0]][vrijePlaats[1]] = geefPuzzelstuk(vrijePlaats[0] + richting.getX(),
				vrijePlaats[1] + richting.getY());
		bord[vrijePlaats[0] + richting.getX()][vrijePlaats[1] + richting.getY()] = null;

	}

	private boolean bevatSteen(int kolom, int rij, Richting richting) {
		int naarKolom = kolom + richting.getX();
		int naarRij = rij + richting.getY();
		return isGeldigeCoordinaat(naarKolom, naarRij) && !isVrij(naarKolom, naarRij);
	}

	private boolean isVerschuivingMogelijk(int kolom, int rij, Richting richting) {
		int naarKolom = kolom + richting.getX();
		int naarRij = rij + richting.getY();
		return isGeldigeCoordinaat(naarKolom, naarRij) && isVrij(naarKolom, naarRij);
	}

	private boolean isGeldigeCoordinaat(int kolom, int rij) {
		return kolom >= 0 && kolom < dimensie && rij >= 0 && rij < dimensie;
	}

	private boolean isVrij(int kolom, int rij) {
		return bord[kolom][rij] == null;
	}

	private int[] geefCoordinatenVanVrijePlaatsOpBord() {
		for (int rij = 0; rij < dimensie; rij++)
			for (int kolom = 0; kolom < dimensie; kolom++)
				if (isVrij(kolom, rij))
					return new int[] { kolom, rij };
		return null;
	}

	private void controleerMoeilijkheidsgraad(Moeilijkheidsgraad moeilijkheidsgraad) {
		if (moeilijkheidsgraad == null)
			throw new IllegalArgumentException("Ongeldige moeilijkheidsgraad");
	}

	private void controleerThema(Thema thema) {
		if (thema == null)
			throw new IllegalArgumentException("Ongeldig thema");
	}

	private void controleerAantalStukken(int aantalStukken) {
		if (aantalStukken != 16 && aantalStukken != 25)
			throw new IllegalArgumentException("Ongeldig aantal puzzelstukken");
	}
}
