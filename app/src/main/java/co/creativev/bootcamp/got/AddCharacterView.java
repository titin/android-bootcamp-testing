package co.creativev.bootcamp.got;

public interface AddCharacterView {
    void setError(String message);

    void showAlertMessage(String message);

    void showDbInsertError();

    void addCharacterSuccess();
}
