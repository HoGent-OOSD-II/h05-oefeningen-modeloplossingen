package persistentie;

import java.util.ArrayList;
import java.util.List;

import domein.Beheerskost;
import domein.Kluis;
import domein.SpaarRekening;
import domein.ZichtRekening;

public class BeheerskostenMapper {
	public List<Beheerskost> geefBeheerskosten() {
		List<Beheerskost> lijst = new ArrayList<>();

		SpaarRekening s1, s2;
		ZichtRekening z1, z2;
		Kluis k1, k2;

		SpaarRekening.setAangroeiIntrest(2);

		s1 = new SpaarRekening(101L, "Kim");
		s2 = new SpaarRekening(202L, "Mo");
		z1 = new ZichtRekening(303L, "Stef", -1000);
		z2 = new ZichtRekening(404L, "Charlotte", -2000);
		k1 = new Kluis("Dylan", 58);
		k2 = new Kluis("Sandra", 59);

		s1.stortOp(100);
		s2.stortOp(200);
		z1.stortOp(300);
		z2.stortOp(400);

		lijst.add(z2);
		lijst.add(k2);
		lijst.add(z1);
		lijst.add(s2);
		lijst.add(s1);
		lijst.add(k1);

		return lijst;
	}
}