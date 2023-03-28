import java.util.ArrayList;

public class SportCenter {

    String name;
    PersonalTrainer[] PTs = new PersonalTrainer[0];

    SportCenter(String name) {
        this.name = name;
    }

    public void addPT(PersonalTrainer pt) {

        PersonalTrainer[] new_PTs = new PersonalTrainer[PTs.length + 1];

        for (int i = 0; i < PTs.length; i++) {
            new_PTs[i] = PTs[i];
        }

        new_PTs[new_PTs.length - 1] = pt;
        PTs = new_PTs;
    }

    public PersonalTrainer searchPT (String name, String surname) {
        PersonalTrainer pt = null;

        for (int i = 0; i < PTs.length; i++) {

            if (PTs[i].getName() == name && PTs[i].getSurname() == surname) {
                pt = PTs[i];
                break;
            }
        }
        return pt;
    }
}
