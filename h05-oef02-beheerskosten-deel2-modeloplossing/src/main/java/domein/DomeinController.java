package domein;

import java.util.ArrayList;
import java.util.List;

import dto.BeheerskostDTO;

public class DomeinController {
	private final KostenRepository kostenRepo;
	private Beheerskost geselecteerdeBeheerskost;

	public DomeinController() {
		kostenRepo = new KostenRepository();
	}

	public List<BeheerskostDTO> geefAlleBeheerskosten() {
		List<BeheerskostDTO> alleDTOs = new ArrayList<>();
		for (Beheerskost bk : kostenRepo.getKosten()) {
			alleDTOs.add(BeheerskostDTO.maakBeheerskostDTO(bk));
		}
		return alleDTOs;
	}

	public List<BeheerskostDTO> geefAlleRekeningen() {
		List<BeheerskostDTO> alleDTOs = new ArrayList<>();
		for (Rekening rk : kostenRepo.geefRekeningen()) {
			alleDTOs.add(BeheerskostDTO.maakBeheerskostDTO(rk));
		}
		return alleDTOs;
	}

	public double geefAangroeiIntrestSpaarRekening() {
		return SpaarRekening.getAangroeiIntrest();
	}

	public void selecteerRekening(int index) {
		this.geselecteerdeBeheerskost = kostenRepo.geefRekening(index);
	}

	public BeheerskostDTO geefGeslecteerdeBeheerskost() {
		return geselecteerdeBeheerskost == null ? null : BeheerskostDTO.maakBeheerskostDTO(geselecteerdeBeheerskost);
	}

	public void stortOpGeselecteerdeRekening(double bedrag) {
		if (geselecteerdeBeheerskost == null)
			throw new IllegalArgumentException("Geen rekening geselecteerd!");
		if (geselecteerdeBeheerskost instanceof ZichtRekening zr)
			zr.stortOp(bedrag);
		else if (geselecteerdeBeheerskost instanceof SpaarRekening sr)
			sr.stortOp(bedrag);
		else
			throw new IllegalArgumentException("Op deze beheerskost kan je geen geld storten!");
	}

	public void haalAfVanGeselecteerdeRekening(double bedrag) {
		if (geselecteerdeBeheerskost == null)
			throw new IllegalArgumentException("Geen rekening geselecteerd!");
		if (geselecteerdeBeheerskost instanceof ZichtRekening zr)
			zr.haalAf(bedrag);
		else if (geselecteerdeBeheerskost instanceof SpaarRekening sr)
			sr.haalAf(bedrag);
		else
			throw new IllegalArgumentException("Van deze beheerskost kan geen geld afgehaald worden");
	}
}