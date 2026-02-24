package objekty.budovy;

import mapa.Hrac;
import akcie.ISpravaniePostav;
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
import java.util.ArrayList;

/**
 * Trieda reprezentujuca vojenske kasarne pre vyrobu bojovych jednotiek
 * Umoznuje vyber typu jednotky a mnozstva za dostupne suroviny
 */
public class Kasaren extends Budova implements ISpravaniePostav {

    private ArrayList<Postava> postavy;
    private Hrac hrac;

    /**
     * Vytvori novy objekt kasarne s prazdnym zoznamom jednotiek
     * Nastavi vsetky pociatocne hodnoty na nulu
     */
    public Kasaren() {
        super(0, 0, 0 , 0);
        this.hrac = null;
        this.postavy = new ArrayList<>();
    }

    /**
     * Ziskanie identifikacneho nazvu budovy
     *
     * @return retazec s nazvom "Kasaren"
     */
    @Override
    public String getNazov() {
        return "Kasaren";
    }

    /**
     * Vytvori graficke rozhranie pre stavbu novych jednotiek
     * Umoznuje vybrat typ jednotky a zadat pocet na vytvorenie
     */
    @Override
    public void sprav() {
        ZadanieMnozstva mnozstvo = new ZadanieMnozstva() {
            @Override
            void zadajMnozstvo() {
                this.setTitle("Zadaj mnozstvo a vyber postavu");
                this.setSize(400, 350);
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

                        if ((pocet * postava.getCenaZlata()) > Kasaren.this.hrac.getZlato() && (pocet * postava.getCenaDreva()) > Kasaren.this.hrac.getDrevo() && (pocet * postava.getCenaKamena()) > Kasaren.this.hrac.getKamen() && (pocet * postava.getCenaZeleza()) > Kasaren.this.hrac.getZelezo()) {
                            throw new IllegalArgumentException();
                        }

                        for (int i = 0; i < pocet; i++) {
                            Kasaren.this.postavy.add(postava);
                            Kasaren.this.hrac.vymazSurovinu("zlato", postava.getCenaZlata());
                            Kasaren.this.hrac.vymazSurovinu("drevo", postava.getCenaDreva());
                            Kasaren.this.hrac.vymazSurovinu("kamen", postava.getCenaKamena());
                            Kasaren.this.hrac.vymazSurovinu("zelezo", postava.getCenaZeleza());
                        }

                        JOptionPane.showMessageDialog(null, "Vytvorili ste " + pocet + " " + postava.getPopis());
                        this.dispose();

                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Zadaj cislo vacsie ako 0");
                    } catch (IllegalArgumentException exceptione) {
                        JOptionPane.showMessageDialog(null, "Nemas dostatocny pocet surovin");
                    }
                });

                this.add(panel);
                this.setVisible(true);
            }
        };
        mnozstvo.zadajMnozstvo();
    }

    /**
     * Zisti aktualneho vlastnika kasarne
     *
     * @return hrac, ktory vlastni kasaren, pripadne null
     */
    @Override
    public Hrac getHrac() {
        this.hrac.setPostavy(this.postavy);
        return this.hrac;
    }

    /**
     * Nastavi noveho vlastnika kasarne
     *
     * @param hrac novy vlastnik kasarne a zdroj jednotiek
     */
    @Override
    public void zadajHraca(Hrac hrac) {
        this.hrac = hrac;
        this.postavy = hrac.getPostavy();
    }

    private abstract static class ZadanieMnozstva extends JFrame {
        abstract void zadajMnozstvo();
    }
}
