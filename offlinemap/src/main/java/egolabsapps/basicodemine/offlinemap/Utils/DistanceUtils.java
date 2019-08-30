package egolabsapps.basicodemine.offlinemap.Utils;

import android.location.Location;
import android.util.Log;

import org.osmdroid.util.GeoPoint;

import java.util.Random;

public class DistanceUtils {

    public static GeoPoint getLocationNearby(int Coordx,int Coordy) {
        Random random = new Random();
        double radiusInDegrees = 10000 / 111000f;
        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);
        double new_x = x / Math.cos(Math.toRadians(Coordx));
        double foundLongitude = new_x + Coordy;
        double foundLatitude = y + Coordx;
        return new GeoPoint(foundLongitude, foundLatitude);
    }

    public static float getDistanceBetweenTwoGeoPoint(GeoPoint p1, GeoPoint p2) {
        Log.d("p2", "" + p2.toDoubleString());
        Location loc1 = new Location("");
        loc1.setLatitude(p1.getLatitude());
        loc1.setLongitude(p1.getLongitude());
        Location loc2 = new Location("");
        loc2.setLatitude(p2.getLatitude());
        loc2.setLongitude(p2.getLongitude());
        return (float) (loc1.distanceTo(loc2) * 0.001);
    }
}
