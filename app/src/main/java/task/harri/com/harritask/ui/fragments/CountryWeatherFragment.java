package task.harri.com.harritask.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import task.harri.com.harritask.R;
import task.harri.com.harritask.models.CountryWeather;
import task.harri.com.harritask.models.DaysList;

import static java.util.Calendar.getInstance;

public class CountryWeatherFragment extends Fragment {

    private View view;
    private String viewName;
    private TextView tvDate;
    private TextView tvTemp;
    private TextView tvPressure;
    private TextView tvHumidity;
    private ImageView ivWeather;

    public CountryWeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_country_weather, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvDate = (TextView) view.findViewById(R.id.date);
        tvTemp = (TextView) view.findViewById(R.id.temp);
        tvPressure = (TextView) view.findViewById(R.id.pressure);
        tvHumidity = (TextView) view.findViewById(R.id.humidity);
        ivWeather = (ImageView) view.findViewById(R.id.ivWeather);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void fillFields(int position,CountryWeather countryWeather) {
        List<DaysList> daysList = countryWeather.getList();
        //int[] positions = getPositionOfToday(daysList);
        DaysList dayList ;
        if (position == 0) {
            dayList = daysList.get(0);
            fillWeatherDetails(dayList);
        }else {
            dayList = daysList.get(4);
            fillWeatherDetails(dayList);
        }
    }

    private void fillWeatherDetails(DaysList dayList) {
        tvDate.setText(dayList.getDtTxt());
        tvTemp.setText(dayList.getMain().getTempMin() + " - " + dayList.getMain().getTempMax());
        tvPressure.setText(dayList.getMain().getPressure() + "");
        tvHumidity.setText(dayList.getMain().getHumidity() + "");
    }

    private int[] getPositionOfToday(List<DaysList> daysList) {
        Date now = getInstance().getTime();
        Calendar cal = getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tempDate = new Date();
        int todayPosition = 0;
        int tomorrowPosition = 1;
        for (int i = 0; i < daysList.size(); i++) {
            try {
                tempDate = format.parse(daysList.get(i).getDtTxt());
                if ( (now.getTime()- tempDate.getTime())<(24*60*60*1000) ) {
                    todayPosition = i;
                }
                else if ((tomorrow.getTime()-now.getTime())<(48*60*60*1000)) {
                    tomorrowPosition = i;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        /*
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        int currentDay          = Integer.parseInt((String) DateFormat.format("dd",   todayDate)); // 20
        int currentMonth  = Integer.parseInt((String) DateFormat.format("MM",   todayDate)); // 06
        int currentYear         = Integer.parseInt((String) DateFormat.format("yyyy", todayDate)); // 2013

        int tempDay  = 0;
        int tempMonth  = 0;
        int tempYear = 0;

        int todayPosition = 0;
        int tommorowPosition = 1;
        for (int i =0; i < daysList.size(); i++){
            try {
                date = format.parse(daysList.get(i).getDtTxt());
                tempDay  = Integer.parseInt((String) DateFormat.format("dd",   todayDate)); // 20
                tempMonth  = Integer.parseInt((String) DateFormat.format("MM",   todayDate)); // 06
                tempYear  = Integer.parseInt((String) DateFormat.format("yyyy", todayDate)); // 2013

                if (currentDay == tempDay && currentMonth == tempMonth && currentYear == tempYear){
                    todayPosition = i;
                }
                if ((currentDay+1) == tempDay && currentMonth == tempMonth && currentYear == tempYear){
                    tommorowPosition = i;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        */
        }
        int [] positions = new int[2];
        positions[0]=todayPosition;
        positions[1]=tomorrowPosition;
        return positions;
    }

}