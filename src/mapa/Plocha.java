package mapa;

import akcie.IZlepsovanie;
import fri.shapesge.BlokTextu;
import fri.shapesge.Manazer;
import fri.shapesge.StylFontu;
import objekty.budovy.Bana;
import objekty.budovy.BudovaSuroviny;
import objekty.budovy.DrevorubacskaChata;
import objekty.budovy.Jaskyna;
import objekty.budovy.Kamenolom;
import objekty.budovy.Kasaren;
import objekty.budovy.Sklad;
import objekty.budovy.TownHall;
import objekty.budovy.Trhovisko;

import java.util.ArrayList;


/**
 * Trieda reprezentujuca hlavnu hernu mapu.
 * Zabezpecuje spravu policok, budov a produkcie surovin v hre.
 */
public class Plocha {
    private final Policko[][] pole;
    private final int dlzka;
    private final int vyska;
    private final Manazer manazer;
    private final BlokTextu text;
    private final ArrayList<String> suroviny;
    private Hrac hrac;

    private final BlokTextu drevo;
    private final BlokTextu zlato;
    private final BlokTextu kamen;
    private final BlokTextu zelezo;

    /**
     * Konstruktor na vytvorenie novej hernej plochy.
     * Inicializuje zakladne suroviny, vytvara mapu a nastavuje pociatocne budovy.
     */
    public Plocha() {
        this.suroviny = new ArrayList<>();
        this.suroviny.add("drevo");
        this.suroviny.add("zlato");
        this.suroviny.add("kamen");
        this.suroviny.add("zelezo");
        this.hrac = new Hrac();

        this.drevo = new BlokTextu("");
        this.drevo.zmenFont("BOLD", StylFontu.BOLD, 12);
        this.drevo.zmenPolohu(500, 15);
        this.drevo.zobraz();
        this.zlato = new BlokTextu("");
        this.zlato.zmenFont("BOLD", StylFontu.BOLD, 12);
        this.zlato.zmenPolohu(500, 30);
        this.zlato.zobraz();
        this.kamen = new BlokTextu("");
        this.kamen.zmenFont("BOLD", StylFontu.BOLD, 12);
        this.kamen.zmenPolohu(500, 45);
        this.kamen.zobraz();
        this.zelezo = new BlokTextu("");
        this.zelezo.zmenFont("BOLD", StylFontu.BOLD, 12);
        this.zelezo.zmenPolohu(500, 60);
        this.zelezo.zobraz();

        this.manazer = new Manazer();
        this.text = new BlokTextu("");
        this.text.zmenFont("BOLD", StylFontu.BOLD, 14);
        this.text.zobraz();
        this.dlzka = 9;
        this.vyska = 10;
        this.pole = new Policko[this.dlzka][this.vyska];
        for (int i = 0; i < this.dlzka; i++) {
            for (int j = 0; j < this.vyska; j++) {
                this.pole[i][j] = new Policko((j * 65) + 2, (i * 65) + 70, 60);
            }
        }
        this.pole[0][0].poloz(new TownHall());
        this.pole[0][1].poloz(new Bana());
        this.pole[1][0].poloz(new Trhovisko());
        this.pole[1][1].poloz(new DrevorubacskaChata());
        this.pole[2][1].poloz(new Kamenolom(false));
        this.pole[1][2].poloz(new Kasaren());
        this.pole[2][2].poloz(new Sklad());
        this.pole[this.dlzka - 1][this.vyska - 1].poloz(new Jaskyna());
        this.manazer.spravujObjekt(this);
    }

    /**
     * Spracuje kliknutie na hernu plochu a aktivuje vybrane policko.
     *
     * @param x suradnica x kliknutia na obrazovke v pixeloch
     * @param y suradnica y kliknutia na obrazovke v pixeloch
     */
    public void vyberSuradnice(int x, int y) {
        for (int i = 0; i < this.dlzka; i++) {
            for (int j = 0; j < this.vyska; j++) {
                this.manazer.prestanSpravovatObjekt(this.pole[i][j]);
            }
        }
        for (int i = 0; i < this.dlzka; i++) {
            for (int j = 0; j < this.vyska; j++) {
                if (x >= this.pole[i][j].getX() && x <= this.pole[i][j].getX() + 65 && y >= this.pole[i][j].getY() && y <= this.pole[i][j].getY() + 65) {
                    this.pole[i][j].setHrac(this.hrac);
                    this.manazer.spravujObjekt(this.pole[i][j]);
                    this.hrac = this.pole[i][j].getHrac();
                    this.text.zmenText(this.pole[i][j].toString());
                    break;
                }
            }
        }

    }

    /**
     * Aktualizuje stav hernej plochy v kazdom casovom intervale.
     * Prepocita produkciu surovin, kontroluje stav budov a aktualizuje informacie o zdrojoch.
     */
    public void tik() {
        double kapacitaDreva = 0;
        double kapacitaZlata = 0;
        double kapacitaKamena = 0;
        double kapacitaZeleza = 0;
        for (int i = 0; i < this.dlzka; i++) {
            for (int j = 0; j < this.vyska; j++) {
                this.pole[i][j].setHrac(this.hrac);
                this.pole[i][j].obnovi();
                if (this.pole[i][j].getBudova() instanceof IZlepsovanie) {
                    ((IZlepsovanie)this.pole[i][j].getBudova()).chyba();
                }
                if (this.pole[i][j].getBudova() instanceof Sklad) {
                    kapacitaKamena += ((Sklad)this.pole[i][j].getBudova()).getKapacitaKamena();
                    kapacitaZlata += ((Sklad)this.pole[i][j].getBudova()).getKapacitaZlata();
                    kapacitaDreva += ((Sklad)this.pole[i][j].getBudova()).getKapacitaDreva();
                    kapacitaZeleza += ((Sklad)this.pole[i][j].getBudova()).getKapacitaZeleza();
                }
                if (this.pole[i][j].getBudova() instanceof BudovaSuroviny) {
                    switch (this.pole[i][j].getBudova()) {
                        case Kamenolom ignored -> this.hrac.pridajSurovinu(this.suroviny.get(2), ((BudovaSuroviny)this.pole[i][j].getBudova()).getSurovina());
                        case DrevorubacskaChata ignored -> this.hrac.pridajSurovinu(this.suroviny.get(0), ((BudovaSuroviny)this.pole[i][j].getBudova()).getSurovina());
                        case Bana ignored -> this.hrac.pridajSurovinu(this.suroviny.get(3), ((BudovaSuroviny)this.pole[i][j].getBudova()).getSurovina());
                        case Trhovisko ignored -> this.hrac.pridajSurovinu(this.suroviny.get(1), ((BudovaSuroviny)this.pole[i][j].getBudova()).getSurovina());
                        default -> throw new IllegalStateException("Unexpected value: " + this.pole[i][j].getBudova());
                    }
                }
            }
        }
        this.hrac.setKapacita(this.suroviny.get(0), kapacitaDreva);
        this.hrac.setKapacita(this.suroviny.get(1), kapacitaZlata);
        this.hrac.setKapacita(this.suroviny.get(2), kapacitaKamena);
        this.hrac.setKapacita(this.suroviny.get(3), kapacitaZeleza);

        this.drevo.zmenText(String.format(this.suroviny.get(0) + ": %.2f/%.2f", this.hrac.getDrevo(), this.hrac.getKapacita(this.suroviny.get(0))));
        this.zlato.zmenText(String.format(this.suroviny.get(1) + ": %.2f/%.2f", this.hrac.getZlato(), this.hrac.getKapacita(this.suroviny.get(1))));
        this.kamen.zmenText(String.format(this.suroviny.get(2) + ": %.2f/%.2f", this.hrac.getKamen(), this.hrac.getKapacita(this.suroviny.get(2))));
        this.zelezo.zmenText(String.format(this.suroviny.get(3) + ": %.2f/%.2f", this.hrac.getZelezo(), this.hrac.getKapacita(this.suroviny.get(3))));

    }
}
