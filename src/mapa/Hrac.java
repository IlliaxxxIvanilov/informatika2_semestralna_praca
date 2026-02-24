package mapa;

import objekty.postavy.Postava;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Reprezentuje hraca v hre spravujuceho suroviny a jednotky
 */
public class Hrac {
    private double drevo;
    private double zlato;
    private double kamen;
    private double zelezo;

    private double kapacitaDreva;
    private double kapacitaZlata;
    private double kapacitaKamena;
    private double kapacitaZeleza;

    private ArrayList<Postava> postavy;

    /**
     * Vytvori noveho hraca s nulovymi surovinami a prazdnym zoznamom jednotiek
     */
    public Hrac() {
        this.drevo = 0;
        this.zlato = 0;
        this.kamen = 0;
        this.zelezo = 0;

        this.kapacitaDreva = 0;
        this.kapacitaZlata = 0;
        this.kapacitaKamena = 0;
        this.kapacitaZeleza = 0;

        this.postavy = new ArrayList<>();
    }

    /**
     * Nastavi zoznam hracovych jednotiek
     * @param postavy zoznam jednotiek na nastavenie
     */
    public void setPostavy(ArrayList<Postava> postavy) {
        this.postavy = postavy;
    }

    /**
     * Ziska zoznam hracovych jednotiek
     * @return kopia zoznamu jednotiek
     */
    public ArrayList<Postava> getPostavy() {
        ArrayList<Postava> pom = this.postavy;
        return pom;
    }

    /**
     * Odstrani zadanu jednotku zo zoznamu hraca
     * @param postava jednotka na odstranenie
     */
    public void vymazPostavu(Postava postava) {
        this.postavy.remove(postava);
    }

    /**
     * Prida zadane mnozstvo suroviny ak to kapacita dovoluje
     * @param kluc typ suroviny (drevo, zlato, kamen, zelezo)
     * @param hodnota mnozstvo na pridanie
     */
    public void pridajSurovinu(String kluc, double hodnota) {
        switch (kluc) {
            case "drevo":
                if (this.drevo + hodnota > this.kapacitaDreva) {
                    return;
                }
                this.drevo += hodnota;
                break;
            case "zlato":
                if (this.zlato + hodnota > this.kapacitaDreva) {
                    return;
                }
                this.zlato += hodnota;
                break;
            case "kamen":
                if (this.kamen + hodnota > this.kapacitaDreva) {
                    return;
                }
                this.kamen += hodnota;
                break;
            case "zelezo":
                if (this.zelezo + hodnota > this.kapacitaDreva) {
                    return;
                }
                this.zelezo += hodnota;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Neplatny typ suroviny");
                break;
        }
    }

    /**
     * Nastavi skladovaciu kapacitu pre zadanu surovinu
     * @param kluc typ suroviny (drevo, zlato, kamen, zelezo)
     * @param hodnota nova hodnota kapacity
     */
    public void setKapacita(String kluc, double hodnota) {
        switch (kluc) {
            case "drevo":
                this.kapacitaDreva = hodnota;
                break;
            case "zlato":
                this.kapacitaZlata = hodnota;
                break;
            case "kamen":
                this.kapacitaKamena = hodnota;
                break;
            case "zelezo":
                this.kapacitaZeleza = hodnota;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Neplatny typ suroviny");
                break;
        }
    }

    /**
     * Odpocita zadane mnozstvo suroviny
     * @param kluc typ suroviny (drevo, zlato, kamen, zelezo)
     * @param hodnota mnozstvo na odpocitanie
     * @return true ak uspesne, false ak nedostatocne mnozstvo
     */
    public boolean vymazSurovinu(String kluc, double hodnota) {
        return switch (kluc) {
            case "drevo" -> {
                if (this.drevo - hodnota < 0) {
                    yield false;
                }
                this.drevo -= hodnota;
                yield true;
            }
            case "zlato" -> {
                if (this.zlato - hodnota < 0) {
                    yield false;
                }
                this.zlato -= hodnota;
                yield true;
            }
            case "kamen" -> {
                if (this.kamen - hodnota < 0) {
                    yield false;
                }
                this.kamen -= hodnota;
                yield true;
            }
            case "zelezo" -> {
                if (this.zelezo - hodnota < 0) {
                    yield false;
                }
                this.zelezo -= hodnota;
                yield true;
            }
            default -> {
                JOptionPane.showMessageDialog(null, "Neplatny typ suroviny");
                yield false;
            }
        };
    }

    /**
     * Ziska aktualne mnozstvo dreva
     * @return mnozstvo dreva
     */
    public double getDrevo() {
        return this.drevo;
    }

    /**
     * Ziska aktualne mnozstvo zlata
     * @return mnozstvo zlata
     */
    public double getZlato() {
        return this.zlato;
    }

    /**
     * Ziska aktualne mnozstvo kamena
     *
     * @return mnozstvo kamena
     */
    public double getKamen() {
        return this.kamen;
    }

    /**
     * Ziska aktualne mnozstvo zeleza
     *
     * @return mnozstvo zeleza
     */
    public double getZelezo() {
        return this.zelezo;
    }

    /**
     * Ziska skladovaciu kapacitu pre zadanu surovinu
     *
     * @param kluc typ suroviny (drevo, zlato, kamen, zelezo)
     * @return hodnota kapacity pre danu surovinu, 0 ak neplatny typ
     */
    public double getKapacita(String kluc) {
        return switch (kluc) {
            case "drevo" -> this.kapacitaDreva;
            case "zlato" -> this.kapacitaZlata;
            case "kamen" -> this.kapacitaKamena;
            case "zelezo" -> this.kapacitaZeleza;
            default -> {
                JOptionPane.showMessageDialog(null, "Neplatny typ suroviny");
                yield 0;
            }
        };
    }
}
