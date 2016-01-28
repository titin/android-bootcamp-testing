package co.creativev.bootcamp.got.deps;

import javax.inject.Singleton;

import co.creativev.bootcamp.got.AddCharacterPresenter;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DbModule.class, AddCharacterModule.class})
public interface GoTDeps {
    void inject(AddCharacterPresenter addCharacterPresenter);
}
