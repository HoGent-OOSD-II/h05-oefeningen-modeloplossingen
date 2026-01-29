package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import persistentie.KaartMapper;

public class KaartRepository {
	private KaartMapper kaartMapper;

	public KaartRepository() {
		kaartMapper = new KaartMapper();
	}

	public List<Kaart> geefWillekeurigeKaarten(int aantal) {
		List<Kaart> kaarten = kaartMapper.geefAlleKaarten();
		if (aantal > kaarten.size())
			throw new IllegalArgumentException("Er zijn niet genoeg kaarten");
		List<Kaart> resultaat = new ArrayList<>();
		Collections.shuffle(kaarten);
		for (int i = 0; i < aantal; i++) {
			resultaat.add(kaarten.get(i));
		}
		return resultaat;
	}

}
