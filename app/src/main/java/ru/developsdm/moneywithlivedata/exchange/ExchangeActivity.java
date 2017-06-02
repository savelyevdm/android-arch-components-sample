package ru.developsdm.moneywithlivedata.exchange;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.developsdm.moneywithlivedata.R;
import ru.developsdm.moneywithlivedata.app.MoneyApp;
import ru.developsdm.moneywithlivedata.di.component.ApplicationComponent;
import ru.developsdm.moneywithlivedata.di.component.DaggerExchangeComponent;
import ru.developsdm.moneywithlivedata.di.component.DaggerMainComponent;
import ru.developsdm.moneywithlivedata.di.component.ExchangeComponent;
import ru.developsdm.moneywithlivedata.di.component.MainComponent;
import ru.developsdm.moneywithlivedata.main.MainFragment;
import ru.developsdm.moneywithlivedata.main.MainViewModel;

/**
 * Created by Daniil Savelyev in 2017.
 */

public class ExchangeActivity extends AppCompatActivity {

    public static final String EXCHANGE_FRAGMENT_TAG = "EXCHANGE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Unbinder viewsUnbinder;

    /**
     * Application component.
     */
    ExchangeViewModel viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        viewsUnbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState == null) {
            ExchangeFragment exchangeFragment = ExchangeFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layContent, exchangeFragment, EXCHANGE_FRAGMENT_TAG).commit();
        }

        // To perform actions from toolbar - we inject ViewModel here.

        ApplicationComponent applicationComponent = ((MoneyApp) getApplication()).getApplicationComponent();
        ExchangeComponent component = DaggerExchangeComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        component.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExchangeViewModel.class);
        viewModel.init();
    }

    @Override
    protected void onDestroy() {
        viewsUnbinder.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                customBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        customBackPressed();
    }

    private void customBackPressed() {
        super.onBackPressed();
    }
}
