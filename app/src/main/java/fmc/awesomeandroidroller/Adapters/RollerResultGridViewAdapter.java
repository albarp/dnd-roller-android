package fmc.awesomeandroidroller.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import fmc.awesomeandroidroller.R;
import fmc.awesomeandroidroller.models.ApplicationStat;
import fmc.awesomeandroidroller.utility.ColorUtility;

/**
 * Created by aarpini on 13/08/2017.
 */

public class RollerResultGridViewAdapter extends ArrayAdapter<ApplicationStat> {

    private List<ApplicationStat> _stats;

    private int _notSetColor;
    private int _lowColor;
    private int _medColor;
    private int _highColor;

    public RollerResultGridViewAdapter(Context context, int resource, List<ApplicationStat> stats) {
        super(context, resource, stats);

        _stats = stats;

        _notSetColor = ColorUtility.getColorWrapper(context, R.color.notSet);

        _lowColor = ColorUtility.getColorWrapper(context, R.color.low);

        _medColor = ColorUtility.getColorWrapper(context, R.color.med);

        _highColor = ColorUtility.getColorWrapper(context, R.color.high);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        ApplicationStat currentStat = _stats.get(position);

        if(v == null){
            LayoutInflater vi = LayoutInflater.from(getContext());

            v = vi.inflate(R.layout.item_grid_roller, null);
        }

        TextView itemGridDescription = (TextView) v.findViewById(R.id.itemGridDescription);

        String description = currentStat.Name + ": " + currentStat.Value + " (" + currentStat.Bonus + ")";

        itemGridDescription.setText(description);

        RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);

        ratingBar.setRating(currentStat.Rating);

        if(currentStat.Rating == 0){
            v.setBackgroundColor(_notSetColor);
        }
        else if(currentStat.Rating == 1){
            v.setBackgroundColor(_lowColor);
        }
        else if(currentStat.Rating == 2) {
            v.setBackgroundColor(_medColor);
        }
        else if(currentStat.Rating == 3){
            v.setBackgroundColor(_highColor);
        }

        return v;
    }
}
