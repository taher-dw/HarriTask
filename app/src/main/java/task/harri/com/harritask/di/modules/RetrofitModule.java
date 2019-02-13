package task.harri.com.harritask.di.modules;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import task.harri.com.harritask.Data.ServiceApi;
import task.harri.com.harritask.di.scopes.ApplicationScope;
import task.harri.com.harritask.retrofit.APIsInterface;

@Module
public class RetrofitModule {
    @Provides
    @ApplicationScope
    APIsInterface getApiInterface(Retrofit retroFit) {
        return retroFit.create(APIsInterface.class);
    }
    @Provides
    @ApplicationScope
    Interceptor getInterceptor(){
        return  new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder();
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    @Provides
    @ApplicationScope
    OkHttpClient getOkHttpCleint(Interceptor interceptor) {
        okhttp3.OkHttpClient.Builder okHttpClientBuilder = new okhttp3.OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(interceptor);
        okhttp3.OkHttpClient okHttpClient = okHttpClientBuilder
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        //}
        return okHttpClient;
    }

    @Provides
    @ApplicationScope
    Retrofit getRetrofit(OkHttpClient okHttpClient) {
        Retrofit r = null;
        try {


             r = new Retrofit.Builder()
                    .baseUrl(ServiceApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }catch (Exception e){
            String s= e.toString();
        }
        return r;
    }


}
