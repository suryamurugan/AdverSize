package ads.in.adversize.adversize;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.model.resp;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveTwoActivity extends AppCompatActivity  {

    TextView mediaid,medianame,dimensions,
    city,state,location,avalability,productid,vendorid;
    ImageButton  mediaimage;
    TextView landmark, totalcost, mainsize;

    Button editButton;

    private GoogleMap mMap;
    UserService userService;
    String string;
    String frommap;
    Gson gson = new Gson();
    MediaObject mediaObject = new MediaObject();

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_two);

        editButton = findViewById(R.id.editbutton);

       /* SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/

        Bundle extras = getIntent().getExtras();
        string= extras.getString("myjson");
        frommap = extras.getString("frommap");

        Toast.makeText(this, ""+frommap, Toast.LENGTH_SHORT).show();

        mediaid= findViewById(R.id.mediaID);
        medianame = findViewById(R.id.mediaName);
        dimensions = findViewById(R.id.dimensions);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        location = findViewById(R.id.Location);
        avalability = findViewById(R.id.Avalablity);
        productid = findViewById(R.id.productid);
        vendorid = findViewById(R.id.vendorid);

        landmark= findViewById(R.id.landmarkName);
        totalcost = findViewById(R.id.mediaCost);
        mainsize = findViewById(R.id.mediaSize);
        mediaimage = findViewById(R.id.mediaImg);



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//#####################################################################################################################################
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.edit_media_layout_custom_dialog, null);
                /*final EditText currentpassword = alertLayout.findViewById(R.id.currentpassword);
                final EditText newpassword = alertLayout.findViewById(R.id.newpassword);
                final EditText confirmnewpassword = alertLayout.findViewById(R.id.confirmnewpassword);


                final CheckBox cbToggle = alertLayout.findViewById(R.id.cb_show_pass);

                cbToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // to encode password in dots
                            newpassword.setTransformationMethod(null);
                            currentpassword.setTransformationMethod(null);
                            confirmnewpassword.setTransformationMethod(null);

                        } else {
                            // to display the password in normal text

                            newpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            currentpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            confirmnewpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                    }
                });*/

                AlertDialog.Builder alert = new AlertDialog.Builder(LiveTwoActivity.this);
                alert.setTitle("Edit Media Data");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);
                ////////////////////////////////
               /* spinner = view.findViewById(R.id.planets_spinner);

                adapter= ArrayAdapter.createFromResource(LiveTwoActivity.this,R.array.mediaTypeArray,android.R.layout.simple_spinner_item);
                //adapter.getDropDownView(int position, View convertView,
                //      ViewGroup parent)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = adapterView.getItemAtPosition(i).toString();
                        //Showing selected spinner item
                        if (adapterView.getSelectedItemPosition() == 0){

                        }
                        else{
                            mediaObject.setMediaType(item);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
*/

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LiveTwoActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*String user = currentpassword.getText().toString();
                        final String pass = newpassword.getText().toString();
                        String cpass = confirmnewpassword.getText().toString();

                        if(pass.equals(cpass)){
                            Toast.makeText(getContext(), "Username: " + user + " Email: " + pass+"\n"+cpass, Toast.LENGTH_SHORT).show();

                            retrofit2.Call<resp> call = userService.changepassword(userLocalStore.getLoggedInUser().useremail,user,pass);
                            call.enqueue(new Callback<resp>() {
                                @Override
                                public void onResponse(Call<resp> call, Response<resp> response) {
                                    Toast.makeText(getActivity(), "YAY BRO Password changed to "+pass, Toast.LENGTH_SHORT).show();

                                    userLocalStore.clearUserData();
                                    Intent intent = new Intent(getActivity(),Main2Activity.class);

                                    startActivity(intent);
                                    getActivity().finish();
                                }

                                @Override
                                public void onFailure(Call<resp> call, Throwable t) {
                                    Toast.makeText(getActivity(), "Go get a life"+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                        else {

                            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }*/


                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
//#####################################################################################################################################



            }
        });





        if(frommap!=null){

            userService= ApiUtils.getUserService();

            retrofit2.Call<List<MediaObject>> call = userService.singlemedia(String.valueOf(frommap));
            call.enqueue(new Callback<List<MediaObject>>() {
                @Override
                public void onResponse(Call<List<MediaObject>> call, Response<List<MediaObject>> response) {
                    //Toast.makeText(LiveTwoActivity.this, "yaay"+response.body().get(0).getMediaCity(), Toast.LENGTH_SHORT).show();

                    mediaObject = response.body().get(0);
                    Toast.makeText(LiveTwoActivity.this, ""+mediaObject.getMediaID(), Toast.LENGTH_SHORT).show();
                    mediaid.setText(mediaObject.getMediaID());
                    medianame.setText(mediaObject.getMediaName());
                    dimensions.setText(mediaObject.getMediaWidth()+"*"+mediaObject.getMediaHeight());
                    city.setText(mediaObject.getMediaCity());
                    state.setText(mediaObject.getMediaState());
                    location.setText(mediaObject.getMediaGoogleLatitude()+"\n"+mediaObject.getMediaGoogleLongitude());
                    avalability.setText(mediaObject.getMediaAvailability());
                    productid.setText(mediaObject.getProductID());
                    vendorid.setText(mediaObject.getVendorID());
                    landmark.setText(mediaObject.getMediaStreet());
                    mainsize.setText(mediaObject.getMediaWidth()+"*"+mediaObject.getMediaHeight());
                    totalcost.setText(mediaObject.getMediaTotalPrice12());
                    Glide.with(LiveTwoActivity.this).load("https://adversize.in/" + mediaObject.getMediaImgLocation()).into(mediaimage);



                }

                @Override
                public void onFailure(Call<List<MediaObject>> call, Throwable t) {

                    Toast.makeText(LiveTwoActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });





        }


        if(string!=null) {
            //Toast.makeText(this, ""+string, Toast.LENGTH_SHORT).show();

            MediaObject mediaObject = gson.fromJson(string, MediaObject.class);

            //back
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /*     ImageButton imageButton = findViewById(R.id.mediaImg);
            landmarkname = findViewById(R.id.landmarkName);
            mediasize = findViewById(R.id.mediaSize);
            mediacost = findViewById(R.id.mediaCost);

            landmarkname.setText(mediaObject.getMediaName());
            mediasize.setText("" + mediaObject.getMediaWidth() + " * " + mediaObject.getMediaHeight());
            mediacost.setText("" + mediaObject.getMediaTotalPrice6());
            Toast.makeText(this, "" + mediaObject, Toast.LENGTH_SHORT).show();
            Glide.with(LiveTwoActivity.this).load("https://adversize.in/" + mediaObject.getMediaImgLocation()).into(imageButton);
*/
            mediaid.setText(mediaObject.getMediaID());
            medianame.setText(mediaObject.getMediaName());
            dimensions.setText(mediaObject.getMediaWidth()+"*"+mediaObject.getMediaHeight());
            city.setText(mediaObject.getMediaCity());
            state.setText(mediaObject.getMediaState());
            location.setText(mediaObject.getMediaGoogleLatitude()+"\n"+mediaObject.getMediaGoogleLongitude());
            avalability.setText(mediaObject.getMediaAvailability());
            productid.setText(mediaObject.getProductID());
            vendorid.setText(mediaObject.getVendorID());


            landmark.setText(mediaObject.getMediaStreet());
            mainsize.setText(mediaObject.getMediaWidth()+"*"+mediaObject.getMediaHeight());
            totalcost.setText(mediaObject.getMediaTotalPrice12());
            Glide.with(LiveTwoActivity.this).load("https://adversize.in/" + mediaObject.getMediaImgLocation()).into(mediaimage);



        }



    }

    @Override
    public boolean onSupportNavigateUp() {
        if (string!=null){
        Intent intent = new Intent(getApplicationContext(), BottomNavActivity.class);
        startActivity(intent);
        finish();
        }
        else if(frommap!=null){
            Intent intent = new Intent(getApplicationContext(), BottomNavActivity.class);
            startActivity(intent);
            finish();

        }
        return true;
    }
/*
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


            mMap.addMarker(new MarkerOptions().
                    position(new LatLng(Double.parseDouble(gson.fromJson(string, MediaObject.class).getMediaGoogleLatitude())
                            ,Double.parseDouble(gson.fromJson(string, MediaObject.class).
                            getMediaGoogleLongitude()))).title(gson.fromJson(string, MediaObject.class).getMediaName()));

            LatLng location = new LatLng(Double.parseDouble(gson.fromJson(string, MediaObject.class).getMediaGoogleLatitude())
                    ,Double.parseDouble(gson.fromJson(string, MediaObject.class).
                    getMediaGoogleLongitude()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));



    }*/
}
