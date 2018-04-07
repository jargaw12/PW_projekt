import java.util.ArrayList;
import java.util.Random;

public class Gasienica implements Runnable {
    private ArrayList<Pole> polaGasienic;
    private Trasa trasa;
    private int predkosc;
    private int dlugosc;
    private static Plansza plansza;
    public static Random random = new Random();

    public ArrayList<Pole> getPolaGasienic() {
        return polaGasienic;
    }

    public Gasienica(int n, int v, Trasa trasa, Plansza plansza) {
        this.dlugosc = n;
        this.predkosc = 1 + random.nextInt(3);
        this.trasa = trasa;
        this.plansza = plansza;
        polaGasienic = new ArrayList<>();
        new Thread(this);
    }

    public void dodajGasienice(int n) {
        for (int i = n; i < dlugosc + n; i++) {
            polaGasienic.add(new Pole(trasa.getPolaTrasy().get(trasa.getPolaTrasy().size() - i).getX(), trasa.getPolaTrasy().get(trasa.getPolaTrasy().size() - i).getY()));
        }
    }

    public ArrayList<Pole> getPolaGasienicy() {
        return polaGasienic;
    }

    private static Plansza getPlansza() {
        return plansza;
    }

    public Pole nastepne(int n) {
        Pole next = this.trasa.getPolaTrasy().get(0);
        for (int i = 0; i < trasa.getPolaTrasy().size(); i++) {
            if (this.polaGasienic.get(0).getX() == trasa.getPolaTrasy().get(i).getX() && this.polaGasienic.get(0).getY() == trasa.getPolaTrasy().get(i).getY())
                if ((i + 1 + n) >= 0) {
                    next = trasa.getPolaTrasy().get((i + 1 + n) % (trasa.getPolaTrasy().size()));
                    return next;
                } else {
                    next = trasa.getPolaTrasy().get((i + 1 + n) + (trasa.getPolaTrasy().size()));
                    return next;
                }
        }
        return next;
    }

    public void chodz(Pole nastepne) {
        for (int i = polaGasienic.size() - 1; i > 0; i--) {
            polaGasienic.get(i).setX(polaGasienic.get(i - 1).getX());
            polaGasienic.get(i).setY(polaGasienic.get(i - 1).getY());
        }
        polaGasienic.get(0).setX(nastepne.getX());
        polaGasienic.get(0).setY(nastepne.getY());
        plansza.repaint();
        nastepne(-2).setZajete(false);
        nastepne((this.getPolaGasienic().size()) * (-1) - 1).setZajete(false);
        nastepne(-1).setZajete(true);
        nastepne((this.getPolaGasienic().size()) * (-1)).setZajete(true);
        try {
            Thread.sleep(180 / predkosc);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void p1() {
        for (int i = 0; i < trasa.getWspolnaDroga1().polaWspolnejDrogi.size(); i++) {
            while (nastepne(1).isZajete()) {
            }
            chodz(nastepne(0));
        }
    }

    public void p2() {
        for (int i = 0; i <= getPolaGasienicy().size() - 1; i++) {
            while (nastepne(1).isZajete()) {
            }
            chodz(nastepne(0));
        }
    }

    public void p3() {
        while (nastepne(1).isZajete()) {
        }
        chodz(nastepne(0));
    }

    public void p4() {
        for (int i = 0; i < trasa.getWspolnaDroga3().polaWspolnejDrogi.size(); i++) {
            while (nastepne(1).isZajete()) {
            }
            chodz(nastepne(0));
        }
    }

    @Override
    public void run() {
        WspolnaDroga aktualna = nastepne((this.dlugosc) * (-1)).getWspolnaDroga();
        if ((nastepne((this.dlugosc) * (-1)).isWspoldzielony())) {
            while (aktualna.nieudaneZajecie(trasa.getWspolnaDroga2(), trasa.getWspolnaDroga2())) {
            }
            while (aktualna.isZajete()) {
                while (nastepne(1).isZajete()) {
                }
                chodz(nastepne(0));
                if (!(nastepne((this.dlugosc + 1) * (-1)).isWspoldzielony())) {
                    aktualna.zejscie();
                }
            }
        }
        while (true) {
            if (nastepne(0).isWspoldzielony()) {
                while (trasa.getWspolnaDroga1().nieudaneZajecie(trasa.getWspolnaDroga3(), trasa.getWspolnaDrogaInna())) {
                }
                p1();

                while (trasa.getWspolnaDroga2().nieudaneZajecie(trasa.getWspolnaDroga2(), trasa.getWspolnaDroga3())) {
                }
                p2();
                trasa.getWspolnaDroga1().zejscie();
                p3();
                trasa.getWspolnaDroga2().zejscie();
                p4();
                trasa.getWspolnaDroga3().zejscie();
            } else {
                while (nastepne(1).isZajete()) {
                }
                chodz(nastepne(0));
            }
            getPlansza().repaint();

        }
    }
}
