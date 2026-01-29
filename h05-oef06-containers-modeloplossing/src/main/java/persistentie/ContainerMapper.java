package persistentie;

import java.util.ArrayList;
import java.util.List;

import domein.Container;

public class ContainerMapper {
	private List<Container> containers;

	public ContainerMapper() {
		containers = new ArrayList<>();
		containers.add(new Container("Brugge", 70, 100, 8564));
		containers.add(new Container("Antwerpen", 60, 150, 1234));
		containers.add(new Container("Rotterdam", 70, 110, 2568));
		containers.add(new Container("Calais", 80, 90, 8569));
	}

	public List<Container> getContainers() {
		return containers;
	}

	// Simulate write to DB
	public void voegContainerToe(Container c) {
		containers.add(c);
	}

}
