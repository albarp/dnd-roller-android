package fmc.awesomeandroidroller.utility;

import android.content.Context;
import android.os.Build;

/**
 * Created by aarpini on 30/08/2017.
 */

public class ColorUtility {
    public static int getColorWrapper(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(id);
        } else {
            //noinspection deprecation
            return context.getResources().getColor(id);
        }
    }
}
