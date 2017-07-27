package sttnf.app.nfkita.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by isfaaghyth on 7/20/17.
 * github: @isfaaghyth
 */

public class GridColumn {

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

}