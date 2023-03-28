class StuntPerformer extends Performers{

    String height;
    String[] actors;
    String type = "StuntPerformer";

    StuntPerformer(String id, String name, String surname, String country, String height, String[] actors) {
        super(id, name, surname, country);
        super.type = this.type;
        this.height = height;
        this.actors = actors;
    }
}