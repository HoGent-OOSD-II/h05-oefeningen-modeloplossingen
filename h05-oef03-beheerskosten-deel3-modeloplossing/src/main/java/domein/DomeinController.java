package domein;

import java.util.ArrayList;
import java.util.List;

import dto.BeheerskostDTO;

public class DomeinController {
	private final KostenRepository kostenRepo;
	private Beheerskost geselecteerdeBeheerskostVan;
	private Beheerskost geselecteerdeBeheerskostNaar;

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

	public void selecteerRekeningVan(int index) {
		this.geselecteerdeBeheerskostVan = kostenRepo.geefRekening(index);
	}

	public void selecteerRekeningNaar(int index) {
		this.geselecteerdeBeheerskostNaar = kostenRepo.geefRekening(index);
	}

	public BeheerskostDTO geefGeslecteerdeBeheerskostVan() {
		return geselecteerdeBeheerskostVan == null ? null
				: BeheerskostDTO.maakBeheerskostDTO(geselecteerdeBeheerskostVan);
	}

	public BeheerskostDTO geefGeslecteerdeBeheerskostNaar() {
		return geselecteerdeBeheerskostNaar == null ? null
				: BeheerskostDTO.maakBeheerskostDTO(geselecteerdeBeheerskostNaar);
	}

	public void schrijfOver(double bedrag) {
		if (geselecteerdeBeheerskostVan == null || geselecteerdeBeheerskostNaar == null
				|| geselecteerdeBeheerskostVan == geselecteerdeBeheerskostNaar
				|| geselecteerdeBeheerskostVan instanceof SpaarRekening
				|| geselecteerdeBeheerskostNaar instanceof SpaarRekening)
			throw new IllegalArgumentException("Kies twee verschillende zichtrekeningen!!");
		try {
			ZichtRekening zr1 = (ZichtRekening) geselecteerdeBeheerskostVan;
			ZichtRekening zr2 = (ZichtRekening) geselecteerdeBeheerskostNaar;
			zr1.schrijfBedragOverNaar(bedrag, zr2);
			geselecteerdeBeheerskostVan = null;
			geselecteerdeBeheerskostNaar = null;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			throw new IllegalArgumentException("Op deze beheerskost kan je geen geld storten!");
		}

	}

}