package domein;

public class SpaarRekening extends Rekening {
	// extra attribuut
	private static double aangroeiIntrest;

	public SpaarRekening(long rekeningNr, String houder) {
		super(rekeningNr, houder);
	}

	@Override
	public String geefCode() {
		return String.format("SR-%d-%.2f", getRekeningNr(), aangroeiIntrest);
	}

	// getter en setter voor attribuut
	public static double getAangroeiIntrest() {
		return aangroeiIntrest;
	}

	public final static void setAangroeiIntrest(double aangroeiIntrest) {
		if (aangroeiIntrest < 0)
			throw new IllegalArgumentException("Intrest mag niet negatief zijn!");
		SpaarRekening.aangroeiIntrest = aangroeiIntrest;
	}

	// extra (overriden) methodes
	@Override
	public void haalAf(double bedrag) {
		double saldo = /* super. */getSaldo();
		if (bedrag <= saldo)
			super.haalAf(bedrag);
		else
			throw new IllegalArgumentException("Saldo is ontoereikend");
	}

	@Override
	public double geefJaarlijkseKost() {
		double saldo = getSaldo();
		return saldo >= 300 ? 0 : (300 - saldo) * 5 / 100;
	}
}
