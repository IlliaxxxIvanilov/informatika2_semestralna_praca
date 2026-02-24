package objekty.budovy;

import akcie.IZlepsovanie;

/**
 * Abstraktna trieda predstavujuca budovu, ktora produkuje suroviny.
 * Implementuje rozhranie IZlepsovanie pre moznost vylepsenia budovy.
 */
public abstract class BudovaSuroviny extends Budova implements IZlepsovanie {

    private double surovina;
    private int uroven;

    /**
     * Vytvori novu budovu produkujucu suroviny so zadanymi parametrami.
     * @param cenaZlata cena budovy v zlate
     * @param cenaDreva cena budovy v dreve
     * @param cenaKamena cena budovy v kameni
     * @param cenaZeleza cena budovy v zeleze
     * @param surovina zakladna produkcia suroviny za jeden tik
     */
    public BudovaSuroviny(double cenaZlata, double cenaDreva, double cenaKamena, double cenaZeleza, double surovina) {
        super(cenaZlata, cenaDreva, cenaKamena, cenaZeleza);
        this.surovina = surovina;
        this.uroven = 1;
    }

    /**
     * Vylepsuje budovu na dalsiu uroven.
     * Zvysuje ceny surovin o 10% a produkciu o 15%.
     */
    @Override
    public void zlepsi() {
        this.setCenaZeleza(this.getCenaZeleza() * 1.1);
        this.setCenaDreva(this.getCenaDreva() * 1.1);
        this.setCenaKamena(this.getCenaKamena() * 1.1);
        this.setCenaZlata(this.getCenaZlata() * 1.1);
        this.surovina *= 1.15;
        this.uroven++;
    }

    /**
     * Nastavi novu hodnotu produkcie suroviny.
     * @param surovina nova hodnota produkcie
     */
    protected void setSurovina(double surovina) {
        this.surovina = surovina;
    }

    /**
     * Vrati aktualnu uroven budovy.
     * @return cislo urovne
     */
    @Override
    public int getUroven() {
        return this.uroven;
    }

    /**
     * Vrati textovy popis urovne budovy.
     * @return retazec s urovnou budovy
     */
    @Override
    public String getPopis() {
        return "\nUroven: " + this.uroven;
    }

    /**
     * Abstraktna metoda pre spracovanie chyb specifickych pre dany typ budovy.
     */
    @Override
    public abstract void chyba();

    /**
     * Vrati aktualne mnozstvo produkovanej suroviny za tik.
     * @return hodnota produkcie
     */
    public double getSurovina() {
        return this.surovina;
    }
}