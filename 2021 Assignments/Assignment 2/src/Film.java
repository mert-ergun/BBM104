class Film {

    String id;
    String title;
    String language;
    String[] directors;
    String length;
    String country;
    String[] performers;
    String type;

    Film (String id, String title, String language, String[] directors, String length, String country, String[] performers) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.directors = directors;
        this.length = length;
        this.country = country;
        this.performers = performers;
    }
}
