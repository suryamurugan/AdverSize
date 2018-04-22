package ads.in.adversize.adversize;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
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

/**
 * Created by suryamurugan on 4/4/18.
 */

public class MapSearchFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.map_search_fragment,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
                //getSupportFragmentManager()

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        CameraPosition position = CameraPosition.builder()
                .target( new LatLng( 12.29, 76.63 ) )
                .zoom( 12f )
                .bearing( 0.0f )
                .tilt( 0.0f )
                .build();
        mMap.animateCamera(
                CameraUpdateFactory.newCameraPosition( position ), null );
        mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
        // Add a marker in Sydney, Australia, and move the camera.
       // LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
       // mMap.moveCamera(CameraUpdateFactory.);
    }

    private void initCamera() {
        CameraPosition position = CameraPosition.builder()
                .target( new LatLng( 40.7506, -73.9936 ) )
                .zoom( 18f )
                .bearing( 0.0f )
                .tilt( 0.0f )
                .build();

        mMap.animateCamera(
                CameraUpdateFactory.newCameraPosition( position ), null );
        mMap.setMapType( GoogleMap.MAP_TYPE_HYBRID );
    }
}
