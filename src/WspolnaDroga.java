import java.util.ArrayList;

public class WspolnaDroga {
    private volatile boolean zajete = false;
    private Pole poczatek;
    private Pole koniec;
    ArrayList<Pole> polaWspolnejDrogi;

    public WspolnaDroga(Pole poczatek, Pole koniec) {
        this.poczatek = poczatek;
        this.koniec = koniec;
        polaWspolnejDrogi = new ArrayList<>();
        dodajPola();
    }

    public synchronized boolean nieudaneZajecie(WspolnaDroga nastepna, WspolnaDroga inna) {
        if (zajete == false) {
            if (this == nastepna) {
                if (inna.zajete == false) {
                    zajete = true;
                    inna.zajete = true;
                    return false;
                } else {
                    return true;
                }

            } else {
                if (nastepna.zajete == false && inna.zajete == false) {
                    zajete = true;
                    return false;
                } else if (nastepna.zajete == false || inna.zajete == false) {
                    zajete = true;
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }
    }

    public synchronized boolean isZajete() {
        return zajete;
    }

    public synchronized void zejscie() {
        zajete = false;
    }

    public int ile() {
        if (poczatek.getX() == koniec.getX())
            return ((Math.abs(koniec.getY() - poczatek.getY())) / Pole.getWysokosc());
        else return ((Math.abs(koniec.getX() - poczatek.getX())) / Pole.getSzerokosc());
    }

    public void dodajPola() {
        if (poczatek.getX() == koniec.getX()) {
            for (int i = 0; i <= ile(); i++) {
                polaWspolnejDrogi.add(new Pole(poczatek.getX(), poczatek.getY() + i * Pole.getWysokosc(), true, this));
            }
        } else {
            for (int i = 0; i <= ile(); i++) {
                polaWspolnejDrogi.add(new Pole(poczatek.getX() + i * Pole.getSzerokosc(), poczatek.getY(), true, this));
            }
        }

    }
}
