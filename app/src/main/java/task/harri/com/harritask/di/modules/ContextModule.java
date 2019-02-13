package task.harri.com.harritask.di.modules;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import task.harri.com.harritask.di.qualifiers.ApplicationContext;
import task.harri.com.harritask.di.scopes.ApplicationScope;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
