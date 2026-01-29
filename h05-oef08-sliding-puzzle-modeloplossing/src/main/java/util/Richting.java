package util;

public enum Richting {
	NAAR_BOVEN(0, -1), NAAR_BENEDEN(0, 1), NAAR_LINKS(-1, 0), NAAR_RECHTS(1, 0);

	private int x, y;

	Richting(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
