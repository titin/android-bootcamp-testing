package co.creativev.bootcamp.got;

import android.graphics.Color;

public class GoTCharacterViewModel {
    public GoTCharacter gotCharacter;

    public GoTCharacterViewModel(GoTCharacter gotCharacter) {
        this.gotCharacter = gotCharacter;
    }

    public String name() {
        return gotCharacter.firstName + " " + gotCharacter.lastName;
    }

    public int color() {
        int color;
        if (gotCharacter.alive)
            color = Color.GREEN;
        else
            color = Color.RED;
        return color;
    }

    public String thumbUrl() {
        return gotCharacter.thumbUrl;
    }
}
