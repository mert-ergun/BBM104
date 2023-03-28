class Writer extends Artist{

    String writing_type;
    String type = "Writer";

    Writer(String id, String name, String surname, String country, String writing_type) {
        super(id, name, surname, country);
        super.type = this.type;
        this.writing_type = writing_type;
    }
}