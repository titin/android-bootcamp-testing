package co.creativev.bootcamp.got;

import android.support.annotation.Nullable;
import android.widget.EditText;

public interface AddCharacterView {
    void initView();

    void inputErrorMessage(String message);

    void errorAlert(String message);

    void openGallery();

    void closeActivity();

    void onDbError();
}
