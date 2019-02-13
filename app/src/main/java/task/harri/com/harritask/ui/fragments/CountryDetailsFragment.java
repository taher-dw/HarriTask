package task.harri.com.harritask.ui.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import task.harri.com.harritask.CountryViewModel;
import task.harri.com.harritask.Data.Constants;
import task.harri.com.harritask.R;
import task.harri.com.harritask.models.Country;
public class CountryDetailsFragment extends Fragment {

    private View view;
    private TextView tvName;
    private TextView tvRegion;
    private TextView tvCapital;
    private TextView tvPopulation;
    private ImageView ivFlag;

    private CountryViewModel countryViewModel;

    public CountryDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_country_details, container, false);
        initView(view);
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        initObserver();
        return view;
    }

    private void initObserver() {
        // Create the observer which updates the UI.
        final Observer<Country> countryObserver = new Observer<Country>() {
            @Override
            public void onChanged(@Nullable final Country newCountry) {
                // Update the UI, in this case, a TextView.
                fillFields(newCountry);
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        countryViewModel.getCountry().observe(this, countryObserver);
    }

    private void initView(View view) {
        tvName = (TextView) view.findViewById(R.id.date);
        tvRegion = (TextView) view.findViewById(R.id.region);
        tvCapital = (TextView) view.findViewById(R.id.capital);
        tvPopulation = (TextView) view.findViewById(R.id.population);
        ivFlag = (ImageView) view.findViewById(R.id.ivFlag);
    }

    public void fillFields(Country country) {
        tvName.setText(country.getName());
        tvRegion.setText(country.getRegion());
        tvCapital.setText(country.getCapital());
        tvPopulation.setText(country.getPopulation()+"");
        String flagURL = Constants.IMAGE_URL+country.alpha2Code+".png";
        Glide.with(getActivity())
                .load(flagURL)
                .into(ivFlag);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    public void updateCountry(Country country){
        countryViewModel.getCountry().setValue(country);
    }

}