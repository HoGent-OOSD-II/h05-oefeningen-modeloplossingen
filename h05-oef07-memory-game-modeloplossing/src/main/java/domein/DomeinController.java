package domein;

import java.util.ArrayList;
import java.util.List;

import dto.KaartDTO;

public class DomeinController {
	private KaartRepository kaartRepository;
	private MemoryGame game;

	public DomeinController() {
		this.kaartRepository = new KaartRepository();
		startNieuwSpel();
	}

	public void startNieuwSpel() {
		this.game = new MemoryGame(kaartRepository.geefWillekeurigeKaarten(MemoryGame.AANTAL_KAARTEN / 2));
	}

	public void verwerkOmgedraaideKaarten() {
		game.verwerkOmgedraaideKaarten();
	}

	public boolean isTafelLeeg() {
		return game.isTafelLeeg();
	}

	public boolean zijnAlleOmgedraaideKaartenVerwerkt() {
		return game.zijnAlleOmgedraaideKaartenverwerkt();
	}

	public KaartDTO geefKaart(int kolom, int rij) {
		Kaart kaart = game.geefKaart(kolom, rij);
		return new KaartDTO(kaart);
	}

	public void draaiKaart(KaartDTO kaart) {
		game.draaiKaartOm(kaart.posX(), kaart.posY());
	}

	public List<KaartDTO> geefKaartenOpTafel() {
		List<KaartDTO> kaarten = new ArrayList<>();
		for (Kaart kaart : game.getNogTeRadenKaarten()) {
			kaarten.add(new KaartDTO(kaart));
		}
		return kaarten;
	}

	public List<KaartDTO> geefGeradenKaarten() {
		List<KaartDTO> kaarten = new ArrayList<>();
		for (Kaart kaart : game.getGeradenKaarten()) {
			kaarten.add(new KaartDTO(kaart));
		}
		return kaarten;
	}

	public List<KaartDTO> geefOmgedraaideKaarten() {
		List<KaartDTO> kaarten = new ArrayList<>();
		for (Kaart kaart : game.getOmgedraaideKaarten()) {
			kaarten.add(new KaartDTO(kaart));
		}
		return kaarten;
	}

}
