package persistentie;

import java.util.ArrayList;
import java.util.List;

import domein.Kaart;

public class KaartMapper {

	public List<Kaart> geefAlleKaarten() {
		List<Kaart> kaarten = new ArrayList<>();
		for (int i = 1; i <= 18; i++) {
			kaarten.add(new Kaart(String.format("card%02d.jpeg", i)));
		}
		return kaarten;
	}
}
