package co.creativev.bootcamp.got;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoTCharacterViewModelTest {
    @Test
    public void testJoinsTheName() throws Exception {
        GoTCharacter gotCharacter = new GoTCharacter("Sansa", "Stark", "", true, "", 0, "", "");
        GoTCharacterViewModel gotCharacterViewModel = new GoTCharacterViewModel(gotCharacter);
        assertEquals("Sansa Stark", gotCharacterViewModel.name());
    }
}