package objekty.postavy;

import java.util.Random;

/**
 * Trieda reprezentujuca rytiera ako bojovu postavu v hre.
 * Rytier je specializovany bojovnik s vysokymi zivotmi a dobrou pohyblivostou.
 */
public class Rytier extends Postava {
    /**
     * Vytvori novu instanciu rytiera s prednastavenymi hodnotami.
     * Rytier zacina so 60 HP, utokom 3, obranou 0, pohybom 6 a maximalnym poctom HP 100.
     */
    public Rytier() {
        super(60, 3, 0, 6, 100);
    }

    /**
     * Vrati nazov typu postavy.
     *
     * @return retazec obsahujuci "Rytier"
     */
    @Override
    public String getPopis() {
        return "Rytier";
    }

    /**
     * Vykonava akciu hladania pre rytiera.
     * Pri hladani ma rytier 1% sancu na stratu 3 HP a 5% sancu na stratu 1 HP.
     */
    @Override
    public void hladaj() {
        Random random = new Random();
        if (random.nextInt(100) < 1) {
            this.setHp(this.getHp() - 3);
        }
        if (random.nextInt(100) > 95) {
            this.setHp(this.getHp() - 1);
        }
    }
}
