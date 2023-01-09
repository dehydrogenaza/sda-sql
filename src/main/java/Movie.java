public class Movie {
    private final String title;
    private final int productionYear;
    private final Genre genre;
    private final double rating;

    Movie(String title, int productionYear, Genre genre, double rating) {
        this.title = title;
        this.productionYear = productionYear;
        this.genre = genre;
        this.rating = rating;
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