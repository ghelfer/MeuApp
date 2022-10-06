package com.example.meuapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity6 extends AppCompatActivity {

    MapView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        map = findViewById(R.id.map);
        map.getTileProvider().clearTileCache();
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        Configuration.getInstance().setCacheMapTileCount((short) 12);
        Configuration.getInstance().setCacheMapTileOvershoot((short) 12);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        IMapController controller = map.getController();
        GeoPoint starpoint =  new GeoPoint(0.0,-50.0);
        controller.setZoom(3.0);
        controller.setCenter(starpoint);
        map.setHasTransientState(true);
        //map.invalidate();

        //usando Google Fused para acesso GPS
        FusedLocationProviderClient fused = LocationServices.getFusedLocationProviderClient(this);
        LocationRequest current = new LocationRequest();
        current.setInterval(5000).setFastestInterval(0).setMaxWaitTime(0).setSmallestDisplacement(0)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationCallback call = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (locationResult == null) {
                    return;
                }
                for (Location loc : locationResult.getLocations()) {
                    if (loc != null) {
                        Toast.makeText(getApplicationContext(),
                                loc.getLatitude() + "@" + loc.getLongitude(),
                                Toast.LENGTH_LONG).show();

                        GeoPoint gp = new GeoPoint(loc.getLatitude(), loc.getLongitude());
                        Marker marker = new Marker(map);
                        marker.setPosition(gp);
                        marker.setTitle("estou aqui");
                        marker.setPanToView(true);
                        map.getOverlays().add(marker);
                        map.invalidate();
                    }
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fused.requestLocationUpdates(current, call, null);

    }
}