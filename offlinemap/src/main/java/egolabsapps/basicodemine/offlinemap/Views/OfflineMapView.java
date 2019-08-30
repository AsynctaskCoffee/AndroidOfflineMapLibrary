package egolabsapps.basicodemine.offlinemap.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Pulse;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import egolabsapps.basicodemine.offlinemap.Interfaces.GeoPointListener;
import egolabsapps.basicodemine.offlinemap.Interfaces.MapListener;
import egolabsapps.basicodemine.offlinemap.Interfaces.PermissionResultListener;
import egolabsapps.basicodemine.offlinemap.R;
import egolabsapps.basicodemine.offlinemap.Utils.MapUtils;
import egolabsapps.basicodemine.offlinemap.Utils.PermissionUtils;
import egolabsapps.basicodemine.offlinemap.Utils.ScreenUtils;

import static android.view.Gravity.CENTER;

public class OfflineMapView extends FrameLayout implements MapListener, PermissionResultListener.PermissionListener {

    private MapListener mapListener;
    private Activity activity;
    private MapUtils mapUtils;
    private SpinKitView kitView;
    private boolean isAnimatePickerAdded = false;

    public void init(Activity activity, MapListener mapListener) {
        this.activity = activity;
        this.mapListener = mapListener;

        if (!PermissionUtils.hasPermissions(activity))
            activity.startActivity(new Intent(activity, PermissionActivity.class));
        else mapUtils = new MapUtils(this, activity);
    }

    public void setInitialPositionAndZoom(@NonNull GeoPoint initialPosition, double zoomLevel) {
        if (mapUtils != null)
            mapUtils.setInitialPosition(initialPosition, zoomLevel);
    }


    public void setAnimatedLocationPicker(boolean isAnimatedLocationPickerActive, GeoPointListener geoPointListener, MapUtils mapUtils) {
        if (activity != null && mapUtils != null && isAnimatedLocationPickerActive && !isAnimatePickerAdded) {
            ImageView locationToggle = new ImageView(activity);
            ImageView locationCircle = new ImageView(activity);

            FrameLayout.LayoutParams paramsForCircle = new LayoutParams((int) ScreenUtils.pxFromDp(16, activity), (int) ScreenUtils.pxFromDp(16, activity));
            paramsForCircle.gravity = CENTER;
            locationCircle.setImageDrawable(activity.getResources().getDrawable(R.drawable.location_oval_icon));
            locationCircle.setLayoutParams(paramsForCircle);

            FrameLayout.LayoutParams paramsForToggle = new LayoutParams((int) ScreenUtils.pxFromDp(50, activity), (int) ScreenUtils.pxFromDp(50, activity));
            paramsForToggle.gravity = CENTER;
            paramsForToggle.bottomMargin = (int) ScreenUtils.pxFromDp(24, activity);
            locationToggle.setImageDrawable(activity.getResources().getDrawable(R.drawable.taskpicke_icon));
            locationToggle.setLayoutParams(paramsForToggle);

            addView(locationCircle);
            addView(locationToggle);

            mapUtils.setAnimatedView(locationToggle, geoPointListener);
            isAnimatePickerAdded = true;
        }
    }

    public OfflineMapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        PermissionResultListener.getInstance().setListener(this);
        kitView = new SpinKitView(context);
        Sprite kitStyle = new Pulse();
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = CENTER;
        kitView.setLayoutParams(params);
        kitView.setIndeterminateDrawable(kitStyle);
        addView(kitView);
    }

    @Override
    public void mapLoadSuccess(MapView mapView, MapUtils mapUtils) {
        mapView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mapView);
        kitView.setVisibility(GONE);
        if (this.mapUtils == null)
            this.mapUtils = mapUtils;
        mapListener.mapLoadSuccess(mapView, mapUtils);
    }

    @Override
    public void mapLoadFailed(String ex) {
        kitView.setVisibility(GONE);
        mapListener.mapLoadFailed(ex);
    }

    @Override
    public void onAccept() {
        mapUtils = new MapUtils(this, activity);
    }

    @Override
    public void onReject() {
        Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show();
    }

    public MapUtils getMapUtils() {
        return mapUtils;
    }
}
