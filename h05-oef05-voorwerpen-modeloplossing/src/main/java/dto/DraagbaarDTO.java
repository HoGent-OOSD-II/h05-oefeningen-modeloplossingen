package dto;

import domein.Draagbaar;
import domein.Gebouw;
import domein.Sleutel;
import domein.Wapen;

public record DraagbaarDTO(char soort, boolean draagbaar, String naam, double gewicht, int niveau, int kracht,
		boolean gebruikt, int deur, int aantalInOmloop, double hoogte) {

	public static DraagbaarDTO maakdraagbaarDTO(Draagbaar d) {
		if (d instanceof Wapen w)
			return new DraagbaarDTO('W', w.isDraagbaar(), w.getNaam(), w.getGewicht(), w.getNiveau(), w.getKracht(),
					w.isGebruikt(), 0, 0, 0);
		if (d instanceof Sleutel s)
			return new DraagbaarDTO('S', s.isDraagbaar(), s.getNaam(), s.getGewicht(), s.getNiveau(), 0, false,
					s.getDeur(), Sleutel.getAantalInOmloop(), 0);
		if (d instanceof Gebouw g)
			return new DraagbaarDTO('G', g.isDraagbaar(), g.getNaam(), 0, 0, 0, false, 0, 0, g.getHoogte());
		throw new IllegalArgumentException("Ongeldig draagbaar item");
	}

}