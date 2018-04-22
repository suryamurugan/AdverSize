package ads.in.adversize.adversize;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.app.Activity;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.model.resp;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by suryamurugan on 4/4/18.
 */

public class AddMediaFragment extends Fragment  {

    MediaObject media;


    private GoogleMap mMap;
//OnMapReadyCallback

    ///
    /* this is a autocomplete fragment working
      <fragment
    android:id="@+id/place_autocomplete_fragment"
    android:layout_width="match_parent"
    android:layout_height="80dp"
    android:name="com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment"
            />
    */

    ImageView imageView;

    private static final int IMG_REQUEST = 777;
    Bitmap bitmap;
    UserLocalStore userLocalStore;
    UserService userService1;
    // 10 fields
    EditText media_landmark, media_facing, media_Width, media_height, media_locality, media_city, media_state, price3, price6, price1;
    // 2 fields
    Button availabeFrom;
    ImageButton longlat; //mediaType;

    // 1 Image Field
    Button select, upload;

    String mediatype;
    String medialandmark;
    String mediafacing;
    String width;
    String height;
    String locality;
    String city;
    String state;
    String p3;
    String p6;
    String p12;
    String vendorID;
    String available;
    String longlati;

    int PLACE_PICKER_REQUEST = 1;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

   // private Context mContext;
    public  Activity activitym;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activitym = activity;
    }

/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }
*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addmedia_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        userLocalStore = new UserLocalStore(getContext());
        userService1 = ApiUtils.getUserService();


        User user = userLocalStore.getLoggedInUser();
        vendorID = String.valueOf(user.vedorid);


        imageView = view.findViewById(R.id.image);
        //mediaType = view.findViewById(R.id.mediaType);

        media_landmark = view.findViewById(R.id.media_landmark);
        media_facing = view.findViewById(R.id.media_facing_towards);
        media_Width = view.findViewById(R.id.media_width);
        media_height = view.findViewById(R.id.media_height);
        media_locality = view.findViewById(R.id.media_locality);
        media_city = view.findViewById(R.id.media_city);
        media_state = view.findViewById(R.id.media_state);
        price3 = view.findViewById(R.id.pirce3);
        price6 = view.findViewById(R.id.price6);
        price1 = view.findViewById(R.id.price1);
        availabeFrom = view.findViewById(R.id.avaibaleFrom);

        longlat = view.findViewById(R.id.locationImage);



        availabeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
showDatePickerDialog(view);


            }
        });

        longlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /////////////////////////////////////// for /

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                //Intent intent = new Intent();
                //   intent.setType("image/*");
                //intent.setAction(Intent.ACTION_GET_CONTENT);


                try {
                    startActivityForResult(builder.build(activitym), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });



        ////////////////////////////////
        spinner = view.findViewById(R.id.planets_spinner);

        adapter= ArrayAdapter.createFromResource(getContext(),R.array.mediaTypeArray,android.R.layout.simple_spinner_item);
        //adapter.getDropDownView(int position, View convertView,
          //      ViewGroup parent)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();

                // Showing selected spinner item
               // Toast.makeText(adapterView.getContext(), ": " + item, Toast.LENGTH_LONG).show();

                mediatype =   item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //////////////




        /////////////////////////////////////////////

////////////////////////////// FOR MAPS /////////////////////////////////////////
/*
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //getSupportFragmentManager()
*/
        //////////////////////////////////////////////

// //////////////// FOR PLACES AUTO/ /////////////////////////////////////////////
        /*
        SupportPlaceAutocompleteFragment autocompleteFragment = (SupportPlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //Log.i(TAG, "Place: " + place.getName());
                Toast.makeText(getContext(), ""+place.getAddress(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
               // Log.i(TAG, "An error occurred: " + status);
                Toast.makeText(getContext(), "error"+status, Toast.LENGTH_SHORT).show();
            }
        });
        */
//////////////////////////////////////////////////////////////////////////////////////

        select = view.findViewById(R.id.SelectButton);
        upload = view.findViewById(R.id.UploadButton
        );

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                selectImage();

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
                Toast.makeText(getContext(), ""+media_landmark.getText(), Toast.LENGTH_SHORT).show();
            }
        });






/*
        // Spinner element
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
     /*   ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>();

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    */
    }

/////////////////for spinner




/*
    ///////////////
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }*/
    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }


    //////////////date picker
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getChildFragmentManager(), "datePicker");

    }

    //////////

    ///////////////////
//this is for AUTOTHING

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 777:
                if (requestCode ==IMG_REQUEST && resultCode == RESULT_OK && data!= null){

                    Uri path = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),path);
                        imageView.setImageBitmap(bitmap);
                        imageView.setVisibility(View.VISIBLE);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;

            case 1:

                if (requestCode == PLACE_PICKER_REQUEST) {
                    if (resultCode == RESULT_OK) {
                        Place place = PlacePicker.getPlace(data, activitym);
                        // String toastMsg = String.format("Place: %s", place.getName());
                        //Place place1 =place;

                        longlati= String.valueOf(place.getLatLng().longitude);
                        Toast.makeText(activitym, "\n"+longlati, Toast.LENGTH_LONG).show();
                    }
                }
                break;

            default:
                Toast.makeText(activitym, "no idea", Toast.LENGTH_SHORT).show();


        }






    }

 /////////////////



    public  String imageToString(){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgByte, android.util.Base64.DEFAULT);



    }

    public void uploadImage(){


        //mediatype = mediaType.getText().toString();
        medialandmark = media_landmark.getText().toString();
        mediafacing = media_facing.getText().toString();
        width = media_Width.getText().toString();
        height =  media_height.getText().toString();
        locality = media_locality.getText().toString();
        city = media_city.getText().toString();
        state = media_state.getText().toString();
        p3 = price3.getText().toString();
        p6 = price6.getText().toString();
        p12 = price1.getText().toString();
        available = availabeFrom.getText().toString();

       // text = findViewById(R.id.text);

        String Image = imageToString();

        retrofit2.Call<resp> call = userService1.mediaUpload(vendorID,mediatype,medialandmark
        ,mediafacing,width,height,locality,city,state,available,p3,p6,p12,Image);

        call.enqueue(new Callback<resp>() {
            @Override
            public void onResponse(Call<resp> call, Response<resp> response) {

                resp resp = response.body();
                Toast.makeText(getContext(), ""+resp.getResponse(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<resp> call, Throwable t) {

            }


        });


//text.setText(Image);
      //  Toast.makeText(getContext(), ""+Image.charAt(0), Toast.LENGTH_SHORT).show();


    }



    /////////////// FOR MAP FRAGMENT ?//////////////////////////
  /*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setScrollGesturesEnabled(true);
       // mMap.getUiSettings().setTiltGesturesEnabled(true);
     //   mMap.getUiSettings().setMyLocationButtonEnabled(true);
        //mMap.getUiSettings().setZoomGesturesEnabled(true);
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
*/
    //////////////////////////////////////////////////////////////////////
}