package objekty.postavy;

import objekty.Objekt;

/**
 * Trieda reprezentujuca postavu v hre
 */
public abstract class Postava extends Objekt {

    private int hp;
    private boolean jeNaZadani;

    /**
     * Vytvori novu postavu so zadanymi parametrami
     *
     * @param cenaZlata  cena zlata potrebna na vytvorenie postavy
     * @param cenaDreva  cena dreva potrebna na vytvorenie postavy
     * @param cenaKamena cena kamena potrebna na vytvorenie postavy
     * @param cenaZeleza cena zeleza potrebna na vytvorenie postavy
     * @param hp         pociatocny pocet zivotov postavy
     */
    public Postava(double cenaZlata, double cenaDreva, double cenaKamena, double cenaZeleza, int hp) {
        super(cenaZlata, cenaDreva, cenaKamena, cenaZeleza);
        this.jeNaZadani = false;
        this.hp = hp;
    }

    /**
     * Vrati textovy popis postavy
     *
     * @return popis postavy
     */
    public abstract String getPopis();

    /**
     * Posle postavu do jaskyne na zadanie
     */
    public void posliDoJaskyne() {
        if (!this.jeNaZadani) {
            this.jeNaZadani = true;
        }
    }

    /**
     * Vrati postavu z jaskyne
     */
    public void vratZJaskyne() {
        if (this.jeNaZadani) {
            this.jeNaZadani = false;
        }
    }

    /**
     * Spusti hladanie v jaskyni
     */
    public abstract void hladaj();

    protected void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Zisti ci je postava na zadani
     *
     * @return true ak je na zadani, false ak nie je
     */
    public boolean jeNaZadani() {
        return this.jeNaZadani;
    }

    /**
     * Vrati pocet zivotov postavy
     *
     * @return aktualne zivoty postavy
     */
    public int getHp() {
        return this.hp;
    }
}
