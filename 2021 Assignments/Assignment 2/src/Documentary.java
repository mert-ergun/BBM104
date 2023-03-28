class Documentary extends Film{

    String release_date;
    String type = "Documentary";

    Documentary (String id, String title, String language, String[] directors, String length,
                 String country, String[] performers, String release_date) {

        super(id, title, language, directors, length, country, performers);
        super.type = type;
        this.release_date = release_date;
    }
}
