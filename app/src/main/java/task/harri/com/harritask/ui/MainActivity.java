package task.harri.com.harritask.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import task.harri.com.harritask.Data.Constants;
import task.harri.com.harritask.Data.ServiceApi;
import task.harri.com.harritask.HarriTaskApplication;
import task.harri.com.harritask.R;
import task.harri.com.harritask.adapters.CountriesAdapter;
import task.harri.com.harritask.di.components.ApplicationComponent;
import task.harri.com.harritask.di.components.DaggerMainActivityComponent;
import task.harri.com.harritask.di.components.MainActivityComponent;
import task.harri.com.harritask.di.modules.MainActivityContextModule;
import task.harri.com.harritask.di.qualifiers.ActivityContext;
import task.harri.com.harritask.di.qualifiers.ApplicationContext;
import task.harri.com.harritask.models.Country;
import task.harri.com.harritask.models.CountryWeather;
import task.harri.com.harritask.retrofit.APIsInterface;
import task.harri.com.harritask.ui.fragments.CountryDetailsFragment;
import task.harri.com.harritask.ui.fragments.CountryWeatherFragment;
import task.harri.com.harritask.ui.fragments.CountryWeathersTabsFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private RecyclerView countriesListView;
    private CountryDetailsFragment countryDetailsFragment;
    private MainActivityComponent mainActivityComponent;

    @Inject
    public CountriesAdapter recyclerViewAdapter;

    @Inject
    public APIsInterface apisInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;
    private CountryWeathersTabsFragment countryWeathersTabsFragment;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openCountryDetailsFragment();
        openCountryWeathersFragment();

        initView();

        ApplicationComponent applicationComponent =  HarriTaskApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();
        mainActivityComponent.injectMainActivity(this);
    }

    private void populateRecyclerview(List<Country> countryList) {
        CountriesAdapter countriesAdapter = new CountriesAdapter(this);
        countriesAdapter.setCountriesList(countryList);
        countriesListView.setAdapter(countriesAdapter);
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.loading_countries_message));
        dialog.setCancelable(false);


        mDrawerLayout = findViewById(R.id.drawer_layout);
        countriesListView = (RecyclerView) findViewById(R.id.countriesList);
        countriesListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                dialog.show();
                getAllCountries();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllCountries() {
        apisInterface.getAllCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                dialog.dismiss();
                populateRecyclerview(response.body());
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

    public void updateCountryFragmentDetails(Country country) {
        mDrawerLayout.closeDrawers();
        dialog.setMessage(getString(R.string.loading_weather_details_message));
        dialog.show();
        apisInterface.getCountryWeathers(ServiceApi.WEATHER_BASE_URL,country.getLatlng().get(1)+"",country.getLatlng().get(0)+"",Constants.WEATHER_KEY).enqueue(new Callback<CountryWeather>() {
            @Override
            public void onResponse(Call<CountryWeather> call, Response<CountryWeather> response) {
                CountryWeather countryWeather = response.body();
                countryWeathersTabsFragment.updateWeatherFragments(countryWeather);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<CountryWeather> call, Throwable t) {
                dialog.dismiss();
            }
        });
        countryDetailsFragment.updateCountry(country);
    }

    private void openCountryDetailsFragment() {
        countryDetailsFragment = new CountryDetailsFragment();

        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();

        String backStateName = CountryDetailsFragment.class.toString();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentsContainer, countryDetailsFragment, backStateName);
            fragmentTransaction.commit();

        }
    }

    private void openCountryWeathersFragment() {
        countryWeathersTabsFragment = new CountryWeathersTabsFragment();

        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();

        String backStateName = CountryWeathersTabsFragment.class.toString();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.weathersContainer, countryWeathersTabsFragment, backStateName);
            fragmentTransaction.commit();

        }
    }
}
