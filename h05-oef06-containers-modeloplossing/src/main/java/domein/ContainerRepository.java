package domein;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import persistentie.ContainerMapper;

public class ContainerRepository {
	private ContainerMapper mapper;
	private List<Container> containers;

	public ContainerRepository() {
		mapper = new ContainerMapper();
		containers = mapper.getContainers();
	}

	public Collection<Container> getContainers() {
		return containers;
	}

	public Collection<Container> geefAlleContainersGesorteerdOpSerienummer() {
		Collections.sort(containers);
		return containers;
	}

	// Anonieme klasse
	public Collection<Container> geefAlleContainersGesorteerdOpMassa() {
		Collections.sort(containers, new Comparator<Container>() {
			@Override
			public int compare(Container c1, Container c2) {
				return c1.getMassa() - c2.getMassa();
			}
		});
		return containers;
	}

	// Lambda expressie
	public Collection<Container> geefAlleContainersGesorteerdOpEigenaar() {
		Collections.sort(containers, (c1, c2) -> c1.getEigenaar().compareTo(c2.getEigenaar()));
		return containers;
	}

	// Static Comparator methods: comparing & thenComparing
	public Collection<Container> geefAlleContainersGesorteerdOpVolumeEnEigenaar() {
		Collections.sort(containers, Comparator.comparing(Container::getVolume).thenComparing(Container::getEigenaar));
		return containers;
	}

	// Example method contains: checks if the collection contains a container that
	// 'equals' the given container c
	public void voegContainerToe(Container c) {
		if (containers.contains(c))
			throw new IllegalArgumentException(
					String.format("Container met serienummer %d bestaat reeds", c.getSerienummer()));
		mapper.voegContainerToe(c);
		containers = mapper.getContainers();
	}

}
