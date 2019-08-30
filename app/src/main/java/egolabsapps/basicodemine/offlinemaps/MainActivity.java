package egolabsapps.basicodemine.offlinemaps;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import egolabsapps.basicodemine.offlinemap.Interfaces.GeoPointListener;
import egolabsapps.basicodemine.offlinemap.Interfaces.MapListener;
import egolabsapps.basicodemine.offlinemap.Utils.MapUtils;
import egolabsapps.basicodemine.offlinemap.Views.OfflineMapView;

public class MainActivity extends AppCompatActivity implements MapListener, GeoPointListener {

    OfflineMapView offlineMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offlineMapView = findViewById(R.id.map);
        offlineMapView.init(this, this);
    }


    @Override
    public void mapLoadSuccess(MapView mapView, MapUtils mapUtils) {

        // GeoPoint belongs to ISTANBUL heart of the world :)

        offlineMapView.setInitialPositionAndZoom(new GeoPoint(41.011099, 28.996885), 15.5);
        offlineMapView.setAnimatedLocationPicker(true, this, mapUtils);
    }

    @Override
    public void mapLoadFailed(String ex) {
        Log.e("ex:", ex);
    }

    @Override
    public void onGeoPointRecieved(GeoPoint geoPoint) {
        Toast.makeText(this, geoPoint.toDoubleString(), Toast.LENGTH_SHORT).show();
    }
}
