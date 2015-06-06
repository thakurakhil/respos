
package co.froogal.froogal.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.froogal.froogal.ForgetPasswordActivity;
import co.froogal.froogal.LoginRegisterActivity;
import co.froogal.froogal.R;
import co.froogal.froogal.ResDetailsActivity;
import co.froogal.froogal.fragment.locationListViewFragment;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> implements View.OnClickListener{

    private List<RestaurantInfo> restaurantList;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public RestaurantAdapter(List<RestaurantInfo> restaurantList) {
        this.restaurantList = restaurantList;
    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder restaurantViewHolder, int i) {
        RestaurantInfo ci = restaurantList.get(i);
        restaurantViewHolder.resName.setText(ci.resName);
        restaurantViewHolder.resAdd.setText(ci.resAddress);
        restaurantViewHolder.resName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Restaurantchild " + v.getTag(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMainActivity =  new Intent(v.getContext(), ResDetailsActivity.class);
                v.getContext().startActivity(openMainActivity);
            }
        });
        return new RestaurantViewHolder(itemView, this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Restaurant " + v.getTag(), Toast.LENGTH_SHORT).show();
    }


    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        protected TextView resName;
        protected TextView resAdd;
        private RestaurantAdapter mAdapter;
        View left;

        public RestaurantViewHolder(View v, RestaurantAdapter adapter) {
            super(v);
            mAdapter = adapter;
            resName =  (TextView) v.findViewById(R.id.resName);
            resAdd = (TextView)  v.findViewById(R.id.resAddress);

            left = v.findViewById(R.id.details);
            left.setTag(resName);
            //left.setOnClickListener(RestaurantAdapter.this);


        }


    }










}