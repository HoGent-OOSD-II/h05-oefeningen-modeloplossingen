package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import util.Kleur;

public class Simon {
	public static final int AANTAL_KLEUREN = 6;
	private static final Random random = new Random();
	private final Kleur[] spelkleuren;
	private final List<Kleur> kleurenreeks;
	private int aantalGoedGeraden;
	private boolean kleurGoedGeraden;

	public Simon(Kleur[] spelkleuren) {
		if (new HashSet<Kleur>(Arrays.asList(spelkleuren)).size() != AANTAL_KLEUREN)
			throw new IllegalArgumentException(
					String.format("Je moet %d verschillende kleuren kiezen.", AANTAL_KLEUREN));
		this.spelkleuren = spelkleuren;
		this.kleurenreeks = new ArrayList<>();
	}

	public void voegNieuweKleurAanKleurenreeksToe() {
		aantalGoedGeraden = 0;
		kleurGoedGeraden = false;
		kleurenreeks.add(geefWillekeurigeKleur());
	}

	public void verwerkGekozenKleur(Kleur kleur) {
		if (kleurenreeks.get(aantalGoedGeraden).equals(kleur)) {
			aantalGoedGeraden++;
			kleurGoedGeraden = true;
		} else {
			kleurGoedGeraden = false;
		}
	}

	public boolean isKleurGoedGeraden() {
		return kleurGoedGeraden;
	}

	public Kleur geefNieuweKleurUitKleurenreeks() {
		return kleurenreeks.get(kleurenreeks.size() - 1);
	}

	public Kleur geefGemisteKleur() {
		return kleurenreeks.get(aantalGoedGeraden);
	}

	public boolean isReeksVolledigGeraden() {
		return aantalGoedGeraden == kleurenreeks.size();
	}

	public int getAantalGoedGeraden() {
		return aantalGoedGeraden;
	}

	public Kleur geefWillekeurigeKleur() {
		return spelkleuren[random.nextInt(spelkleuren.length)];
	}

	public List<Kleur> getKleurenReeks() {
		return kleurenreeks;
	}

	public Kleur[] getSpelKleuren() {
		return spelkleuren;
	}
}
