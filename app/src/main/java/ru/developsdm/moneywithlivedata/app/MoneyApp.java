package ru.developsdm.moneywithlivedata.app;

import android.app.Application;

import ru.developsdm.moneywithlivedata.BuildConfig;
import ru.developsdm.moneywithlivedata.di.component.ApplicationComponent;
import ru.developsdm.moneywithlivedata.di.component.DaggerApplicationComponent;
import ru.developsdm.moneywithlivedata.di.module.ApplicationModule;
import timber.log.Timber;

/**
 * Android custom application class.
 * <p>
 * Created by Daniil Savelyev in 2017.
 */

public class MoneyApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize Timber
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                return super.createStackElementTag(element) + ':' + element.getLineNumber();
            }
        });

        // initialize Dagger 2.
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
