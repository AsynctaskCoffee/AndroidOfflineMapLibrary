package egolabsapps.basicodemine.offlinemaps

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import egolabsapps.basicodemine.offlinemap.Interfaces.GeoPointListener
import egolabsapps.basicodemine.offlinemap.Interfaces.MapListener
import egolabsapps.basicodemine.offlinemap.Utils.MapUtils
import egolabsapps.basicodemine.offlinemap.Views.OfflineMapView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MainActivityKotlin : AppCompatActivity(), MapListener, GeoPointListener {

    lateinit var offlineMapView: OfflineMapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)
        offlineMapView = findViewById(R.id.map)
        offlineMapView.init(this, this)
    }

    @SuppressLint("ShowToast")
    override fun onGeoPointRecieved(geoPoint: GeoPoint?) {

        //Selected GeoPoint Returns Here

        Toast.makeText(this, geoPoint?.toDoubleString(), Toast.LENGTH_SHORT).show()
    }

    override fun mapLoadSuccess(mapView: MapView?, mapUtils: MapUtils?) {

        // GeoPoint belongs to ISTANBUL heart of the world :)

        offlineMapView.setInitialPositionAndZoom(GeoPoint(41.011099, 28.996885), 15.5)
        offlineMapView.setAnimatedLocationPicker(true, this, mapUtils)


    }

    override fun mapLoadFailed(ex: String?) {
        Log.e("ex", ex.orEmpty())
    }
}
