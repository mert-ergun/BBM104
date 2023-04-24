public abstract class Member {
    private int id;
    private static int nextId = 1;

    public Member() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Member.nextId = nextId;
    }
}
