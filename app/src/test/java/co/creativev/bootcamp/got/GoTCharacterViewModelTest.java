package co.creativev.bootcamp.got;

import android.graphics.Color;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoTCharacterViewModelTest {
    @Test
    public void testJoinsTheName() throws Exception {
        GoTCharacter gotCharacter = new GoTCharacter("Sansa", "Stark", "", true, "", 0, "", "");
        GoTCharacterViewModel gotCharacterViewModel = new GoTCharacterViewModel(gotCharacter);
        assertEquals("Sansa Stark", gotCharacterViewModel.name());
    }

    @Test
    public void testReturnsRedWhenCharacterIsDead() throws Exception {
        GoTCharacter gotCharacter = new GoTCharacter("", "", "", false, "", 0, "", "");
        GoTCharacterViewModel gotCharacterViewModel = new GoTCharacterViewModel(gotCharacter);
        assertEquals(Color.RED, gotCharacterViewModel.color());
    }

    @Test
    public void testReturnsGreenWhenCharacterIsAlive() throws Exception {
        GoTCharacter gotCharacter = new GoTCharacter("", "", "", true, "", 0, "", "");
        GoTCharacterViewModel gotCharacterViewModel = new GoTCharacterViewModel(gotCharacter);
        assertEquals(Color.GREEN, gotCharacterViewModel.color());
    }
}