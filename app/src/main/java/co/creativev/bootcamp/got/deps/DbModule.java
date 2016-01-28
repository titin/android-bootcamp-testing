package co.creativev.bootcamp.got.deps;

import android.content.Context;

import javax.inject.Singleton;

import co.creativev.bootcamp.got.DatabaseHelper;
import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {
    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public DatabaseHelper providesDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }
}
