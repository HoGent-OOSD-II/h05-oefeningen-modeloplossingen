package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGame {
	public static final int AANTAL_RIJEN = 4;
	public static final int AANTAL_KOLOMMEN = 6;
	public static final int AANTAL_KAARTEN = AANTAL_RIJEN * AANTAL_KOLOMMEN;
	private List<Kaart> nogTeRadenKaarten;
	private List<Kaart> omgedraaideKaarten;
	private List<Kaart> geradenKaarten;

	public MemoryGame(List<Kaart> kaarten) {
		nogTeRadenKaarten = kaarten;
		omgedraaideKaarten = new ArrayList<>();
		geradenKaarten = new ArrayList<>();
		maakVanElkeNogTeRadenKaartEenDubbel();
		legNogTeRadenKaartenWillekeurigOpTafel();
	}

	private void maakVanElkeNogTeRadenKaartEenDubbel() {
		List<Kaart> dubbels = new ArrayList<>();
		for (Kaart kaart : nogTeRadenKaarten) {
			dubbels.add(kaart.clone());
		}
		nogTeRadenKaarten.addAll(dubbels);
	}

	private void legNogTeRadenKaartenWillekeurigOpTafel() {
		Collections.shuffle(nogTeRadenKaarten);
		int kaartNr = 0;
		for (int rij = 0; rij < AANTAL_RIJEN; rij++)
			for (int kolom = 0; kolom < AANTAL_KOLOMMEN; kolom++)
				nogTeRadenKaarten.get(kaartNr++).kenPlaatsToe(kolom, rij);
	}

	public void draaiKaartOm(int kolom, int rij) {
		Kaart kaart = geefKaart(kolom, rij);
		if (kaart != null && !omgedraaideKaarten.contains(kaart) && !(omgedraaideKaarten.size() == 2)) {
			kaart.draaiOm();
			omgedraaideKaarten.add(kaart);
		}
	}

	public void verwerkOmgedraaideKaarten() {
		if (omgedraaideKaarten.size() == 2) {
			if (omgedraaideKaarten.get(0).heeftZelfdeAfbeeldingAls(omgedraaideKaarten.get(1)))
				neemOmgedraaidekaartenVanTafel();
			else
				draaiOmgedraaideKaartenTerugOm();
			omgedraaideKaarten.clear();
		}
	}

	private void draaiOmgedraaideKaartenTerugOm() {
		for (Kaart k : omgedraaideKaarten) {
			k.draaiOm();
		}
	}

	private void neemOmgedraaidekaartenVanTafel() {
		boolean toegevoegd = false;
		for (Kaart k : omgedraaideKaarten) {
			if (!toegevoegd) {
				geradenKaarten.add(k);
				toegevoegd = true;
			}
			nogTeRadenKaarten.remove(k);
		}
	}

	public Kaart geefKaart(int kolom, int rij) {
		for (Kaart kaart : nogTeRadenKaarten) {
			if (kaart.getKolom() == kolom && kaart.getRij() == rij) {
				return kaart;
			}
		}
		throw new IllegalArgumentException(String.format("Geen kaart op rij %d, kolom %d", rij, kolom));
	}

	public List<Kaart> getNogTeRadenKaarten() {
		return nogTeRadenKaarten;
	}

	public List<Kaart> getOmgedraaideKaarten() {
		return omgedraaideKaarten;
	}

	public List<Kaart> getGeradenKaarten() {
		return geradenKaarten;
	}

	public boolean isTafelLeeg() {
		return nogTeRadenKaarten.isEmpty();
	}

	public boolean zijnAlleOmgedraaideKaartenverwerkt() {
		return omgedraaideKaarten.size() != 2;
	}
}
