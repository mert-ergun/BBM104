class TVSeries extends Film{

    String[] genre;
    String[] writers;
    String start_date;
    String end_date;
    String seasons;
    String episodes;
    String type = "TVSeries";

    TVSeries (String id, String title, String language, String[] directors, String length,
                 String country, String[] performers, String[] genre,
                 String[] writers, String start_date, String end_date, String seasons, String episodes) {

        super(id, title, language, directors, length, country, performers);
        super.type = type;
        this.genre = genre;
        this.writers = writers;
        this.start_date = start_date;
        this.end_date = end_date;
        this.seasons = seasons;
        this.episodes = episodes;
    }
}