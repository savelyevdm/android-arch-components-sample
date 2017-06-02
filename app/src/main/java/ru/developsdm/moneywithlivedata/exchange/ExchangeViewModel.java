package ru.developsdm.moneywithlivedata.exchange;

import static com.google.common.base.Preconditions.*;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Looper;
import android.support.annotation.NonNull;

import ru.developsdm.moneywithlivedata.model.Account;
import ru.developsdm.moneywithlivedata.model.AccountRepository;
import timber.log.Timber;

/**
 * Created by Daniil Savelyev in 2017.
 */

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ExchangeViewModel extends ViewModel {

    public static class ExchangeState {

        private int firstDeposit;
        private int secondDeposit;

        private ExchangeState(int firstDeposit, int secondDeposit) {
            this.firstDeposit = firstDeposit;
            this.secondDeposit = secondDeposit;
        }

        public static ExchangeState newInstance(int firstDeposit, int secondDeposit) {
            return new ExchangeState(firstDeposit, secondDeposit);
        }

        public void doExchange(int newFirstDepositValue, int newSecondDepositValue) {
            checkArgument((firstDeposit + secondDeposit) == (newFirstDepositValue + newSecondDepositValue),
                    "Sum of new account deposits values must be equal to sum of previous values.");
            this.firstDeposit = newFirstDepositValue;
            this.secondDeposit = newSecondDepositValue;
        }

        public int getFirstDeposit() {
            return firstDeposit;
        }

        public int getSecondDeposit() {
            return secondDeposit;
        }

        @Override
        public String toString() {
            return "ExchangeState{" +
                    "firstDeposit=" + firstDeposit +
                    ", secondDeposit=" + secondDeposit +
                    '}';
        }
    }

    /**
     * Flag to check if ViewModel is initialized.
     */
    private boolean isInitialized = false;

    /**
     * Current exchange state.
     */
    private ExchangeState exchangeState;

    /**
     * Observable account from repository.
     */
    private LiveData<Account> observableAccount;

    /**
     * Observable exchange state for all Views of this ViewModel.
     */
    private MutableLiveData<ExchangeState> observableExchangeState;

    /**
     * Repository instance.
     */
    private AccountRepository repository;

    public ExchangeViewModel() {
    }

    public void init() {
        // do some init stuff, if needed.

        if (isInitialized)
            return;

        observableAccount = repository.getActiveAccount();

        Account account = observableAccount.getValue();
        checkNotNull(account);
        exchangeState = ExchangeState.newInstance(account.getFirstDeposit(), account.getSecondDeposit());
        observableExchangeState = new MutableLiveData<>();
        updateObservableExchangeState();

        isInitialized = true;
    }

    public LiveData<ExchangeState> getObservableExchangeState() {
        return this.observableExchangeState;
    }

    public void initRepository(@NonNull AccountRepository repository) {
        if (this.repository == null) {
            this.repository = checkNotNull(repository);
        }
    }

    public void doDec() {
        if (exchangeState.getSecondDeposit() < 1)
            return;
        exchangeState.doExchange(exchangeState.getFirstDeposit() + 1, exchangeState.getSecondDeposit() - 1);
        updateObservableExchangeState();
    }

    public void doInc() {
        if (exchangeState.getFirstDeposit() < 1)
            return;
        exchangeState.doExchange(exchangeState.getFirstDeposit() - 1, exchangeState.getSecondDeposit() + 1);
        updateObservableExchangeState();
    }

    public void acceptExchange() {
        this.repository.makeExchange(exchangeState.getFirstDeposit(), exchangeState.getSecondDeposit());
    }

    private void updateObservableExchangeState() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            // Current Thread is Main Thread.
            observableExchangeState.setValue(exchangeState);
        } else {
            observableExchangeState.postValue(exchangeState);
        }
        Timber.d("Changes exchange state to: " + exchangeState.toString());
    }

}
