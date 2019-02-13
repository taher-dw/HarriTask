package task.harri.com.harritask.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import task.harri.com.harritask.ui.MainActivity;
import task.harri.com.harritask.di.qualifiers.ActivityContext;
import task.harri.com.harritask.di.scopes.ActivityScope;

@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
