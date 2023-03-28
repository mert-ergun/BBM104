class Actor extends Performers{

    String height;
    String type = "Actor";

    Actor (String id, String name, String surname, String country, String height) {
        super(id, name, surname, country);
        super.type = this.type;
        this.height = height;
    }
}
