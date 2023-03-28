import java.util.ArrayList;

public class PersonalTrainer extends Person {

    Member[] members = new Member[0];
    String sportType;

    PersonalTrainer(int id, String name, String surname, String sportType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sportType = sportType;
    }

    public void addMember (Member m) {

        Member[] new_members = new Member[members.length + 1];

        for (int i = 0; i < members.length; i++) {
            new_members[i] = members[i];
        }

        new_members[new_members.length - 1] = m;
        members = new_members;
    }

    public int returnCountofMembers() {
        return members.length;
    }

    public Member returnMember (int memberID) {
        Member m = null;

        for (int i = 0; i < members.length; i++) {
            if (members[i].getId() == memberID)
                m = members[i];
        }

        return m;
    }

    public Member ReturnFattestMember() {
        Member m = null;
        double w = 0;

        for (int i = 0; i < members.length; i++) {
            if (members[i].getWeight() > w) {
                w = members[i].getWeight();
                m = members[i];
            }
        }

        return m;
    }
}
