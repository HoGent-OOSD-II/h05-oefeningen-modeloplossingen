package dto;

import domein.Container;

public record ContainerDTO(String eigenaar, int massa, int volume, int serienummer) {

	public ContainerDTO(Container container) {
		this(container.getEigenaar(), container.getMassa(), container.getVolume(), container.getSerienummer());
	}

	public ContainerDTO(String eigenaar, int massa, int volume, int serienummer) {
		this.serienummer = serienummer;
		this.volume = volume;
		this.eigenaar = eigenaar;
		this.massa = massa;
	}
}
