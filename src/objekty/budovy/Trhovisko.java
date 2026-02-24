package objekty.budovy;

import javax.swing.JOptionPane;

/**
 * Predstavuje budovu trhoviska v hre
 * Sluzi na obchodovanie so surovinami a udrzuje ekonomicku stabilitu
 */
public class Trhovisko extends BudovaSuroviny {

    private double pocet;

    /**
     * Vytvori novu instanciu trhoviska
     * Inicializuje zakladne hodnoty pre obchodovanie so surovinami
     */
    public Trhovisko() {
        super(700, 1000, 800, 120, 1.5);
        this.pocet = 0;
    }

    /**
     * Ziska nazov tejto budovy
     *
     * @return nazov budovy ako retazec znakov
     */
    @Override
    public String getNazov() {
        return "Trhovisko";
    }

    /**
     * Kontroluje ekonomicku stabilitu v hre
     * Pri nadmernom mnozstve obchodov znizi hodnotu suroviny
     */
    @Override
    public void chyba() {
        this.pocet += this.getSurovina();
        if (this.pocet >= 2000 * this.getSurovina()) {
            JOptionPane.showMessageDialog(null, "V obehu je vela peniaze");
            this.setSurovina(this.getSurovina() * 0.7);
            this.pocet = 0;
        }
    }
}
