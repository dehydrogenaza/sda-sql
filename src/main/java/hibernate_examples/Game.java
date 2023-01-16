package hibernate_examples;

//4. setup klasy Game:
//Przygotuj klasę Game, gry mają mieć takie cechy jak: id, tytuł, cena i platforma (np. PC).
//        jeśli nie masz to przygotuj klasę Game z poprzedniego zadania
//        dodaj do niej adnotację @Entity
//   przy konfiguracji wykorzystaj metodę addAnnotatedClass aby wskazać klasę Game
//           oznacz pole id adnotacją @Id
//   oznacz pole id adnotacją @GeneratedValue
//   udostępnij bezparametrowy konstruktor
//           zadbaj o wskazanie odpowiednich nazw tabeli i kolumn poprzez adnotacje @Table i @Column


import javax.persistence.*;

@Entity
@Table(name = "games_hibernate")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "game_title")
    private String title;

//    @Column(name = "price") //default równy nazwie pola
    private double price;

    @Column(name = "release_platform")
    private String platform;

    public Game() {}

    public Game(String title, double price, String platform) {
        this.title = title;
        this.price = price;
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", platform='" + platform + '\'' +
                '}';
    }
}
