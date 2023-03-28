class ChildActor extends Performers{

    String age;
    String type = "ChildActor";

    ChildActor(String id, String name, String surname, String country, String age) {
        super(id, name, surname, country);
        super.type = this.type;
        this.age = age;
    }
}
