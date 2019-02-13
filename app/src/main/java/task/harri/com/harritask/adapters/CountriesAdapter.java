package task.harri.com.harritask.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import task.harri.com.harritask.Data.Constants;
import task.harri.com.harritask.R;
import task.harri.com.harritask.models.Country;
import task.harri.com.harritask.ui.MainActivity;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

public class CountriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  int layoutResourceId;
    private  Context mContext;
    private  List<Country> countries;
    private  Activity activity;

    public CountriesAdapter(Activity activity){
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(activity);

        View conView = inflater.inflate(R.layout.list_item_country, parent, false);
        CountryViewHolder countryViewHolder = new CountryViewHolder(conView);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof CountryViewHolder) {
            final CountryViewHolder holder = (CountryViewHolder) viewHolder;
            final Country country = this.countries.get(position);

            holder.countryName.setText(country.getName());
            holder.countryRegion.setText(country.getRegion());
            String flagURL = Constants.IMAGE_URL+country.alpha2Code+".png";
            Glide.with(activity)
                    .load(flagURL)
                    .into(holder.flag);
            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)activity).updateCountryFragmentDetails(country);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void setCountriesList(List<Country> countriesList) {
        this.countries = countriesList;
        notifyDataSetChanged();
    }

    private class CountryViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout parent;
        private TextView countryName;
        private TextView countryRegion;
        private ImageView flag;

        public CountryViewHolder(View itemView) {
            super(itemView);
            parent = (RelativeLayout)itemView.findViewById(R.id.parent);
            countryName = (TextView) itemView.findViewById(R.id.countryName);
            countryRegion = (TextView) itemView.findViewById(R.id.countryRegion);
            flag = (ImageView) itemView.findViewById(R.id.flag);

        }
    }
}
