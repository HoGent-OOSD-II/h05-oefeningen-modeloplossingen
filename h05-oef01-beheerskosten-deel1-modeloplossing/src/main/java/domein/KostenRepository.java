package domein;

import java.util.List;

import persistentie.BeheerskostenMapper;

public class KostenRepository {
	private List<Beheerskost> kosten;
	private BeheerskostenMapper bm;

	public KostenRepository() {
		bm = new BeheerskostenMapper();
		kosten = bm.geefBeheerskosten();
	}

	public List<Beheerskost> getKosten() {
		return kosten;
	}

	public Beheerskost geefBeheerskost(int index) {
		return kosten.get(index);
	}
}
