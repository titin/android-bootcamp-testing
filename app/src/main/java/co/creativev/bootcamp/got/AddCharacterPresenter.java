package co.creativev.bootcamp.got;

import javax.inject.Inject;

public class AddCharacterPresenter {
    @Inject
    AddCharacterService addCharacterService;
    private final AddCharacterView addCharacterView;

    public AddCharacterPresenter(AddCharacterView addCharacterView) {
        this.addCharacterView = addCharacterView;
    }

    public void setAddCharacterService(AddCharacterService addCharacterService) {
        this.addCharacterService = addCharacterService;
    }

    public void initView() {
        addCharacterView.initView();
    }

    public void openGallery() {
        addCharacterView.openGallery();
    }

    public void addCharacter(int selectedHouse, String name, String imagePath) {
        if (!validateCharacter(selectedHouse, name, imagePath))
            return;

        long id = addCharacterService.insertInDb(getHouseResId(selectedHouse), name, imagePath);
        if (id == -1) {
            addCharacterView.onDbError();
        } else {
            addCharacterView.closeActivity();
        }
    }

    public boolean validateCharacter(int selectedHouse, String name, String imagePath) {
        if (name.isEmpty()) {
            addCharacterView.inputErrorMessage("Cannot be empty");
            return false;
        }
        if (imagePath == null) {
            addCharacterView.errorAlert("Image is not selected");
            return false;
        }
        if (selectedHouse == -1) {
            addCharacterView.errorAlert("House is not selected");
            return false;
        }
        return true;
    }

    public int getHouseResId(int radioButtonId) {
        switch (radioButtonId) {
            case R.id.radio_baratheon:
                return R.drawable.baratheon;
            case R.id.radio_lannister:
                return R.drawable.lannister;
            case R.id.radio_stark:
                return R.drawable.stark;
            case R.id.radio_targaryen:
                return R.drawable.targaryen;
            default:
                throw new IllegalArgumentException("No icon found for radio button " + radioButtonId);
        }
    }
}
