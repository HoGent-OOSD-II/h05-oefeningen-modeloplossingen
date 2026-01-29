package domein;

import java.util.ArrayList;
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

	public List<Rekening> geefRekeningen() {
		List<Rekening> rekeningen = new ArrayList<>();
		for (Beheerskost bh : kosten) {
			if (bh instanceof Rekening r)
				rekeningen.add(r);
		}
		return rekeningen;
	}

	public Rekening geefRekening(int index) {
		return geefRekeningen().get(index);
	}
}
