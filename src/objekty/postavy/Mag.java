package objekty.postavy;

import java.util.Random;

/**
 * Reprezentuje postavu maga v hre, ktora ma specialne vlastnosti
 */
public class Mag extends Postava {
    /**
     * Vytvori novu instanciu maga so zakladnymi statistikami
     */
    public Mag() {
        super(20, 18, 5, 3, 50);
    }

    /**
     * Vrati textovy popis postavy maga
     *
     * @return nazov postavy
     */
    @Override
    public String getPopis() {
        return "Mag";
    }

    /**
     * Spracuje akciu hladania pre maga
     * Ma sancu na zranenie alebo liecenie
     */
    @Override
    public void hladaj() {
        Random random = new Random();
        if (random.nextInt(100) < 10) {
            this.setHp(this.getHp() - 1);
        }
        if (random.nextInt(100) > 90) {
            this.setHp(this.getHp() + 1);
        }
    }
}
