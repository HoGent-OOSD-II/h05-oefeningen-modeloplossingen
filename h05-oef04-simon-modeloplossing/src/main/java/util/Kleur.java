package util;

public enum Kleur {
    GROEN(0, 255, 0), ROOD(255, 0, 0), BLAUW(0, 0, 255), GEEL(255, 255, 0), PAARS(255, 0, 255), CYAAN(0, 255, 255),
    ROZE(255, 192, 203), GRIJS(193, 205, 205), ORANJE(255, 165, 0);

    private final int[] rgb;

    Kleur(int rood, int groen, int blauw) {
        this.rgb = new int[]{rood, groen, blauw};
    }

    public int[] getRgb() {
        return rgb;
    }
}