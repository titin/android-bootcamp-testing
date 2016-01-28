package co.creativev.bootcamp.got;

import android.app.Application;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import co.creativev.bootcamp.got.deps.AppModule;
import co.creativev.bootcamp.got.deps.DaggerGoTDeps;
import co.creativev.bootcamp.got.deps.GoTDeps;

public class GoTApplication extends Application {

    private GoTDeps goTDeps;

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso picasso = new Picasso.Builder(this)
                .indicatorsEnabled(BuildConfig.DEBUG)
                .loggingEnabled(BuildConfig.DEBUG)
                .memoryCache(new LruCache(1024 * 1024 * 30)) // 30 MB
                .build();
        Picasso.setSingletonInstance(picasso);
        goTDeps = DaggerGoTDeps.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public GoTDeps getGoTDeps() {
        return goTDeps;
    }
}
