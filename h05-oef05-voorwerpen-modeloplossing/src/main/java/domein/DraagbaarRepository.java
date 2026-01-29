package domein;

import java.util.ArrayList;
import java.util.List;

public class DraagbaarRepository {

	private final List<Draagbaar> draagbaren;

	public DraagbaarRepository() {
		draagbaren = new ArrayList<>();
		draagbaren.add(new Wapen("Colt", 1.5, 3, 6, false));
		draagbaren.add(new Sleutel("Voordeur", 0.5, 3, 1));
		draagbaren.add(new Wapen("Brown", 0.5, 1, 23, true));
		draagbaren.add(new Sleutel("Achterdeur", 0.5, 1, 2));
		draagbaren.add(new Gebouw("Residentie Frankenstein", 4.5));
	}

	public void voegDraagbaarItemToe(Draagbaar item) {
		draagbaren.add(item);
	}

	public List<Draagbaar> getDraagbaren() {
		return draagbaren;
	}

}
