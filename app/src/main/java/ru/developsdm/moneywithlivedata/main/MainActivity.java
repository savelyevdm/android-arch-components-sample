package ru.developsdm.moneywithlivedata.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.developsdm.moneywithlivedata.R;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Unbinder viewsUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.d("Creates main activity.");

        viewsUnbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            MainFragment homeFragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.layContent, homeFragment).commit();
        }
    }

    @Override
    protected void onDestroy() {
        Timber.d("Destroys main activity. finishing flag: " + isFinishing());
        viewsUnbinder.unbind();
        super.onDestroy();
    }

}
