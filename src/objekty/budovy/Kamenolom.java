package objekty.budovy;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Trieda reprezentuje budovu na tazbu kamena v hre
 * Obsahuje system produkcie kamena a nahodnych nehod
 * Ak bola na mieste predtym bana, ma dvojnasobnu produkciu
 */
public class Kamenolom extends BudovaSuroviny {

    private boolean jeChyba;
    private int pocetCasu;
    private double pocetSuroviny;

    /**
     * Vytvori novy kamenolom s nastavenymi vlastnostami
     *
     * @param bolaBana urcuje ci bola tu bana
     */
    public Kamenolom(boolean bolaBana) {
        super(300, 500, 450, 30, 0);
        if (bolaBana) {
            this.setSurovina(1.8);
        } else {
            this.setSurovina(0.9);
        }

        this.jeChyba = false;
        this.pocetCasu = 0;
    }

    /**
     * Vrati nazov tejto budovy
     *
     * @return textovy retazec s nazvom "Kamenolom"
     */
    @Override
    public String getNazov() {
        return "Kamenolom";
    }

    /**
     * Kontroluje a riesi pripadne nehody v kamenolome
     * Pri nehode (sanca 1/600) sa zastavi produkcia na 50 jednotiek casu
     */
    @Override
    public void chyba() {
        Random rand = new Random();
        int random = rand.nextInt(600);
        if (random == 50 && !this.jeChyba) {
            this.jeChyba = true;
            this.pocetSuroviny = this.getSurovina();
            this.setSurovina(0);
            JOptionPane.showMessageDialog(null, "Kamenom zabilo pracovnika");
        } else if (this.jeChyba) {
            this.pocetCasu++;
            if (this.pocetCasu >= 50) {
                this.pocetCasu = 0;
                this.jeChyba = false;
                this.setSurovina(this.pocetSuroviny);
                JOptionPane.showMessageDialog(null, "Kamenolom moze pokracovat");
            }
        }
    }
}
