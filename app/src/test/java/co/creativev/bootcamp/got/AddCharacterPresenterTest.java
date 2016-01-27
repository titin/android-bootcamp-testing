package co.creativev.bootcamp.got;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class AddCharacterPresenterTest {
    @Mock
    AddCharacterView addCharacterView;
    @Mock
    Context context;
    private AddCharacterPresenter addCharacterPresenter;

    @Before
    public void setUp() throws Exception {
        addCharacterPresenter = new AddCharacterPresenter(context, addCharacterView);
    }

    @Test
    public void testValidatesCharacterInputs() {
        boolean isValid = addCharacterPresenter.validateCharacter(1, "Stark", "file://home");
        assertEquals(true, isValid);
    }

    @Test
    public void testInvalidatesCharacterInputsWhenNoHouseName() {
        addCharacterPresenter.validateCharacter(1, "", "file://home");
        verify(addCharacterView).inputErrorMessage("Cannot be empty");
    }

    @Test
    public void testInvalidatesCharacterInputsWhenSelectedHouseIsInvalid() {
        addCharacterPresenter.validateCharacter(-1, "Stark", "file://home");
        verify(addCharacterView).errorAlert("House is not selected");
    }

    @Test
    public void testInvalidatesCharacterInputsWhenNoImagePath() {
        addCharacterPresenter.validateCharacter(1, "Stark", null);
        verify(addCharacterView).errorAlert("Image is not selected");
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(addCharacterView);
    }
}