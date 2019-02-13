package task.harri.com.harritask;

import android.app.Activity;
import android.app.Application;

import task.harri.com.harritask.di.components.ApplicationComponent;
import task.harri.com.harritask.di.components.DaggerApplicationComponent;
import task.harri.com.harritask.di.modules.ContextModule;

public class HarriTaskApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);
    }

    public static HarriTaskApplication get(Activity activity){
        return (HarriTaskApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
