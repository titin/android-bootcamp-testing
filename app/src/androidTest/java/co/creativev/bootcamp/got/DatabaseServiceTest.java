package co.creativev.bootcamp.got;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.creativev.bootcamp.got.deps.AppModule;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DatabaseServiceTest {

    private DatabaseService databaseService;

    @Before
    public void setUp() throws Exception {
        TestPresenter presenter = new TestPresenter();
        DaggerTestComponentDeps.builder()
                .appModule(new AppModule(InstrumentationRegistry.getTargetContext()))
                .build()
                .inject(presenter);
        databaseService = presenter.databaseService;
    }

    @Test
    public void testAddCharacterAddsCharacterToDatabase() throws Exception {
        String name = "Ned";
        String image = "file://temp/ned.jpg";
        long id = databaseService.insertInDb(R.id.radio_stark, name, image);
        assertThat(databaseService.count(), is(29));
        GoTCharacter goTCharacter = databaseService.getGoTCharacter(id);
        assertThat(goTCharacter, is(new GoTCharacter(((int) id), name, "Unknown", image, image, true, "New", R.id.radio_stark, "Lorem")));
    }

    @After
    public void tearDown() throws Exception {
        databaseService.reset();
    }
}