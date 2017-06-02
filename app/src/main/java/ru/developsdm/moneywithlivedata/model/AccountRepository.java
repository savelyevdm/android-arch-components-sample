package ru.developsdm.moneywithlivedata.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.google.common.base.Preconditions;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import timber.log.Timber;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Daniil Savelyev in 2017.
 */
public class AccountRepository {

    /**
     * Account record, fetched from web, db, elsewhere.
     */
    private Account activeAccount;
    /**
     * Account accountLiveData source.
     */
    private MutableLiveData<Account> accountLiveData;

    public AccountRepository() {
        // Simplification - need to be fetched from web, db, etc.
        this.activeAccount = Account.newInstance("Daniil Savelyev", 1000, 2000);
        this.accountLiveData = new MutableLiveData<>();
        accountLiveData.setValue(activeAccount);
    }

    public LiveData<Account> getActiveAccount() {
        Timber.d("Gets LiveData<Account> from repository");
        if (accountLiveData.getValue() == null)
            updateObservableAccount();
        return accountLiveData;
    }

    public void makeExchange(int newFirstDepositValue, int newSecondDepositValue) {
        checkArgument((activeAccount.getFirstDeposit() + activeAccount.getSecondDeposit()) == (newFirstDepositValue + newSecondDepositValue),
                "Sum of new account deposits values must be equal to sum of previous values.");
        Timber.d("Makes exchange in repository");
        activeAccount.setFirstDeposit(newFirstDepositValue);
        activeAccount.setSecondDeposit(newSecondDepositValue);
        updateObservableAccount();
    }

    private void updateObservableAccount() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            // Current Thread is Main Thread.
            accountLiveData.setValue(activeAccount);
        } else {
            accountLiveData.postValue(activeAccount);
        }
        Timber.d("Changes account to: " + activeAccount.toString());
    }

}
