package task.harri.com.harritask;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import task.harri.com.harritask.models.Country;
import task.harri.com.harritask.models.CountryWeather;

public class CountryViewModel extends ViewModel {

    private MutableLiveData<Country> mCurrentName;

    public MutableLiveData<Country> getCountry() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<Country>();
        }
        return mCurrentName;
    }
    private MutableLiveData<CountryWeather> currentCountryWeather;

    public MutableLiveData<CountryWeather> getCountryWeather() {
        if (currentCountryWeather == null) {
            currentCountryWeather = new MutableLiveData<CountryWeather>();
        }
        return currentCountryWeather;
    }

}
