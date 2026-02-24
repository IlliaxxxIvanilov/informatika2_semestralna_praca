package objekty.budovy;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Reprezentuje budovu bane v hre.
 * Bana produkuje zelezo a ma obmedzenu kapacitu tazby.
 */
public class Bana extends BudovaSuroviny {

    private int kapacitaBane;
    private double pocet;

    /**
     * Vytvori novu banu s nahodnymi pociatocnymi zasobami.
     */
    public Bana() {
        super(500, 400, 900, 200, 0.5);
        Random rand = new Random();
        this.kapacitaBane = rand.nextInt(3000) + 2000;
        this.pocet = 0;
    }

    /**
     * Vrati nazov budovy.
     * @return retazec "Bana"
     */
    @Override
    public String getNazov() {
        return "Bana";
    }

    /**
     * Kontroluje stav bane a jej kapacitu.
     * Ak sa vycerpa kapacita bane, zastavi produkciu.
     */
    @Override
    public void chyba() {
        this.pocet += this.getSurovina();
        if (this.pocet >= this.kapacitaBane) {
            this.setSurovina(0);
            JOptionPane.showMessageDialog(null, "V tejto miestnosti nezostalo zelezo");
        }
    }
}