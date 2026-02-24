package objekty.budovy;

import objekty.Objekt;

/**
 * Abstraktna trieda predstavujuca zakladnu budovu v hre.
 * Rozsiruje triedu Objekt a definuje zakladne vlastnosti budov.
 */
public abstract class Budova extends Objekt {
    /**
     * Vytvori novu budovu so zadanymi cenami surovin.
     * @param cenaZlata cena budovy v zlate
     * @param cenaDreva cena budovy v dreve 
     * @param cenaKamena cena budovy v kameni
     * @param cenaZeleza cena budovy v zeleze
     */
    public Budova(double cenaZlata, double cenaDreva, double cenaKamena, double cenaZeleza) {
        super(cenaZlata, cenaDreva, cenaKamena, cenaZeleza);
    }

    /**
     * Vrati nazov budovy.
     * @return nazov budovy ako retazec
     */
    public abstract String getNazov();
}