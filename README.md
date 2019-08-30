# AndroidOfflineMapLibrary
Offline OpenStreet Map Library (No Internet Required) You dont have to even one-time connect!

> Offline OpenStreetMap Library

> Performance friendly and scalable

## Why this project exists

Offline map usage is kind a problem for developers and there are rare 

## Features and Usage

### Easy implementation
 
You need to download offline maptiles as SQLite format. And you should put them under assets folder.
 
 
#### Java 

```java
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

        //Selected GeoPoint Returns Here

        Toast.makeText(this, geoPoint.toDoubleString(), Toast.LENGTH_SHORT).show();
    }
}
```

#### Kotlin
```kotlin
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
```

```xml    
<egolabsapps.basicodemine.offlinemap.Views.OfflineMapView
        android:id="@+id/map"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:initialFocusLatitude="41.025818"
        app:initialFocusLongitude="28.973436"
        app:zoomLevel="15" />
```

## Implementation

###### Add it in your root build.gradle at the end of repositories

```groovy
    repositories {
        maven { url 'https://jitpack.io' }
    }
```

###### Add the dependency

```groovy

```

## License

```
   Copyright 2019 Egemen ÖZOGUL

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
