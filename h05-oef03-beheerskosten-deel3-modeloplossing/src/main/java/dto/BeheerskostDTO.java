package dto;

import domein.Beheerskost;
import domein.Kluis;
import domein.SpaarRekening;
import domein.ZichtRekening;

public record BeheerskostDTO(char soort, String houder, long rekeningnr, double saldo, double maxKredietOnderNul,
		int kluisnr, double jaarlijkseKost) {

	public static BeheerskostDTO maakBeheerskostDTO(Beheerskost bk) {
		if (bk instanceof Kluis k)
			return new BeheerskostDTO('K', k.getHouder(), 0, 0, 0, k.getKluisnummer(), k.geefJaarlijkseKost());
		if (bk instanceof ZichtRekening zr)
			return new BeheerskostDTO('Z', zr.getHouder(), zr.getRekeningNr(), zr.getSaldo(),
					zr.getMaxKredietOnderNul(), 0, zr.geefJaarlijkseKost());
		if (bk instanceof SpaarRekening sr)
			return new BeheerskostDTO('S', sr.getHouder(), sr.getRekeningNr(), sr.getSaldo(), 0, 0,
					sr.geefJaarlijkseKost());
		throw new IllegalArgumentException("Ongeldige beheerskost");
	}
}