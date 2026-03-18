package domein;

import util.Kleur;

import java.util.*;

public class Simon {
    public static final int AANTAL_KLEUREN = 5;
    private static final Random random = new Random();
    private final Kleur[] spelkleuren;
    private final List<Kleur> kleurenreeks;
    private int aantalGoedGeraden;
    private boolean kleurGoedGeraden;

    public Simon(Kleur[] spelkleuren) {
        List<Kleur> kleuren = Arrays.asList(spelkleuren);
        if (kleuren.contains(null) || new HashSet<>(kleuren).size() != AANTAL_KLEUREN)
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
        return kleurenreeks.getLast();
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