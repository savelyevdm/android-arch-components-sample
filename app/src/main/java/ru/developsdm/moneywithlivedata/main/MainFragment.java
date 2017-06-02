package ru.developsdm.moneywithlivedata.main;

import static com.google.common.base.Preconditions.*;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import ru.developsdm.moneywithlivedata.di.component.DaggerMainComponent;
import ru.developsdm.moneywithlivedata.di.component.MainComponent;
import ru.developsdm.moneywithlivedata.exchange.ExchangeActivity;
import timber.log.Timber;

/**
 * Created by Daniil Savelyev in 2017.
 */

@SuppressWarnings("ResultOfMethodCallIgnored")
public class MainFragment extends LifecycleFragment {

    /**
     * Application component
     */
    MainViewModel viewModel;

    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvDeposit1)
    TextView tvDeposit1;
    @BindView(R.id.tvDeposit2)
    TextView tvDeposit2;

    @BindView(R.id.btnExchange)
    Button btnExchange;

    Unbinder viewsUnbinder;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ApplicationComponent applicationComponent = ((MoneyApp) getActivity().getApplication()).getApplicationComponent();
        MainComponent component = DaggerMainComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        component.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        viewModel.init();

        viewModel.getActiveAccount().observe(this, activeAccount -> {
            checkNotNull(activeAccount);
            Timber.d("Receives account: " + activeAccount.toString());
            // update UI
            tvUserName.setText(activeAccount.getUser());
            tvDeposit1.setText(String.valueOf(activeAccount.getFirstDeposit()));
            tvDeposit2.setText(String.valueOf(activeAccount.getSecondDeposit()));
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.d("Creates main fragment");
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        viewsUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        Timber.d("Destroys main fragment");
        viewsUnbinder.unbind();
        super.onDestroyView();
    }

    @OnClick(R.id.btnExchange)
    void startExchange() {
        Intent intent = new Intent(this.getActivity(), ExchangeActivity.class);
        startActivity(intent);
    }

}
