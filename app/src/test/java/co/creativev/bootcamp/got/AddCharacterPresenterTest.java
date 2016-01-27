package co.creativev.bootcamp.got;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class AddCharacterPresenterTest {
    @Mock
    AddCharacterView addCharacterView;
    @Mock
    AddCharacterService addCharacterService;
    private AddCharacterPresenter addCharacterPresenter;

    @Before
    public void setUp() throws Exception {
        addCharacterPresenter = new AddCharacterPresenter(addCharacterView, addCharacterService);
    }

    @Test
    public void testAddsCharacterToDatabase() {
        doReturn((long) 1).when(addCharacterService).insertInDb(R.drawable.baratheon, "Stark", "file://home");
        addCharacterPresenter.addCharacter(R.id.radio_baratheon, "Stark", "file://home");
        verify(addCharacterView).closeActivity();
    }

    @Test
    public void testDoesNotAddCharacterOnDatabaseError() {
        doReturn((long) -1).when(addCharacterService).insertInDb(R.drawable.baratheon, "Stark", "file://home");
        addCharacterPresenter.addCharacter(R.id.radio_baratheon, "Stark", "file://home");
        verify(addCharacterView).onDbError();
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