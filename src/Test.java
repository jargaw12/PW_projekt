import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Podaj liczbe czerwonych gasienic od 0 do 2");
        String text1 = input.nextLine();
        Plansza.liczbaCzerwonych = Integer.parseInt(text1);

        System.out.println("Podaj liczbe niebieskich gasienic od 0 do 1");
        String text2 = input.nextLine();
        Plansza.liczbaNiebieskich = Integer.parseInt(text2);

        System.out.println("Podaj liczbe zielonych gasienic od 0 do 3");
        String text3 = input.nextLine();
        Plansza.liczbaZielonych = Integer.parseInt(text3);
        new Okno();
    }
}
