package akcie;

import mapa.Hrac;

/**
 * Interface definujuci spravanie pre objekty, ktore mozu pracovat s postavami.
 */
public interface ISpravaniePostav {
    /**
     * Spusti spravu postav.
     */
    void sprav();

    /**
     * Vrati aktualny stav hraca.
     * @return objekt triedy Hrac
     */
    Hrac getHrac();

    /**
     * Nastavi hraca pre danu instanciu.
     * @param hrac objekt triedy Hrac
     */
    void zadajHraca(Hrac hrac);
}