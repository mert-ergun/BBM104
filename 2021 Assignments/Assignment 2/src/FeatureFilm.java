class FeatureFilm extends Film{

    String[] genre;
    String release_date;
    String[] writers;
    String budget;
    String type = "FeatureFilm";

    FeatureFilm (String id, String title, String language, String[] directors, String length,
                 String country, String[] performers, String[] genre, String release_date,
                 String[] writers, String budget) {

        super(id, title, language, directors, length, country, performers);
        super.type = type;
        this.genre = genre;
        this.release_date = release_date;
        this.writers = writers;
        this.budget = budget;
    }
}
