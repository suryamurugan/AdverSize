package ads.in.adversize.adversize;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by suryamurugan on 4/4/18.
 */

public class MapSearchFragment extends Fragment implements OnMapReadyCallback{
    private GoogleMap mMap;

  //  PlaceAutocompleteFragment placeAutoComplete;
   // HashMap<Marker, MediaObject> mDataMap = new HashMap<>();

    private UserService userService;

  //  final List<MediaObject> data = new ArrayList<>();

   // LocationRequest mLocationRequest;
    GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;

    PlaceSelectionListener placeSelectionListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.map_search_fragment,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SupportPlaceAutocompleteFragment placeAutoComplete = (SupportPlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

               addMarker(place);
/*
                CameraPosition position = CameraPosition.builder()
                        .target( new LatLng( place.getLatLng().latitude, place.getLatLng().longitude ) )
                        .zoom( 12f )
                        .bearing( 0.0f )
                        .tilt( 0.0f )

                        .build();
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition( position ));
*/
            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });
        /* supposed to work :(
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.d("Maps", "Place selected: " + place.getName());

                CameraPosition position = CameraPosition.builder()
                        .target( new LatLng( place.getLatLng().latitude, place.getLatLng().longitude ) )
                        .zoom( 12f )
                        .bearing( 0.0f )
                        .tilt( 0.0f )

                        .build();
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition( position ));

            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }


        });
*/
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          //  checkLocationPermission();
        }


        //show error dialog if Google Play Services not available
        if (!isGooglePlayServicesAvailable()) {
            Log.d("onCreate", "Google Play Services not available. Ending Test case.");
            //finish();
        }
        else {
            Log.d("onCreate", "Google Play Services available. Continuing.");
        }










        final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
                //getSupportFragmentManager()




    }


    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(getContext());
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(getActivity(), result,
                        0).show();
            }
            return false;
        }
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
          mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );

        //Initialize Google Play Services
     /*   if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
             //   buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            //buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }







        mMap = googleMap;
*/

        CameraPosition position = CameraPosition.builder()
                .target( new LatLng( 12.29, 76.63 ) )
                .zoom( 12f )
                .bearing( 0.0f )
                .tilt( 0.0f )

               .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition( position ), null );


   //     for (int i=0;i<data.size();i++) {

          /*  mMap.addMarker(new MarkerOptions().
                    position(new LatLng(Double.parseDouble(data.get(0).getMediaGoogleLatitude())
                            ,Double.parseDouble(data.get(0).getMediaGoogleLongitude()))).title(data.get(0).getMediaName()));
                            */
      //  }
   /*   LatLng sydney = new LatLng( 12.29, 76.63 );
       mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
     mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        // Add a marker in Sydney, Australia, and move the camera.
       // LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
       // mMap.moveCamera(CameraUpdateFactory.);

      //  Toast.makeText(getContext(), "activated", Toast.LENGTH_SHORT).show();


        userService = ApiUtils.getUserService();
        // mMap= googleMap;

        retrofit2.Call<List<MediaObject>> call = userService.vendorm(String.valueOf("44"));

        mMap.clear();
        call.enqueue(new Callback<List<MediaObject>>() {
            @Override
            public void onResponse(Call<List<MediaObject>> call, Response<List<MediaObject>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                   /* MediaObject fishData = new MediaObject();
                    fishData.setMediaWidth(response.body().get(i).getMediaWidth());
                    fishData.setMediaHeight(response.body().get(i).getMediaHeight());
                    fishData.setMediaName(response.body().get(i).getMediaName());
                    fishData.setMediaImgLocation(response.body().get(i).getMediaImgLocation());
                    data.add(fishData);

*/
/*                  WOrking code
                    mMap.addMarker(new MarkerOptions().
                            position(new LatLng(Double.parseDouble(response.body().get(i).getMediaGoogleLatitude())
                                    ,Double.parseDouble(response.body().get(i).getMediaGoogleLongitude()))).title(response.body().get(0).getMediaName()));

*/
                    Double lat = Double.valueOf(response.body().get(i).getMediaGoogleLatitude());
                    Double lng = Double.valueOf(response.body().get(i).getMediaGoogleLongitude());
                    String placeName = response.body().get(i).getMediaName();
                    String vicinity = response.body().get(i).getMediaStreet();
                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng latLng = new LatLng(lat, lng);
                    // Position of Marker on Map
                    markerOptions.position(latLng);
                    // Adding Title to the Marker
                    markerOptions.title(placeName + " : " + vicinity);
                    // Adding Marker to the Camera.
                    Marker m = mMap.addMarker(markerOptions);
                    m.showInfoWindow();
                    // Adding colour to the marker
                    //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    /*markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));*/
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marketbit));

                    // move map camera

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(11));



                }
              /*  Toast.makeText(getActivity(), ""+response.body(), Toast.LENGTH_SHORT).show();*/
              //  mapFragment.getMapAsync(MapSearchFragment.this);
            }

            @Override
            public void onFailure(Call<List<MediaObject>> call, Throwable t) {

                Toast.makeText(getContext(), "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }
    public void addMarker(Place p){

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(p.getLatLng());
        markerOptions.title(p.getName()+"");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marketbit));

        //  mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p.getLatLng()));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

    }



/*
    public void drawMarkers(ArrayList<MediaObject> data) {
        Marker m;

        for (MediaObject object: data) {

            m = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng( Double.parseDouble(object.getMediaGoogleLatitude()),Double.parseDouble(object.getMediaGoogleLongitude())))
                    .title(object.getMediaName()));
                    /*.icon(BitmapDescriptorFactory.fromBitmap(D));

            mDataMap.put(m, object);
        }
            }
*/

    private void initCamera() {
        CameraPosition position = CameraPosition.builder()
                .target( new LatLng( 40.7506, -73.9936 ) )
                .zoom( 18f )
                .bearing( 0.0f )
                .tilt( 0.0f )
                .build();

        mMap.animateCamera(
                CameraUpdateFactory.newCameraPosition( position ), null );
      //  mMap.setMapType( GoogleMap.MAP_TYPE_HYBRID );
    }

}
