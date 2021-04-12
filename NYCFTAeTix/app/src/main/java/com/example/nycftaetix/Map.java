package com.example.nycftaetix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.SearchView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.GeoApiContext;

import java.io.IOException;
import java.util.List;


public class Map extends AppCompatActivity implements OnMapReadyCallback {
    private static final int REQUEST_CODE = 1;
    private Location currentLocation;
    private MapView mapView;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private FusedLocationProviderClient mFusedLocationClient;
    private String TAG = "MAP_ACTIVITY";
    private GoogleMap gMap;
    private SearchView searchView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        // initializing fused location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // initializing search view
        searchView =  findViewById(R.id.searchView);

        // MapView is in a bundle to save current state before being destroyed
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        getLocation();
        searchLocation();


    }
    @Override
    public void onMapReady(GoogleMap map) {
        LatLng lng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        // Creating pin for map
        MarkerOptions options = new MarkerOptions().position(lng).title("Current location");
        map.animateCamera(CameraUpdateFactory.newLatLng(lng));
        // Allows users to zoom in and out of maps
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(lng, 15));
        // Adds a pin to the map
        map.addMarker(options);

        gMap = map;

    }

    // Will retrieve current location of user
    private void getLocation() {
        Log.d(TAG, "getLocation called");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
       Task<Location> locationTask = mFusedLocationClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Checks to make sure that location isn't null. On rare occasions this could happen when using FusedLocationClient
                if(location != null){
                    currentLocation = location;
                    Log.d(TAG, "Longitude and latitude coordinates: " + currentLocation.getLongitude() + " " + currentLocation.getLatitude());
                    assert mapView != null;
                    mapView.getMapAsync(Map.this);
                }
            }
        });
    }

    // This method is for the Search view which will allow users to search for a location
    private void searchLocation(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Retrieving location name from search view
                String locationSearch = searchView.getQuery().toString();

                //creating a list of address where addresses will be stored
                List<Address> addressList = null;

                if(locationSearch != null || locationSearch.equals("")){
                    Geocoder geocoder = new Geocoder(Map.this);
                    try{
                        addressList = geocoder.getFromLocationName(locationSearch, 1);
                    }catch (IOException e){
                        Log.d(TAG, "search view: " + e.getLocalizedMessage());
                    }
                    assert addressList != null;
                    Address address =  addressList.get(0);

                    LatLng lng = new LatLng(address.getLatitude(), address.getLongitude());
                    gMap.addMarker(new MarkerOptions().position(lng).title(locationSearch));
                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lng, 15));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    // Below methods are needed with mapViews
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // When permission is granted, the getLocation method finds the location of user.
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }
        }
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void onTickets(View view) {
        Intent ticketPage = new Intent(this, Tickets.class);
        startActivity(ticketPage);
    }

    public void onMap(View view) {
        Intent mapIntent = new Intent(this, Map.class);
        startActivity(mapIntent);
    }
    public void onProfile(View view) {
        Intent profileIntent = new Intent(this, Profile.class);
        startActivity(profileIntent);
    }
}