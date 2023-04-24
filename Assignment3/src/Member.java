/**
 * The Member abstract class represents a member in a system.
 * Each member has a unique ID assigned to them upon creation.
 * The ID is automatically incremented for each new member.
 * There will never be two members with the same ID.
 */
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
