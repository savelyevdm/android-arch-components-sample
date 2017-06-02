package ru.developsdm.moneywithlivedata.exchange;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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
import ru.developsdm.moneywithlivedata.di.component.DaggerExchangeComponent;
import ru.developsdm.moneywithlivedata.di.component.DaggerMainComponent;
import ru.developsdm.moneywithlivedata.di.component.ExchangeComponent;
import ru.developsdm.moneywithlivedata.di.component.MainComponent;
import ru.developsdm.moneywithlivedata.main.MainViewModel;
import timber.log.Timber;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Daniil Savelyev in 2017.
 */

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ExchangeFragment extends LifecycleFragment {

    /**
     * Application component.
     */
    ExchangeViewModel viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.tvDeposit1)
    TextView tvDeposit1;
    @BindView(R.id.tvDeposit2)
    TextView tvDeposit2;

    Unbinder viewsUnbinder;

    public static ExchangeFragment newInstance() {

        Bundle args = new Bundle();

        ExchangeFragment fragment = new ExchangeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ApplicationComponent applicationComponent = ((MoneyApp) getActivity().getApplication()).getApplicationComponent();
        ExchangeComponent component = DaggerExchangeComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        component.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExchangeViewModel.class);
        viewModel.init();

        viewModel.getObservableExchangeState().observe(this, exchangeState -> {
            checkNotNull(exchangeState);
            Timber.d("Receives exchange state: " + exchangeState.toString());
            // update UI
            tvDeposit1.setText(String.valueOf(exchangeState.getFirstDeposit()));
            tvDeposit2.setText(String.valueOf(exchangeState.getSecondDeposit()));
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.d("Creates exchange fragment");
        View view = inflater.inflate(R.layout.fragment_exchange, container, false);
        viewsUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        Timber.d("Destroys exchange fragment");
        viewsUnbinder.unbind();
        super.onDestroyView();
    }

    @OnClick(R.id.btnDec)
    void decAccount() {
        viewModel.doDec();
    }

    @OnClick(R.id.btnInc)
    void incAccount() {
        viewModel.doInc();
    }

    @OnClick(R.id.btnAccept)
    void doAccept() {
        viewModel.acceptExchange();
        // FIXME: not optimal solution (as it contains nav logic in UI) - must be moved to ViewModel
        getActivity().finish();
    }

}
