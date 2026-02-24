package objekty.budovy;

import akcie.ISpravaniePostav;
import fri.shapesge.Manazer;
import mapa.Hrac;
import objekty.postavy.Lukostrelec;
import objekty.postavy.Mag;
import objekty.postavy.Postava;
import objekty.postavy.Rytier;
import objekty.postavy.Zlodej;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;


/**
 * Trieda reprezentujuca jaskynu v hernom svete
 * Umoznuje postavam plnit ulohy a zbierat suroviny
 */
public class Jaskyna extends Budova implements ISpravaniePostav {

    private Hrac hrac;
    private int timer;

    /**
     * Inicializuje novu jaskynu
     * Nastavi pociatocne hodnoty a spravcu objektu
     */
    public Jaskyna() {
        super(0, 0, 0, 0);
        this.hrac = null;
        this.timer = 0;
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
    }

    /**
     * Ziska nazov budovy
     * @return textovy retazec obsahujuci "Jaskyna"
     */
    @Override
    public String getNazov() {
        return "Jaskyna";
    }

    /**
     * Metoda volana v kazdom hernom cykle
     * Riadi priebeh zadani a zber surovin postavami
     */
    public void tik() {
        if (this.hrac == null) {
            return;
        }
        if (this.timer == 10000) {
            for (Postava postava : this.hrac.getPostavy()) {
                if (postava.getHp() < 1) {
                    this.hrac.vymazPostavu(postava);
                    continue;
                }
                if (postava.jeNaZadani()) {
                    String surovina;
                    double s;
                    switch (postava.getPopis()) {
                        case "Mag" -> {
                            surovina = "kamen";
                            s = postava.getCenaKamena() * (postava.getHp() / 10.0);
                        }
                        case "Rytier" -> {
                            surovina = "zelezo";
                            s = postava.getCenaZeleza() * (postava.getHp() / 10.0);
                        }
                        case "Zlodej" -> {
                            surovina = "zlato";
                            s = postava.getCenaZlata() * (postava.getHp() / 10.0);
                        }
                        case "Lukostrelec" -> {
                            surovina = "drevo";
                            s = postava.getCenaDreva() * (postava.getHp() / 10.0);
                        }
                        default -> {
                            surovina = "null";
                            s = 0;
                        }
                    }
                    this.hrac.pridajSurovinu(surovina, s);
                }
                postava.vratZJaskyne();
            }
        }

        this.timer++;
    }

    /**
     * Zobrazi okno pre posielanie postav na zadania
     * Umoznuje vybrat typ a pocet postav na odoslanie
     */
    @Override
    public void sprav() {
        PoslanieNaMissiu poslanieNaMissiu = new PoslanieNaMissiu() {
            @Override
            void poslanieNaMissiu() {
                this.setTitle("Kolko postav chces poslat?");
                this.setSize(500, 450);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);

                JPanel panel = new JPanel(new BorderLayout());

                String[] moznosti = {"Mag", "Rytier", "Zlodej", "Lukostrelec"};

                JList<String> vyberMoznosti = new JList<>(moznosti);
                vyberMoznosti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                vyberMoznosti.setSelectedIndex(0);

                JScrollPane skrolovaciaPanel = new JScrollPane(vyberMoznosti);
                skrolovaciaPanel.setPreferredSize(new Dimension(150, 100));
                panel.add(skrolovaciaPanel, BorderLayout.WEST);

                JPanel panelPreVpisanie = new JPanel(new FlowLayout());
                panelPreVpisanie.add(new JLabel("Mnozstvo: "));
                JTextField mnozstvo = new JTextField("1", 3);
                panelPreVpisanie.add(mnozstvo);
                panel.add(panelPreVpisanie, BorderLayout.CENTER);

                JPanel panelPotvrdenia = new JPanel(new FlowLayout());
                JButton vytvor = new JButton("Vytvor");
                panelPotvrdenia.add(vytvor);

                panel.add(panelPotvrdenia, BorderLayout.SOUTH);

                vytvor.addActionListener(e -> {
                    try {
                        int pocet = Integer.parseInt(mnozstvo.getText().trim());
                        Postava postava;
                        switch (vyberMoznosti.getSelectedIndex()) {
                            case 0 -> {
                                postava = new Mag();
                            }
                            case 1 -> {
                                postava = new Rytier();
                            }
                            case 2 -> {
                                postava = new Zlodej();
                            }
                            case 3 -> {
                                postava = new Lukostrelec();
                            }
                            default -> throw new NumberFormatException();
                        }
                        if (pocet <= 0) {
                            throw new NumberFormatException();
                        }

                        int maxPocet = 0;

                        for (Postava value : Jaskyna.this.hrac.getPostavy()) {
                            if (value.getPopis().equals(postava.getPopis()) && !(value.jeNaZadani())) {
                                maxPocet++;
                            }
                        }

                        if (pocet > maxPocet) {
                            throw new IllegalArgumentException();
                        }

                        int pom = 0;

                        for (Postava value : Jaskyna.this.hrac.getPostavy()) {
                            if (value.getPopis().equals(postava.getPopis()) && !(value.jeNaZadani())) {
                                value.posliDoJaskyne();
                                pom++;
                            }
                            if (pom == pocet) {
                                break;
                            }
                        }
                        Jaskyna.this.timer = 0;

                        JOptionPane.showMessageDialog(null, "Poslali ste " + pocet + " " + postava.getPopis());
                        this.dispose();

                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Zadaj cislo vacsie ako 0");
                    } catch (IllegalArgumentException exceptione) {
                        JOptionPane.showMessageDialog(null, "Nemas tolko postav");
                    }
                });

                this.add(panel);
                this.setVisible(true);
            }
        };

        poslanieNaMissiu.poslanieNaMissiu();
    }

    /**
     * Vrati hraca
     * @return objekt hraca
     */
    @Override
    public Hrac getHrac() {
        return this.hrac;
    }

    /**
     * Nastavi hraca
     * @param hrac
     */
    @Override
    public void zadajHraca(Hrac hrac) {
        this.hrac = hrac;
    }

    private abstract static class PoslanieNaMissiu extends JFrame {
        abstract void poslanieNaMissiu();
    }
}
