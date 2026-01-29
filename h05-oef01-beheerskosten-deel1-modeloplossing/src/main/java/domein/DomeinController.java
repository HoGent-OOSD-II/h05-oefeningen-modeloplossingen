package domein;

import java.util.ArrayList;
import java.util.List;

import dto.BeheerskostDTO;

public class DomeinController {
	private final KostenRepository kostenRepo;

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

	public double geefAangroeiIntrestSpaarRekening() {
		return SpaarRekening.getAangroeiIntrest();
	}
}