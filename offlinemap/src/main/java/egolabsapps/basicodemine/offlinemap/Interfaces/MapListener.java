package egolabsapps.basicodemine.offlinemap.Interfaces;

import org.osmdroid.views.MapView;

import egolabsapps.basicodemine.offlinemap.Utils.MapUtils;

public interface MapListener {
    void mapLoadSuccess(MapView mapView, MapUtils mapUtils);

    void mapLoadFailed(String ex);
}
