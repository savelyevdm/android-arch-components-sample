package ru.developsdm.moneywithlivedata.main;

import static com.google.common.base.Preconditions.*;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import javax.inject.Inject;

import ru.developsdm.moneywithlivedata.model.Account;
import ru.developsdm.moneywithlivedata.model.AccountRepository;

/**
 * Created by Daniil Savelyev in 2017.
 */

public class MainViewModel extends ViewModel {

    /**
     * Flag to check if ViewModel is initialized.
     */
    private boolean isInitialized = false;
    /**
     * Observable account from repository.
     */
    private LiveData<Account> activeAccount;

    /**
     * Repository instance.
     */
    private AccountRepository repository;

    public MainViewModel() {
    }

    public void init() {

        if (isInitialized)
            return;

        // do some init stuff, if needed.

        activeAccount = this.repository.getActiveAccount();
        isInitialized = true;
    }

    public LiveData<Account> getActiveAccount() {
        return this.activeAccount;
    }

    public void initRepository(@NonNull AccountRepository repository) {
        if (this.repository == null) {
            this.repository = checkNotNull(repository);
        }
    }

}
