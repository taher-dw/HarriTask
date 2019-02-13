package task.harri.com.harritask.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import task.harri.com.harritask.Data.Constants;
import task.harri.com.harritask.Data.ServiceApi;
import task.harri.com.harritask.models.Country;
import task.harri.com.harritask.models.CountryWeather;

public interface APIsInterface {
    @GET(ServiceApi.GET_All_COUNTRIES)
    Call<List<Country>> getAllCountries();

    @GET
    Call<CountryWeather> getCountryWeathers(@Url String url,
                                            @Query(Constants.LON)String lon,
                                            @Query(Constants.LAT)String lat,
                                            @Query(Constants.APP_ID)String appid);
}
