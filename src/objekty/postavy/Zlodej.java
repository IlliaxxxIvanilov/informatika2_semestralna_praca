package objekty.postavy;

import java.util.Random;

/**
 * Reprezentuje postavu zlodeja v hre
 */
public class Zlodej extends Postava {
    /**
     * Vytvori novu instanciu zlodeja so zakladnymi vlastnostami
     */
    public Zlodej() {
        super(50, 6, 10, 9, 60);
    }

    /**
     * Vrati textovy popis postavy
     *
     * @return nazov postavy
     */
    @Override
    public String getPopis() {
        return "Zlodej";
    }

    /**
     * Spracuje hladanie postavy a pripadne poskodenie
     */
    @Override
    public void hladaj() {
        Random random = new Random();
        if (random.nextInt(100) == 13) {
            this.setHp(this.getHp() - 1);
        }
        if (random.nextInt(100) == 0) {
            this.setHp(this.getHp() - 2);
        }
    }
}
