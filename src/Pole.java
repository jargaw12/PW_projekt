public class Pole {
    private int x;
    private int y;
    private static final int szerokosc = 25;
    private static final int wysokosc = 20;
    private volatile boolean zajete;
    private boolean wspoldzielony;
    private WspolnaDroga wspolnaDroga;

    public boolean isZajete() {
        return zajete;
    }

    public void setZajete(boolean zajete) {
        this.zajete = zajete;
    }

    public boolean isWspoldzielony() {
        return wspoldzielony;
    }

    public WspolnaDroga getWspolnaDroga() {
        return wspolnaDroga;
    }

    public Pole(int x, int y, boolean wspoldzielony, WspolnaDroga wspolnaDroga) {
        this.x = x;
        this.y = y;
        this.wspoldzielony = wspoldzielony;
        this.wspolnaDroga = wspolnaDroga;
    }

    public Pole(int x, int y) {
        this.x = x;
        this.y = y;
        this.wspoldzielony = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static int getSzerokosc() {
        return szerokosc;
    }

    public static int getWysokosc() {
        return wysokosc;
    }

}