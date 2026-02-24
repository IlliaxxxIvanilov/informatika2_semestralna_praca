package objekty;

public abstract class Objekt {
    private double cenaZlata;
    private double cenaDreva;
    private double cenaKamena;
    private double cenaZeleza;

    /**
     * Konstruktor pre vytvorenie objektu s cenami surovin
     *
     * @param cenaZlata  cena zlata potrebna na vytvorenie objektu
     * @param cenaDreva  cena dreva potrebna na vytvorenie objektu
     * @param cenaKamena cena kamena potrebna na vytvorenie objektu
     * @param cenaZeleza cena zeleza potrebna na vytvorenie objektu
     */
    public Objekt(double cenaZlata, double cenaDreva, double cenaKamena, double cenaZeleza) {
        this.cenaZlata = cenaZlata;
        this.cenaDreva = cenaDreva;
        this.cenaKamena = cenaKamena;
        this.cenaZeleza = cenaZeleza;
    }

    protected void setCenaZlata(double cenaZlata) {
        this.cenaZlata = cenaZlata;
    }
    protected void setCenaDreva(double cenaDreva) {
        this.cenaDreva = cenaDreva;
    }
    protected void setCenaKamena(double cenaKamena) {
        this.cenaKamena = cenaKamena;
    }
    protected void setCenaZeleza(double cenaZeleza) {
        this.cenaZeleza = cenaZeleza;
    }

    /**
     * Vrati cenu zlata potrebnu na vytvorenie objektu
     *
     * @return cena zlata
     */
    public double getCenaZlata() {
        return this.cenaZlata;
    }

    /**
     * Vrati cenu dreva potrebnu na vytvorenie objektu
     *
     * @return cena dreva
     */
    public double getCenaDreva() {
        return this.cenaDreva;
    }

    /**
     * Vrati cenu kamena potrebnu na vytvorenie objektu
     *
     * @return cena kamena
     */
    public double getCenaKamena() {
        return this.cenaKamena;
    }

    /**
     * Vrati cenu zeleza potrebnu na vytvorenie objektu
     *
     * @return cena zeleza
     */
    public double getCenaZeleza() {
        return this.cenaZeleza;
    }
}
