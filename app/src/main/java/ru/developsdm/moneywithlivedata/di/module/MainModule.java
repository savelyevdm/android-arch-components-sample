package ru.developsdm.moneywithlivedata.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.developsdm.moneywithlivedata.app.MoneyApp;
import ru.developsdm.moneywithlivedata.di.scope.PerActivity;
import ru.developsdm.moneywithlivedata.di.scope.PerApplication;
import ru.developsdm.moneywithlivedata.main.MainViewModel;
import ru.developsdm.moneywithlivedata.model.AccountRepository;


/**
 * Module. Can provide objects for scopes.
 * <p>
 * Created by Daniil Savelyev in 2017.
 */

@Module
public class MainModule {

}
