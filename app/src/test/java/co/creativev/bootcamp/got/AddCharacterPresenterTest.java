package co.creativev.bootcamp.got;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddCharacterPresenterTest {
    @Test
    public void testShouldShowNameErrorIfNameIsEmpty() throws Exception {
        DatabaseHelper mockDatabaseHelper = mock(DatabaseHelper.class);
        AddCharacterView mockView = mock(AddCharacterView.class);
        AddCharacterPresenter presenter = new AddCharacterPresenter(mockView, mockDatabaseHelper);

        presenter.addCharacter("", "file://tmp.jpg", 1);

        verify(mockView).setError("Cannot be empty");
    }
}