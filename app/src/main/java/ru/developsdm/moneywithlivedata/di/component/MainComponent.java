package ru.developsdm.moneywithlivedata.di.component;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Named;

import dagger.Component;
import ru.developsdm.moneywithlivedata.di.module.MainModule;
import ru.developsdm.moneywithlivedata.di.scope.PerActivity;
import ru.developsdm.moneywithlivedata.main.MainActivity;
import ru.developsdm.moneywithlivedata.main.MainFragment;
import ru.developsdm.moneywithlivedata.main.MainViewModel;


/**
 * Component.
 * <p>
 * Created by Daniil Savelyev in 2017.
 */
@PerActivity
@Component(modules = MainModule.class, dependencies = ApplicationComponent.class)
public interface MainComponent {

    void inject(MainFragment mainFragment);

}
