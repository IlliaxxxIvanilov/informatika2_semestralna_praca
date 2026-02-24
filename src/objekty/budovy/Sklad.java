package objekty.budovy;

import akcie.IZlepsovanie;

import javax.swing.JOptionPane;
import java.util.Random;

/**
 * Reprezentuje sklad surovin v hre
 * Umoznuje skladovat zlato, drevo, kamen a zelezo do urcitej kapacity
 */
public class Sklad extends Budova implements IZlepsovanie {

    private double kapacitaZlata;
    private double kapacitaDreva;
    private double kapacitaKamena;
    private double kapacitaZeleza;

    private int uroven;

    /**
     * Vytvori novy sklad s pociatocnymi hodnotami
     */
    public Sklad() {
        super(100, 200, 150, 30);
        this.kapacitaZlata = 1000;
        this.kapacitaDreva = 2500;
        this.kapacitaKamena = 2000;
        this.kapacitaZeleza = 400;
        this.uroven = 1;
    }

    @Override
    /**
     * Vrati nazov budovy
     * @return retazec "Sklad"
     */
    public String getNazov() {
        return "Sklad";
    }

    @Override
    /**
     * Vylepsenie skladu na dalsiu uroven
     * Zvysi ceny surovin o 10% a kapacity o 15%
     * Zvysi uroven o 1
     */
    public void zlepsi() {
        this.setCenaZeleza(this.getCenaZeleza() * 1.1);
        this.setCenaDreva(this.getCenaDreva() * 1.1);
        this.setCenaKamena(this.getCenaKamena() * 1.1);
        this.setCenaZlata(this.getCenaZlata() * 1.1);

        this.kapacitaDreva *= 1.15;
        this.kapacitaKamena *= 1.15;
        this.kapacitaZeleza *= 1.15;
        this.kapacitaZlata *= 1.15;

        this.uroven++;
    }

    @Override
    /**
     * Zisti aktualnu uroven skladu
     * @return cislo urovne
     */
    public int getUroven() {
        return this.uroven;
    }

    @Override
    /**
     * Vrati textovy popis skladu
     * @return retazec obsahujuci uroven skladu
     */
    public String getPopis() {
        return "\nUroven: " + this.uroven;
    }

    @Override
    /**
     * Spracuje nahodnu nehodu v sklade
     * Ma sancu 4/10000 znizit kapacitu niektorej suroviny na polovicu
     */
    public void chyba() {
        Random rand = new Random();
        int nehoda = rand.nextInt(10000);
        if (nehoda == 7777) {
            this.kapacitaZlata *= 0.5;
            JOptionPane.showMessageDialog(null, "Hlad v meste prinutil zachranit jedlo voci zlatu");
        } else if (nehoda == 8888) {
            this.kapacitaDreva *= 0.5;
            JOptionPane.showMessageDialog(null, "Dazd znicil schranok dreva");
        } else if (nehoda == 6666) {
            this.kapacitaKamena *= 0.5;
            JOptionPane.showMessageDialog(null, "Meteorit spadol na kamene, neda sa vyuzit celu kapacitu");
        } else if (nehoda == 5555) {
            this.kapacitaZeleza *= 0.5;
            JOptionPane.showMessageDialog(null, "Zelezo sa stalo odpadom");
        }
    }

    /**
     * Zisti aktualnu kapacitu pre zlato
     *
     * @return maximalna kapacita zlata v sklade
     */
    public double getKapacitaZlata() {
        return this.kapacitaZlata;
    }

    /**
     * Zisti aktualnu kapacitu pre drevo
     *
     * @return maximalna kapacita dreva v sklade
     */
    public double getKapacitaDreva() {
        return this.kapacitaDreva;
    }

    /**
     * Zisti aktualnu kapacitu pre kamen
     *
     * @return maximalna kapacita kamena v sklade
     */
    public double getKapacitaKamena() {
        return this.kapacitaKamena;
    }

    /**
     * Zisti aktualnu kapacitu pre zelezo
     *
     * @return maximalna kapacita zeleza v sklade
     */
    public double getKapacitaZeleza() {
        return this.kapacitaZeleza;
    }
}
