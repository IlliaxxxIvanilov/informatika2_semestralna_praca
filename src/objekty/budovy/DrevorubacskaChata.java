package objekty.budovy;

import java.util.Random;

/**
 * Budova na produkovanie dreva, zakladna budova na ziskavanie tejto suroviny.
 */
public class DrevorubacskaChata extends BudovaSuroviny {
    /**
     * Vytvori novu drevorubacsku chatu s pevne nastavenymi poziadavkami na suroviny.
     */
    public DrevorubacskaChata() {
        super(250, 600, 300, 20, 1);
    }

    /**
     * Ziska nazov tejto budovy.
     *
     * @return retazec s nazvom budovy
     */
    @Override
    public String getNazov() {
        return "Drevorubacska chata";
    }

    /**
     * Vykona nahodnu udalost ktora moze ovplyvnit produkciu suroviny.
     * S rovnakou pravdepodobnostou zvysi alebo znizi produkciu o 0.1.
     */
    @Override
    public void chyba() {
        Random rand = new Random();
        int r = rand.nextInt(1000);
        if (r % 2 == 0) {
            this.setSurovina(this.getSurovina() + 0.1);
        } else {
            this.setSurovina(this.getSurovina() - 0.1);
        }
    }
}
