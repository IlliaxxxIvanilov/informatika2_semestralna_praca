package objekty.budovy;

/**
 * Reprezentuje hlavnu budovu mesta, ktora sluzi ako centrum spravy.
 */
public class TownHall extends Budova {
    /**
     * Vytvori novu instanciu hlavnej budovy mesta.
     */
    public TownHall() {
        super(0, 0, 0, 0);
    }

    /**
     * Vrati nazov budovy.
     *
     * @return retazec obsahujuci nazov "TownHall"
     */
    @Override
    public String getNazov() {
        return "TownHall";
    }
}
