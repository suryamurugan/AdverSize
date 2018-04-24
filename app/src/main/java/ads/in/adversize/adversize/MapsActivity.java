package ads.in.adversize.adversize;

/**
 * Created by suryamurugan on 5/4/18.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private UserService userService;

    final List<MediaObject> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_search_fragment);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        userService = ApiUtils.getUserService();

        retrofit2.Call<List<MediaObject>> call = userService.vendorm(String.valueOf("44"));
        call.enqueue(new Callback<List<MediaObject>>() {
            @Override
            public void onResponse(Call<List<MediaObject>> call, Response<List<MediaObject>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    MediaObject fishData = new MediaObject();
                    fishData.setMediaWidth(response.body().get(i).getMediaWidth());
                    fishData.setMediaHeight(response.body().get(i).getMediaHeight());
                    fishData.setMediaName(response.body().get(i).getMediaName());
                    fishData.setMediaImgLocation(response.body().get(i).getMediaImgLocation());
                    data.add(fishData);

                }
            }

            @Override
            public void onFailure(Call<List<MediaObject>> call, Throwable t) {

                //Toast.makeText(getContext(), "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        for (int i=0;i<data.size();i++) {

            mMap.addMarker(new MarkerOptions().
                    position(new LatLng(Double.parseDouble(data.get(i).getMediaGoogleLatitude())
                            ,Double.parseDouble(data.get(i).getMediaGoogleLongitude()))).title(data.get(i).getMediaName()));
        }


    }
}
