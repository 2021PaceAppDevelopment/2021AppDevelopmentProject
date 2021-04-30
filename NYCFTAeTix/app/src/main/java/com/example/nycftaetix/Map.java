package com.example.nycftaetix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;



public class Map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener {
    private static final int REQUEST_CODE = 1;
    private Location currentLocation;
    private MapView mapView;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private FusedLocationProviderClient mFusedLocationClient;
    private final String TAG = "MAP_ACTIVITY";
    private GoogleMap gMap;
    private LatLng latLngOne;
    private  LatLng latLngTwo;
    private GeoApiContext geoApiContext;
    //ArrayList to store PolyLine Information
    private ArrayList<PolylineInfo> polylineInfos = new ArrayList<>();
    private Marker mark;
    private Marker selectedMarker;
    private final String apiKey = "AIzaSyDWnEiYtshg-hHBlUcPR8S4aae6BTKoc3k";
    private  String lineName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Toolbar mapToolbar =  findViewById(R.id.maps_toolbar);
        setSupportActionBar(mapToolbar);


        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        if(geoApiContext == null){
            //used to calculate
            geoApiContext = new GeoApiContext.Builder().apiKey(getString(R.string.api_key)).build();
        }
        // initializing fused location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        // Initializing places
        if(!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }
        PlacesClient placesClient = Places.createClient(this);


        setAutocompleteSupportFragment();
        // MapView is in a bundle to save current state before being destroyed
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        getLocation();

    }
    @Override
    public void onMapReady(GoogleMap map) {
        LatLng lng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());


        // Creating pin for map
        MarkerOptions options = new MarkerOptions().position(lng).title("Current Location");
        map.animateCamera(CameraUpdateFactory.newLatLng(lng));
        // Allows users to zoom in and out of maps
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(lng, 15));
        // Adds a pin to the map
        map.addMarker(options);

        gMap = map;
        //Allows user to decide which route they would like to take
        map.setOnPolylineClickListener(this);
        map.setOnInfoWindowClickListener(this::retrieveDirections);
        map.setMapType(R.string.map_id);
    }
    private void setAutocompleteSupportFragment(){
        // initializing autoComplete
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autoComplete_fragment);

        assert autocompleteSupportFragment != null;
        // autocompleteSupportFragment.setTypeFilter(TypeFilter.ESTABLISHMENT);
        autocompleteSupportFragment.setLocationBias(RectangularBounds.newInstance(new LatLng(40.74918831638174, -73.99070172291377),
                new LatLng(40.74918831638174, -73.99070172291377)));
        autocompleteSupportFragment.setCountries("USA");

        //specify types of place data to return
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS));
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                String dest = place.getName();
                Address address;
                List<Address> addressList = null;
                if(dest != null && !dest.equals("")){
                    Geocoder geocoder = new Geocoder(Map.this);
                    try{
                        addressList = geocoder.getFromLocationName(dest, 1);
                    }catch (Exception e){
                        Log.d(TAG, "onPlaceSelected: " + e.getMessage() + " " + e.toString());

                    }
                    address = addressList.get(0);
                    Log.d(TAG, "autocompleteSupportFramgnet method: " + address);

                    latLngTwo = new LatLng(address.getLatitude(), address.getLongitude());
                    mark = gMap.addMarker(new MarkerOptions()
                            .position(latLngTwo)
                            .title(dest));
                    mark.showInfoWindow();
                    retrieveDirections(mark);
                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngTwo, 15));
                    selectedMarker = mark;
                }


                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.i(TAG, "AutoComplete Error. Could no get place id and name " + status);

            }
        });
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
                    latLngOne = new LatLng(currentLocation.getLongitude(), currentLocation.getLatitude());
                    Log.d(TAG, "Longitude and latitude coordinates: " + currentLocation.getLongitude() + " " + currentLocation.getLatitude());
                    assert mapView != null;
                    mapView.getMapAsync(Map.this);

                }
            }

        });
    }



    private void retrieveDirections( Marker mapMarker){
        com.google.maps.model.LatLng dest = new  com.google.maps.model.LatLng(
                mapMarker.getPosition().latitude, mapMarker.getPosition().longitude);
        DirectionsApiRequest directionsApiRequest = new DirectionsApiRequest(geoApiContext);
        directionsApiRequest.alternatives(true)
                .mode(TravelMode.TRANSIT)
                .transitMode(TransitMode.BUS)
                .origin(new com.google.maps.model.LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()))
                .destination(dest).setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                polyLines(result);

                DirectionsStep directionsStep = new DirectionsStep();
                //DirectionsStep[] dStep = new DirectionsStep[]();
              //result.routes[0].legs[0].steps[0].transitDetails.line.name;



                //Log.d(TAG, "Retrieve directions: Result routes:" + result.routes[0].toString());
                Log.d(TAG, "Retrieve directions: Result routes duration:" + result.routes[0].legs[0].steps[0].transitDetails.line.name);
                //Log.d(TAG, "Retrieve directions: Result routes distance:" + result.routes[0].legs[0].distance);
                //Log.d(TAG, "Retrieve directions: Result routes way points:" + result.geocodedWaypoints[0].toString());
                //Duration duration = result.routes[0].legs[0].duration;


            }

            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "Unable to retrieve directions. Error: " + e.getMessage() + " " + e.toString());
            }
        });

        Log.d(TAG, "retrieveDirections method " + dest.toString());
    }

    /**
     *This method will add polyLines between
     * users location and user searched location
     * @param result
     * */

    private void polyLines(DirectionsResult result){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //checking for duplicates
                if (polylineInfos.size() > 0){
                    for (PolylineInfo polylineInfo : polylineInfos){
                        //removing the duplicate
                        polylineInfo.getLine().remove();

                    }
                    polylineInfos.clear();
                    polylineInfos = new ArrayList<>();
                }
                for (DirectionsRoute directionsRoute : result.routes){
                    List<com.google.maps.model.LatLng> path = PolylineEncoding.decode(
                            directionsRoute.overviewPolyline.getEncodedPath());
                    List<LatLng> newPath = new ArrayList<>();
                    //retrieving line name of bus
                    lineName = directionsRoute.legs[0].steps[0].transitDetails.line.name;
                    // Retrieving all coordinates to make a polyline
                    for(com.google.maps.model.LatLng latLng: path){
                        newPath.add(new LatLng(latLng.lat, latLng.lng));
                        Log.d(TAG, "run and leg " + latLng.toString());
                    }

                    Log.d(TAG, "run and leg " +  directionsRoute.legs[0].toString());
                    Polyline line = gMap.addPolyline(new PolylineOptions().addAll(newPath));
                    line.setWidth(15);
                    line.setColor(Color.GRAY);
                    line.setGeodesic(true);
                    line.setClickable(true);
                    //Getting reference from direction and polyline
                    polylineInfos.add(new PolylineInfo(line, directionsRoute.legs[0]));
                    onPolylineClick(line);
                }
                selectedMarker.setVisible(false);
            }
        });
    }


    /** If the polyline has the same id as the one that was clicked,
     * that polyline will turn blue, otherwise it will stay gray
     *
     **/

    public void onPolylineClick(Polyline polyline) {
        for (PolylineInfo info : polylineInfos){
            if(polyline.getId().equals(info.getLine().getId())){
                info.getLine().setColor(Color.BLUE);
                info.getLine().setZIndex(1);
                LatLng endLocation = new LatLng(info.getLeg().endLocation.lat, info.getLeg().endLocation.lng);
                Marker gMapMarker = gMap.addMarker(new MarkerOptions()
                        .position(endLocation).title(info.getLeg().endAddress)
                        .snippet(" Duration: " + info.getLeg().duration + " Distance: "
                                + info.getLeg().distance));
                Log.d("onPolylineClick", "lineName: " + lineName);
                gMapMarker.showInfoWindow();

            }else{
                info.getLine().setColor(Color.GRAY);
                info.getLine().setZIndex(0);
            }

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
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