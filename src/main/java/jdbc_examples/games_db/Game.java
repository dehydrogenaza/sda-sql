package jdbc_examples.games_db;

public class Game {
    private int id;
    private final String title;
    private final int price;
    private final String platform;

    public Game(String title, int price, String platform) {
        this.title = title;
        this.price = price;
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getPlatform() {
        return platform;
    }

    public String toQuery(String template) {
        return template.formatted(title, price, platform);
    }
}
