package co.circe.respos.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.ArrayList;
import java.util.Random;

import co.circe.respos.R;
import co.circe.respos.util.ImageUtil;

/**
 * Created by akhil on 17/6/15.
 */
public class StaggeredAdapter extends ArrayAdapter<String> {
    private static final String TAG = "StaggeredAdapter";
    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    public StaggeredAdapter(Context context, int textViewResourceId,
                         ArrayList<String> objects) {
        super(context, textViewResourceId, objects);
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mRandom = new Random();
    }
    @Override
    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_grid_item,
                    parent, false);
            vh = new ViewHolder();
            vh.imgView = (DynamicHeightImageView) convertView
                    .findViewById(R.id.imgView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        double positionHeight = getPositionRatio(position);
        vh.imgView.setHeightRatio(positionHeight);
        ImageUtil.displayImage(vh.imgView, getItem(position), null);
        return convertView;
    }
    static class ViewHolder {
        DynamicHeightImageView imgView;
    }
    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
// if not yet done generate and stash the columns height
// in our real world scenario this will be determined by
// some match based on the known height and width of the image
// and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }
    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
// the width
    }
}
