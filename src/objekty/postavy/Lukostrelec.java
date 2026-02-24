package objekty.postavy;

import java.util.Random;

/**
 * Trieda reprezentuje bojovnika specializovaneho na boj na dialku
 */
public class Lukostrelec extends Postava {
    /**
     * Vytvori noveho lukostrelca so zakladnymi atributmi
     */
    public Lukostrelec() {
        super(50, 10, 0, 3, 40);
    }

    /**
     * Vrati textovy identifikator postavy
     *
     * @return nazov typu postavy
     */
    @Override
    public String getPopis() {
        return "Lukostrelec";
    }

    /**
     * Vykonava hladanie v okoli s 2% sancou na zranenie
     */
    @Override
    public void hladaj() {
        Random random = new Random();
        if (random.nextInt(100) < 2) {
            this.setHp(this.getHp() - 1);
        }
    }
}
