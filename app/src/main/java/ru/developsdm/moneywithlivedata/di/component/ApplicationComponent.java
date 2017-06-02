package ru.developsdm.moneywithlivedata.di.component;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Component;
import ru.developsdm.moneywithlivedata.di.module.ApplicationModule;
import ru.developsdm.moneywithlivedata.di.scope.PerApplication;
import ru.developsdm.moneywithlivedata.exchange.ExchangeActivity;
import ru.developsdm.moneywithlivedata.exchange.ExchangeFragment;
import ru.developsdm.moneywithlivedata.main.MainFragment;
import ru.developsdm.moneywithlivedata.model.AccountRepository;

/**
 * Application component.
 * <p/>
 * Created by Daniil Savelyev in 2017.
 */
@PerApplication
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context context();

    AccountRepository accountRepository();

    SharedPreferences sharedPreferences();

    ViewModelProvider.Factory viewModelFactory();

}