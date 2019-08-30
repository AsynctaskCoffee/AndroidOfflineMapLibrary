package egolabsapps.basicodemine.offlinemap.Utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {
    public static float convertPixelsToDp(float px, Context context) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final float dp, final Context context) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
