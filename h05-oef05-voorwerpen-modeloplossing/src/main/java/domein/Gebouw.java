package domein;

public class Gebouw implements Draagbaar {

	private final String naam;
	private double hoogte;

	public Gebouw(String naam, double hoogte) {
		super();
		controleerNaam(naam);
		this.naam = naam;
		this.setHoogte(hoogte);
	}

	private void controleerNaam(String naam) {
		if (naam == null || naam.equals(""))
			throw new IllegalArgumentException("naam moet ingevuld worden!");
	}

	public String getNaam() {
		return naam;
	}

	public double getHoogte() {
		return hoogte;
	}

	public final void setHoogte(double hoogte) {
		if (hoogte < 3)
			throw new IllegalArgumentException("hoogte moet minstens 3 meter zijn!");
		this.hoogte = hoogte;
	}

	@Override
	public boolean isDraagbaar() {
		return false;
	}

	@Override
	public String toString() {
		return String.format("Gebouw \"%s\" met hoogte %.1f meter, is %s.", naam, hoogte,
				isDraagbaar() ? "draagbaar" : "niet draagbaar");
	}

}
