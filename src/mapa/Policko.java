package mapa;

import objekty.Objekt;
import akcie.ISpravaniePostav;
import akcie.IZlepsovanie;
import fri.shapesge.Obrazok;
import fri.shapesge.Stvorec;
import objekty.budovy.Bana;
import objekty.budovy.Budova;
import objekty.budovy.DrevorubacskaChata;
import objekty.budovy.Kamenolom;
import objekty.budovy.Sklad;
import objekty.budovy.Trhovisko;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Reprezentuje policko na hernej ploche
 * Umoznuje spravovanie budov a ich interakciu
 */
public class Policko {
    private final Stvorec stvorec;
    private final int x;
    private final int y;
    private boolean jeBudova;
    private Budova budova;
    private final ArrayList<String> minuleBudovy;
    private Obrazok obrazok;
    private Hrac hrac;

    /**
     * Vytvori nove policko na hernej ploche
     * @param x x-ova pozicia policka
     * @param y y-ova pozicia policka
     * @param rozmer velkost policka v pixeloch
     */
    public Policko(int x, int y, int rozmer) {
        this.hrac = null;
        this.x = x;
        this.y = y;
        this.stvorec = new Stvorec(x, y);
        this.jeBudova = false;
        this.stvorec.zmenStranu(rozmer);
        this.stvorec.zobraz();
        this.stvorec.zmenFarbu("green");
        this.minuleBudovy = new ArrayList<>();
    }

    /**
     * Vrati x-ovu suradnicu policka
     * @return x-ova pozicia
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vrati y-ovu suradnicu policka
     * @return y-ova pozicia
     */
    public int getY() {
        return this.y;
    }

    /**
     * Zisti ci je na policku postavena budova
     * @return true ak je na policku budova, false ak nie
     */
    public boolean jeBudova() {
        return this.jeBudova;
    }

    /**
     * Vytvori textovy popis policka
     * @return retazec popisujuci stav policka a jeho budovu
     */
    public String toString() {
        String pom = "nie je";
        if (this.jeBudova()) {
            pom = this.budova.getNazov();
        }
        if (this.budova instanceof IZlepsovanie) {
            return "Budova: " + pom + ((IZlepsovanie)this.budova).getPopis();
        }
        return "Budova: " + pom;
    }

    /**
     * Vrati referenciu na budovu na policku
     * @return objekt budovy alebo null ak ziadna neexistuje
     */
    public Budova getBudova() {
        return this.budova;
    }

    /**
     * Umiestni budovu na policko
     * @param budova budova ktora sa ma polozit
     */
    public void poloz(Budova budova) {
        this.budova = budova;
        this.jeBudova = true;
        this.obrazok = new Obrazok("obrazky/" + budova.getNazov() + ".png");
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
    }

    private boolean skontroluj(Objekt objekt) {
        return objekt.getCenaDreva() <= this.hrac.getDrevo() && objekt.getCenaKamena() <= this.hrac.getKamen() && objekt.getCenaZeleza() <= this.hrac.getZelezo() && objekt.getCenaZlata() <= this.hrac.getZlato();
    }

    private void odpocitaj(Objekt objekt) {
        this.hrac.vymazSurovinu("drevo", objekt.getCenaDreva());
        this.hrac.vymazSurovinu("kamen", objekt.getCenaKamena());
        this.hrac.vymazSurovinu("zelezo", objekt.getCenaZeleza());
        this.hrac.vymazSurovinu("zlato", objekt.getCenaZlata());
    }

    /**
     * Vrati hraca vlastniaceho policko
     * @return objekt hraca
     */
    public Hrac getHrac() {
        return this.hrac;
    }

    /**
     * Nastavi hraca
     *
     * @param hrac hrac
     */
    public void setHrac(Hrac hrac) {
        this.hrac = hrac;
    }

    /**
     * Postavi sklad na policku ak su dostupne suroviny
     * Kontroluje ci na policku uz nie je budova
     */
    public void postavSklad() {
        if (!this.jeBudova()) {
            if (this.skontroluj(new Sklad())) {
                this.poloz(new Sklad());
                this.odpocitaj(new Sklad());
            } else {
                JOptionPane.showMessageDialog(null, "Nemas dostatocny pocet surovin");
            }
        }
    }

    /**
     * Postavi banu na policku ak su dostupne suroviny
     * Kontroluje ci na policku uz nie je budova a ci tam nebola bana
     */
    public void postavBanu() {
        if (!this.jeBudova() && !this.minuleBudovy.contains("Bana")) {
            if (this.skontroluj(new Bana())) {
                this.poloz(new Bana());
                this.odpocitaj(new Bana());
            } else {
                JOptionPane.showMessageDialog(null, "Nemas dostatocny pocet surovin");
            }
        }
    }

    /**
     * Postavi drevovubacsku chatu na policku ak su dostupne suroviny
     * Kontroluje ci na policku uz nie je budova
     */
    public void postavChatu() {
        if (!this.jeBudova()) {
            if (this.skontroluj(new DrevorubacskaChata())) {
                this.poloz(new DrevorubacskaChata());
                this.odpocitaj(new DrevorubacskaChata());
            } else {
                JOptionPane.showMessageDialog(null, "Nemas dostatocny pocet surovin");
            }
        }
    }

    /**
     * Postavi trhovisko na policku ak su dostupne suroviny
     * Kontroluje ci na policku uz nie je budova
     */
    public void postavTrhovisko() {
        if (!this.jeBudova()) {
            if (this.skontroluj(new Trhovisko())) {
                this.poloz(new Trhovisko());
                this.odpocitaj(new Trhovisko());
            } else {
                JOptionPane.showMessageDialog(null, "Nemas dostatocny pocet surovin");
            }
        }
    }

    /**
     * Postavi kamenolom na policku ak su dostupne suroviny
     * Kontroluje ci na policku uz nie je budova
     */
    public void postavKamen() {
        if (!this.jeBudova()) {
            if (this.skontroluj(new Kamenolom(this.minuleBudovy.contains("Bana")))) {
                this.poloz(new Kamenolom(this.minuleBudovy.contains("Bana")));
                this.odpocitaj(new Kamenolom(this.minuleBudovy.contains("Bana")));
            } else {
                JOptionPane.showMessageDialog(null, "Nemas dostatocny pocet surovin");
            }
        }
    }

    /**
     * Obnovi stav budovy na policku
     * Aktualizuje referenciu na hraca pre budovy s postavami
     */
    public void obnovi() {
        if (this.jeBudova() && this.budova instanceof ISpravaniePostav) {
            ((ISpravaniePostav)this.budova).zadajHraca(this.hrac);
            this.hrac = ((ISpravaniePostav)this.budova).getHrac();
        }
    }

    /**
     * Aktivuje specialnu schopnost budovy na policku
     * Pre budovy s vylepovanim kontroluje dostupnost surovin
     * Pre budovy s postavami aktualizuje ich stav
     */
    public void aktivuj() {
        if (this.jeBudova() && this.budova instanceof IZlepsovanie) {
            if (this.skontroluj(this.budova)) {
                ((IZlepsovanie)this.budova).zlepsi();
                this.odpocitaj(this.budova);
            } else {
                JOptionPane.showMessageDialog(null, "Nemas dostatocny pocet surovin");
            }
        }
        if (this.jeBudova() && this.budova instanceof ISpravaniePostav) {
            ((ISpravaniePostav)this.budova).zadajHraca(this.hrac);
            ((ISpravaniePostav)this.budova).sprav();
            this.hrac = ((ISpravaniePostav)this.budova).getHrac();
        }
    }

    /**
     * Zrusi budovu na policku a ulozi jej nazov do historie
     * Po zruseni nie je mozne postavit niektore budovy na rovnake miesto
     */
    public void zrus() {
        if (this.jeBudova) {
            this.obrazok = null;
            this.minuleBudovy.add(this.budova.getNazov());
            this.budova = null;
        }
    }

}
