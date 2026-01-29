package domein;

public abstract class Rekening implements Beheerskost {
	private final long rekeningNr;
	private double saldo;
	private String houder;

	public Rekening() {
		this(0L, "onbekend");
	}

	public Rekening(long rekeningNr, String houder) {
		controleerRekeningNr(rekeningNr);
		this.rekeningNr = rekeningNr;
		setHouder(houder);
	}

	public abstract String geefCode();

	private void controleerRekeningNr(long rekeningNr) {
		long eerste10 = rekeningNr / 100;
		int rest = (int) (rekeningNr % 100);

		if (!(eerste10 % 97 == rest || (eerste10 % 97 == 0 && rest == 97))) {
			throw new IllegalArgumentException("Rekeningnummer moet correct zijn");
		}
	}

	private void setHouder(String houder) {
		if (houder == null || houder.isBlank()) {
			throw new IllegalArgumentException("Houder mag niet leeg zijn");
		}
		this.houder = houder;
	}

	public long getRekeningNr() {
		return rekeningNr;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getHouder() {
		return houder;
	}

	public void stortOp(double bedrag) {
		if (bedrag <= 0)
			throw new IllegalArgumentException("Je kan geen negatief bedrag storten");
		saldo += bedrag;
	}

	public void haalAf(double bedrag) {
		if (bedrag <= 0)
			throw new IllegalArgumentException("Je kan geen negatief bedrag afhalen");
		saldo -= bedrag;
	}

	public void schrijfBedragOverNaar(double bedrag, Rekening naarRek) {
		if (naarRek == null)
			throw new IllegalArgumentException("Ongeldige rekening om naar over te schrijven");
		haalAf(bedrag);
		try {
			naarRek.stortOp(bedrag);
		} catch (Exception e) {
			stortOp(bedrag);
		}

	}
}
