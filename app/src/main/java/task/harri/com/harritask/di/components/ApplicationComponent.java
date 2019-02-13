package task.harri.com.harritask.di.components;

import android.content.Context;

import dagger.Component;
import task.harri.com.harritask.HarriTaskApplication;
import task.harri.com.harritask.di.modules.ContextModule;
import task.harri.com.harritask.di.modules.RetrofitModule;
import task.harri.com.harritask.di.qualifiers.ApplicationContext;
import task.harri.com.harritask.di.scopes.ApplicationScope;
import task.harri.com.harritask.retrofit.APIsInterface;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    public APIsInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(HarriTaskApplication harriTaskApplication);
}
