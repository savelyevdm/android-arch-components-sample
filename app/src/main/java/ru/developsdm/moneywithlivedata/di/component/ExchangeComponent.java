package ru.developsdm.moneywithlivedata.di.component;

import dagger.Component;
import ru.developsdm.moneywithlivedata.di.module.ExchangeModule;
import ru.developsdm.moneywithlivedata.di.module.MainModule;
import ru.developsdm.moneywithlivedata.di.scope.PerActivity;
import ru.developsdm.moneywithlivedata.exchange.ExchangeActivity;
import ru.developsdm.moneywithlivedata.exchange.ExchangeFragment;
import ru.developsdm.moneywithlivedata.main.MainFragment;


/**
 * Component.
 * <p>
 * Created by Daniil Savelyev in 2017.
 */
@PerActivity
@Component(modules = ExchangeModule.class, dependencies = ApplicationComponent.class)
public interface ExchangeComponent {

    void inject(ExchangeActivity exchangeActivity);

    void inject(ExchangeFragment exchangeFragment);

}
