package co.creativev.bootcamp.got;

import javax.inject.Singleton;

import co.creativev.bootcamp.got.deps.AddCharacterModule;
import co.creativev.bootcamp.got.deps.AppModule;
import co.creativev.bootcamp.got.deps.DbModule;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DbModule.class, AddCharacterModule.class})
public interface TestComponentDeps {
    public void inject(TestPresenter presenter);
}