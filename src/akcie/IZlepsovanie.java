package akcie;

/**
 * Interface definujuci metody pre vylepsovanie budov v hre.
 */
public interface IZlepsovanie {
    /**
     * Vylepsenie objektu na dalsiu uroven.
     */
    void zlepsi();

    /**
     * Vrati aktualnu uroven objektu.
     * @return cislo urovne
     */
    int getUroven();

    /**
     * Vrati textovy popis objektu.
     * @return popis objektu
     */
    String getPopis();

    /**
     * Spracuje moznu chybu/nehodu objektu.
     */
    void chyba();
}