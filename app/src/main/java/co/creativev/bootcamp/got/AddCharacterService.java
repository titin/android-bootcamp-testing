package co.creativev.bootcamp.got;

public class AddCharacterService {
    private final DatabaseHelper databaseHelper;

    public AddCharacterService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertInDb(int houseResId, String name, String imagePath) {
        String[] names = name.split(" ");
        String firstName = names[0];
        String lastName;
        if (names.length > 1) {
            lastName = name.substring(name.indexOf(" "));
        } else {
            lastName = "Unknown";
        }
        return databaseHelper.insert(new GoTCharacter(firstName, lastName, imagePath, true, "New", houseResId, "Lorem", imagePath));
    }
}
