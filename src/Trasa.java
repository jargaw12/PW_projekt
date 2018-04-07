import java.util.ArrayList;

public class Trasa {
    private Pole pktStartowy;
    private int lPoziomych;
    private int lPionowych;
    private ArrayList<Pole> polaTrasy;
    private volatile WspolnaDroga wspolnaDroga1;
    private volatile WspolnaDroga wspolnaDroga2;
    private volatile WspolnaDroga wspolnaDroga3;
    private volatile WspolnaDroga wspolnaDrogaInna;


    public Trasa(int lPoziomych, int lPionowych, Pole pktStartowy, WspolnaDroga wspolnaDroga1, WspolnaDroga wspolnaDroga2, WspolnaDroga wspolnaDroga3, WspolnaDroga wspolnaDrogaInna) {
        this.lPoziomych = lPoziomych;
        this.lPionowych = lPionowych;
        this.pktStartowy = pktStartowy;
        polaTrasy = new ArrayList<>();
        this.wspolnaDroga1 = wspolnaDroga1;
        this.wspolnaDroga2 = wspolnaDroga2;
        this.wspolnaDroga3 = wspolnaDroga3;
        this.wspolnaDrogaInna = wspolnaDrogaInna;
    }

    public void dodajPolaCzerwone() {
        for (int i = 1; i < lPoziomych - 1; i++) {
            polaTrasy.add(new Pole(pktStartowy.getX() + i * Pole.getSzerokosc(), pktStartowy.getY()));
        }
        polaTrasy.addAll(wspolnaDroga1.polaWspolnejDrogi);
        polaTrasy.add(wspolnaDroga2.polaWspolnejDrogi.get(0));

        for (int i = wspolnaDroga3.polaWspolnejDrogi.size() - 1; i >= 0; i--) {
            polaTrasy.add(wspolnaDroga3.polaWspolnejDrogi.get(i));
        }
        for (int i = 1; i >= 0; i--) {
            polaTrasy.add(new Pole(i * Pole.getSzerokosc(), (lPionowych - 1) * Pole.getWysokosc()));
        }
        for (int i = lPionowych - 2; i >= 0; i--) {
            polaTrasy.add(new Pole(pktStartowy.getX(), pktStartowy.getY() + i * Pole.getWysokosc()));
        }
    }

    public void dodajPolaNiebieskie() {
        for (int i = 1; i < lPoziomych; i++) {
            polaTrasy.add(new Pole(pktStartowy.getX() + i * Pole.getSzerokosc(), pktStartowy.getY()));
        }
        for (int i = 1; i < lPionowych - 1; i++) {
            polaTrasy.add(new Pole(pktStartowy.getX() + (lPoziomych - 1) * Pole.getSzerokosc(), pktStartowy.getY() + i * Pole.getWysokosc()));
        }
        for (int i = lPoziomych - 1; i >= lPoziomych - 2; i--) {
            polaTrasy.add(new Pole(pktStartowy.getX() + i * Pole.getSzerokosc(), pktStartowy.getY() + (lPionowych - 1) * Pole.getWysokosc()));
        }

        for (int i = wspolnaDroga1.polaWspolnejDrogi.size() - 1; i >= 0; i--) {
            polaTrasy.add(wspolnaDroga1.polaWspolnejDrogi.get(i));
        }
        polaTrasy.add(wspolnaDroga2.polaWspolnejDrogi.get(0));
        for (int i = wspolnaDroga3.polaWspolnejDrogi.size() - 1; i >= 0; i--) {
            polaTrasy.add(wspolnaDroga3.polaWspolnejDrogi.get(i));
        }
    }

    public void dodajPolaZielone() {
        for (int i = 0; i <= wspolnaDroga1.polaWspolnejDrogi.size() - 1; i++) {
            polaTrasy.add(wspolnaDroga1.polaWspolnejDrogi.get(i));
        }

        polaTrasy.add(wspolnaDroga2.polaWspolnejDrogi.get(0));
        for (int i = 0; i <= wspolnaDroga3.polaWspolnejDrogi.size() - 1; i++) {
            polaTrasy.add(wspolnaDroga3.polaWspolnejDrogi.get(i));
        }
        for (int i = 1; i < lPionowych - 1; i++) {
            polaTrasy.add(new Pole(pktStartowy.getX() + (lPoziomych - 1) * Pole.getSzerokosc(), pktStartowy.getY() + i * Pole.getWysokosc()));
        }
        for (int i = lPoziomych - 1; i >= 0; i--) {
            polaTrasy.add(new Pole(pktStartowy.getX() + i * Pole.getSzerokosc(), pktStartowy.getY() + (lPionowych - 1) * Pole.getWysokosc()));
        }
        for (int i = lPionowych - 2; i > 0; i--) {
            polaTrasy.add(new Pole(pktStartowy.getX(), pktStartowy.getY() + i * Pole.getWysokosc()));
        }
    }

    public WspolnaDroga getWspolnaDroga1() {
        return wspolnaDroga1;
    }

    public WspolnaDroga getWspolnaDroga2() {
        return wspolnaDroga2;
    }

    public WspolnaDroga getWspolnaDroga3() {
        return wspolnaDroga3;
    }

    public WspolnaDroga getWspolnaDrogaInna() {
        return wspolnaDrogaInna;
    }

    public Pole getPktStartowy() {
        return pktStartowy;
    }

    public int getlPoziomych() {
        return lPoziomych;
    }

    public int getlPionowych() {
        return lPionowych;
    }

    public ArrayList<Pole> getPolaTrasy() {
        return polaTrasy;
    }

}
