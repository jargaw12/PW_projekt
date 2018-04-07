import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Plansza extends JPanel {
    public ArrayList<Gasienica> gasieniceCzerwone = new ArrayList<>();
    public ArrayList<Gasienica> gasieniceNiebieskie = new ArrayList<>();
    public ArrayList<Gasienica> gasieniceZielone = new ArrayList<>();
    public static int liczbaCzerwonych = 2;
    public static int liczbaNiebieskich = 1;
    public static int liczbaZielonych = 1;
    ArrayList<Thread> watki = new ArrayList();

    private ArrayList<Trasa> trasy = new ArrayList<>();

    public Plansza() {
        WspolnaDroga drogaCNZ = new WspolnaDroga(new Pole(5 * Pole.getSzerokosc(), 10 * Pole.getWysokosc()), new Pole(5 * Pole.getSzerokosc(), 10 * Pole.getWysokosc()));
        WspolnaDroga drogaCN = new WspolnaDroga(new Pole(5 * Pole.getSzerokosc(), 0), new Pole(5 * Pole.getSzerokosc(), 9 * Pole.getWysokosc()));
        WspolnaDroga drogaCZ = new WspolnaDroga(new Pole(2 * Pole.getSzerokosc(), 10 * Pole.getWysokosc()), new Pole(4 * Pole.getSzerokosc(), 10 * Pole.getWysokosc()));
        WspolnaDroga drogaNZ = new WspolnaDroga(new Pole(6 * Pole.getSzerokosc(), 10 * Pole.getWysokosc()), new Pole(8 * Pole.getSzerokosc(), 10 * Pole.getWysokosc()));

        Trasa trasaCzerwona;
        trasy.add(trasaCzerwona = new Trasa(6, 11, new Pole(0, 0), drogaCN, drogaCNZ, drogaCZ, drogaNZ));
        trasaCzerwona.dodajPolaCzerwone();
        Trasa trasaNiebieska;
        trasy.add(trasaNiebieska = new Trasa(6, 11, new Pole(trasaCzerwona.getPktStartowy().getX() + (trasaCzerwona.getlPoziomych() - 1) * Pole.getSzerokosc(), trasaCzerwona.getPktStartowy().getY()), drogaNZ, drogaCNZ, drogaCN, drogaCZ));
        trasaNiebieska.dodajPolaNiebieskie();
        Trasa trasaZielona;
        trasy.add(trasaZielona = new Trasa(7, 8, new Pole(trasaCzerwona.getPktStartowy().getX() + 2 * Pole.getSzerokosc(), trasaCzerwona.getPktStartowy().getY() + (trasaCzerwona.getlPionowych() - 1) * Pole.getWysokosc()), drogaCZ, drogaCNZ, drogaNZ, drogaCN));
        trasaZielona.dodajPolaZielone();

        if (liczbaNiebieskich < 1) liczbaNiebieskich = 0;
        if (liczbaNiebieskich >= 1) liczbaNiebieskich = 1;
        for (int s = 1; s <= liczbaNiebieskich; s++) {
            gasieniceNiebieskie.add(new Gasienica(9, 4, trasaNiebieska, this));
        }
        dn();

        if (liczbaCzerwonych < 1) liczbaCzerwonych = 0;
        if (liczbaCzerwonych >= 2) liczbaCzerwonych = 2;
        for (int s = 1; s <= liczbaCzerwonych; s++) {
            gasieniceCzerwone.add(new Gasienica(6, 2, trasaCzerwona, this));
        }
        dc();

        if (liczbaZielonych < 1) liczbaZielonych = 0;
        if (liczbaZielonych >= 4) liczbaZielonych = 4;
        for (int s = 1; s <= liczbaZielonych; s++) {
            gasieniceZielone.add(new Gasienica(4, 1, trasaZielona, this));
        }
        dz();

        for (Thread t : watki) {
            t.start();
        }
    }

    public void dz() {
        int i = 1;
        for (Gasienica c : gasieniceZielone) {
            c.dodajGasienice(i);
            c.nastepne((c.getPolaGasienic().size()) * (-1)).setZajete(true);
            watki.add(new Thread(c));
            i = i + c.getPolaGasienicy().size() + 1;
        }
    }

    public void dc() {
        int i = 1;
        for (Gasienica c : gasieniceCzerwone) {
            c.dodajGasienice(i);
            c.nastepne((c.getPolaGasienic().size()) * (-1)).setZajete(true);
            watki.add(new Thread(c));
            i = i + c.getPolaGasienicy().size() + 1;
        }
    }

    public void dn() {
        int i = 1;
        for (Gasienica c : gasieniceNiebieskie) {
            c.dodajGasienice(i);
            c.nastepne((c.getPolaGasienic().size()) * (-1)).setZajete(true);
            watki.add(new Thread(c));
            i = i + c.getPolaGasienicy().size() + 1;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < 3; i++) {
            // rysuje trasy
            for (Pole x : trasy.get(i).getPolaTrasy()) {
                g.setColor(Color.black);
                g.drawRect(x.getX(), x.getY(), Pole.getSzerokosc(), Pole.getWysokosc());
            }
        }
        //rysuje gasienice

        for (Gasienica s : gasieniceCzerwone) {
            for (Pole x : s.getPolaGasienic()) {
                g.setColor(Color.RED);
                g.fillRect(x.getX(), x.getY(), Pole.getSzerokosc(), Pole.getWysokosc());
                g.setColor(Color.black);
                g.drawRect(x.getX(), x.getY(), Pole.getSzerokosc(), Pole.getWysokosc());
            }
        }


        for (Gasienica s : gasieniceNiebieskie) {
            for (Pole x : s.getPolaGasienic()) {
                g.setColor(Color.blue);
                g.fillRect(x.getX(), x.getY(), Pole.getSzerokosc(), Pole.getWysokosc());
                g.setColor(Color.black);
                g.drawRect(x.getX(), x.getY(), Pole.getSzerokosc(), Pole.getWysokosc());
            }
        }


        for (Gasienica s : gasieniceZielone) {
            for (Pole x : s.getPolaGasienic()) {
                g.setColor(Color.green);
                g.fillRect(x.getX(), x.getY(), Pole.getSzerokosc(), Pole.getWysokosc());
                g.setColor(Color.black);
                g.drawRect(x.getX(), x.getY(), Pole.getSzerokosc(), Pole.getWysokosc());
            }
        }
    }
}
