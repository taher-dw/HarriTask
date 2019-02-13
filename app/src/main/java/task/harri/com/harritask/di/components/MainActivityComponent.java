package task.harri.com.harritask.di.components;

import android.content.Context;

import dagger.Component;
import dagger.Provides;
import task.harri.com.harritask.adapters.CountriesAdapter;
import task.harri.com.harritask.di.modules.CountriesAdapterModule;
import task.harri.com.harritask.di.modules.MainActivityContextModule;
import task.harri.com.harritask.di.qualifiers.ActivityContext;
import task.harri.com.harritask.di.scopes.ActivityScope;
import task.harri.com.harritask.ui.MainActivity;

@ActivityScope
@Component(modules = CountriesAdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();


    void injectMainActivity(MainActivity mainActivity);
}
