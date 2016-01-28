package co.creativev.bootcamp.got.deps;

import javax.inject.Singleton;

import co.creativev.bootcamp.got.AddCharacterService;
import co.creativev.bootcamp.got.DatabaseHelper;
import dagger.Module;
import dagger.Provides;

@Module
public class AddCharacterModule {
    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public AddCharacterService providesAddCharacterService(DatabaseHelper databaseHelper) {
        return new AddCharacterService(databaseHelper);
    }
}
