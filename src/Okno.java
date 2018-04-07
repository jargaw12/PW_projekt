import javax.swing.*;

public class Okno extends JFrame {
    public Okno() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gasieniece");
        setIconImage(new ImageIcon("g100.png").getImage());
        setSize(290, 520);
        setResizable(false);
        Plansza plansza = new Plansza();
        add(plansza);
        setVisible(true);
    }
}
