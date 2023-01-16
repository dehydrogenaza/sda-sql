package movies_db.movie;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "productionYear")
    private int productionYear;
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(name = "rating")
    private double rating;

    public Movie() {} //required by Hibernate

    private Movie(String title, int productionYear, Genre genre, double rating) {
        this.title = title;
        this.productionYear = productionYear;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title + " (" + productionYear + "), " + genre + "\t- ocena: " + rating;
    }

    public static class Builder {
        public static final int MIN_YEAR = 1800;
        public static final int MAX_YEAR = 2100;
        private String title;
        private int productionYear;
        private Genre genre;
        private double rating = 5.0;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withProductionYear(int productionYear) throws IllegalArgumentException {
            if (checkYear(productionYear)) {
                this.productionYear = productionYear;
            } else throw new IllegalArgumentException("Rok produkcji poza zakresem " + MIN_YEAR + " - " + MAX_YEAR);
            return this;
        }

        public Builder withGenre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Builder withRating(double rating) {
            this.rating = rating;
            return this;
        }

        public Movie create() {
            if (this.title == null || this.title.isBlank()) throw new IllegalStateException("Nie można stworzyć " +
                    "filmu bez nazwy");
            if (!checkYear(productionYear)) throw new IllegalStateException("Nie można stworzyć filmu bez roku " +
                    "produkcji");
            return new Movie(title, productionYear, genre, rating);
        }

        private boolean checkYear(int year) {
            return year >= MIN_YEAR && year <= MAX_YEAR;
        }
    }
}