import java.util.LinkedHashMap;

class User extends People{

    LinkedHashMap<String, Integer> ratings = new LinkedHashMap<String, Integer>();

    User(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }
}