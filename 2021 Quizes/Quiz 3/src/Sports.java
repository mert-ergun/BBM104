class Sports {

    int total_match = 0;
    int winning = 0;
    int tie = 0;
    int losing = 0;
    int goals = 0;
    int against = 0;
    int average = 0;
    int score = 0;
    int award;
    String type;
    private String name;

    Sports (String name) {
        this.name = name;
    }

    String getName () {
        return name;
    }

    void setName (String name) {
        this.name = name;
    }
}
