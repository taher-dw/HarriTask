package task.harri.com.harritask.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import task.harri.com.harritask.R;
import task.harri.com.harritask.models.CountryWeather;
import task.harri.com.harritask.ui.fragments.CountryWeatherFragment;
import task.harri.com.harritask.ui.fragments.CountryWeathersTabsFragment;

/**
 * @Author Taher Dweikat
 */
public class CountryWeatherPagerAdapter extends FragmentPagerAdapter {

    private  FragmentActivity activity;
    private  CountryWeathersTabsFragment countryWeathersTabsFragment;
    private CountryWeatherFragment countryTodayWeatherFragment;
    private CountryWeatherFragment countryTomorrowWeatherFragment;


    public CountryWeatherPagerAdapter(FragmentManager fm, FragmentActivity activity, CountryWeathersTabsFragment countryWeathersTabsFragment) {
        super(fm);
        this.activity = activity;
        this.countryWeathersTabsFragment = countryWeathersTabsFragment;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment weatherFragment = null;
        if (position == 0) {
            weatherFragment = new CountryWeatherFragment();
            countryTodayWeatherFragment = (CountryWeatherFragment) weatherFragment;
        } else if (position == 1) {
            weatherFragment = new CountryWeatherFragment();
            countryTomorrowWeatherFragment = (CountryWeatherFragment) weatherFragment;
        }
        return weatherFragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;

    }

    @Override
    public int getCount() {
        return 2;

    }

    public void udpateCountryWeathersFields(CountryWeather countryWeather){
        countryTodayWeatherFragment.fillFields(0,countryWeather);
        countryTomorrowWeatherFragment.fillFields(1,countryWeather);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        String title ="";
        switch (position) {
            case 0:
                title =  activity.getString(R.string.today);
                break;
            case 1:
                title =  activity.getString(R.string.tomorrow);
                break;
        }
        return title;

    }

}
