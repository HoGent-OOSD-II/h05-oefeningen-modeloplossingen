package domein;

import java.util.ArrayList;
import java.util.Collection;

import dto.ContainerDTO;

public class DomeinController {

	private final ContainerRepository containerRepo;

	public DomeinController() {
		containerRepo = new ContainerRepository();
	}

	public Collection<ContainerDTO> geefAlleContainers() {
		return zetContainersOmNaatContainerDTOs(containerRepo.getContainers());
	}

	public Collection<ContainerDTO> geefAlleContainersGesorteerdOpSerienummer() {
		return zetContainersOmNaatContainerDTOs(containerRepo.geefAlleContainersGesorteerdOpSerienummer());
	}

	public Collection<ContainerDTO> geefAlleContainersGesorteerdOpMassa() {
		return zetContainersOmNaatContainerDTOs(containerRepo.geefAlleContainersGesorteerdOpMassa());
	}

	public Collection<ContainerDTO> geefAlleContainersGesorteerdOpEigenaar() {
		return zetContainersOmNaatContainerDTOs(containerRepo.geefAlleContainersGesorteerdOpEigenaar());
	}

	public Collection<ContainerDTO> geefAlleContainersGesorteerdOpVolumeEnEigenaar() {
		return zetContainersOmNaatContainerDTOs(containerRepo.geefAlleContainersGesorteerdOpVolumeEnEigenaar());
	}

	public void voegContainerToe(ContainerDTO containerDTO) {
		containerRepo.voegContainerToe(new Container(containerDTO.eigenaar(), containerDTO.volume(),
				containerDTO.massa(), containerDTO.serienummer()));
	}

	private Collection<ContainerDTO> zetContainersOmNaatContainerDTOs(Collection<Container> containers) {
		Collection<ContainerDTO> containerDTOs = new ArrayList<>();
		for (Container c : containers) {
			containerDTOs.add(new ContainerDTO(c));
		}
		return containerDTOs;
	}

}
