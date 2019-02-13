package task.harri.com.harritask.di.modules;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import task.harri.com.harritask.adapters.CountriesAdapter;
import task.harri.com.harritask.di.scopes.ActivityScope;
import task.harri.com.harritask.models.Country;
import task.harri.com.harritask.ui.MainActivity;

@Module(includes = {MainActivityContextModule.class})
public class CountriesAdapterModule {
    @Provides
    @ActivityScope
    public CountriesAdapter getCountiresAdapter(MainActivity mainActivity) {
        return new CountriesAdapter(mainActivity);
    }
}
