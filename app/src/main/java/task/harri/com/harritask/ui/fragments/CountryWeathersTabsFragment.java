package task.harri.com.harritask.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import task.harri.com.harritask.CountryViewModel;
import task.harri.com.harritask.R;
import task.harri.com.harritask.adapters.CountryWeatherPagerAdapter;
import task.harri.com.harritask.models.Country;
import task.harri.com.harritask.models.CountryWeather;


public class CountryWeathersTabsFragment extends Fragment implements View.OnClickListener {

    TabLayout tabLayout;
    CountryWeatherPagerAdapter countryWeatherPagerAdapter;
    ViewPager viewPager;
    private View view;
    private CountryViewModel countryViewModel;

    public CountryWeathersTabsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_country_weathers_tabs, container, false);
        initView(view);
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        initObserver();
        return view;
    }

    private void initObserver() {
        // Create the observer which updates the UI.
        final Observer<CountryWeather> countryWeatherObserver = new Observer<CountryWeather>() {
            @Override
            public void onChanged(@Nullable final CountryWeather newCountryWeather) {
                // Update the UI, in this case, a TextView.
                countryWeatherPagerAdapter.udpateCountryWeathersFields(newCountryWeather);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        countryViewModel.getCountryWeather().observe(this, countryWeatherObserver);
    }

    public void updateWeatherFragments(CountryWeather newCountryWeather) {
        countryViewModel.getCountryWeather().setValue(newCountryWeather);
    }

    private void initView(final View view) {

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        countryWeatherPagerAdapter = new CountryWeatherPagerAdapter(getChildFragmentManager(), getActivity(), this);

        viewPager.setAdapter(countryWeatherPagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(2);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ResourcesCompat.getColor(getResources(), R.color.white, null),
                ResourcesCompat.getColor(getResources(), R.color.white, null));
            }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
