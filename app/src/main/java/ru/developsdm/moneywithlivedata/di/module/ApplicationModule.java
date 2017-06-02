package ru.developsdm.moneywithlivedata.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;
import ru.developsdm.moneywithlivedata.app.MoneyApp;
import ru.developsdm.moneywithlivedata.di.scope.PerApplication;
import ru.developsdm.moneywithlivedata.exchange.ExchangeViewModel;
import ru.developsdm.moneywithlivedata.main.MainViewModel;
import ru.developsdm.moneywithlivedata.model.AccountRepository;

/**
 * Module. Can provide objects for scopes.
 * <p>
 * Created by Daniil Savelyev in 2017.
 */
@Module
public class ApplicationModule {

    private final MoneyApp application;

    public ApplicationModule(MoneyApp application) {
        this.application = application;
    }

    @Provides
    @PerApplication
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @PerApplication
    AccountRepository provideAccountRepository() {
        return new AccountRepository();
    }

    @Provides
    @PerApplication
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @PerApplication
    ViewModelProvider.Factory provideVMFactory(AccountRepository repository) {
        return new ViewModelProvider.Factory() {

            private final String mainViewModelClassName = MainViewModel.class.getCanonicalName();
            private final String exchangeViewModelClassName = ExchangeViewModel.class.getCanonicalName();

            @SuppressWarnings("unchecked")
            @Override
            public <T extends ViewModel> T create(Class<T> modelClass) {

                String wantedClassName = modelClass.getCanonicalName();

                if (wantedClassName.equals(mainViewModelClassName)) {
                    MainViewModel mainViewModel = new MainViewModel();
                    mainViewModel.initRepository(repository);
                    return (T) mainViewModel;
                }
                if (wantedClassName.equals(exchangeViewModelClassName)) {
                    ExchangeViewModel exchangeViewModel = new ExchangeViewModel();
                    exchangeViewModel.initRepository(repository);
                    return (T) exchangeViewModel;
                }
                return null;
            }
        };
    }

}
