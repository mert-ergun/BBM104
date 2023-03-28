class Director extends Artist{

    String agent;
    String type = "Director";

    Director(String id, String name, String surname, String country, String agent) {
        super(id, name, surname, country);
        super.type = this.type;
        this.agent = agent;
    }
}
