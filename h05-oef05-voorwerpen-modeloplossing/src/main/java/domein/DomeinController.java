package domein;

import java.util.ArrayList;
import java.util.List;

import dto.DraagbaarDTO;

public class DomeinController {

	private final DraagbaarRepository draagbaarRepo;

	public DomeinController() {
		draagbaarRepo = new DraagbaarRepository();
	}

	public void voegWapenToe(String naam, double gewicht, int niveau, int kracht, boolean gebruikt) {
		Wapen wapen = new Wapen(naam, gewicht, niveau, kracht, gebruikt);
		draagbaarRepo.voegDraagbaarItemToe(wapen);
	}

	public void voegSleutelToe(String naam, double gewicht, int niveau, int deur) {
		Sleutel sleutel = new Sleutel(naam, gewicht, niveau, deur);
		draagbaarRepo.voegDraagbaarItemToe(sleutel);
	}

	public void voegGebouwToe(String naam, double hoogte) {
		Gebouw gebouw = new Gebouw(naam, hoogte);
		draagbaarRepo.voegDraagbaarItemToe(gebouw);
	}

	public List<DraagbaarDTO> geefOverzicht() {
		List<Draagbaar> draagbaren = draagbaarRepo.getDraagbaren();
		List<DraagbaarDTO> draagbaarDTOs = new ArrayList<>();
		for (Draagbaar item : draagbaren) {
			draagbaarDTOs.add(DraagbaarDTO.maakdraagbaarDTO(item));
		}
		return draagbaarDTOs;
	}
}
