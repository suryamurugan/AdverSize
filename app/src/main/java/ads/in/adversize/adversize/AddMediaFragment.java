package ads.in.adversize.adversize;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.model.MediaUploadObject;
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

    MediaUploadObject mediaObject;
    ImageView imageView;
    private static final int IMG_REQUEST = 777;
    int PLACE_PICKER_REQUEST = 1;
    Bitmap bitmap;
    UserLocalStore userLocalStore;
    UserService userService1;
    // 10 fields
    EditText media_landmark, media_facing, media_Width, media_height, media_locality, media_city, media_state, price3, price6, price1;
    // 2 fields
    Button availabeFrom,select, upload;
    ImageButton longlat;
    TextView tvlong,tvlat;
    String vendorID;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    public  Activity activitym;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activitym = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addmedia_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userService1 = ApiUtils.getUserService();

        mediaObject = new MediaUploadObject();


        userLocalStore = new UserLocalStore(getContext());
        vendorID = String.valueOf(userLocalStore.getLoggedInUser().vedorid);

        imageView = view.findViewById(R.id.image);
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
        tvlong = view.findViewById(R.id.tvlongitude);
        tvlat = view.findViewById(R.id.tvlatitude);
        tvlong.setText("Longitude : "+mediaObject.getMediaGoogleLongitude());
        tvlong.setText("Latitude : "+mediaObject.getMediaGoogleLatitude());

        select = view.findViewById(R.id.SelectButton);
        upload = view.findViewById(R.id.UploadButton);



        spinner = view.findViewById(R.id.planets_spinner);



        availabeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        longlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        adapter= ArrayAdapter.createFromResource(getContext(),R.array.mediaTypeArray,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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

                Toast.makeText(activitym, ""+mediaObject.getMediaCity(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void selectImage() {

        // Getting all data from editText
       // mediaObject.setMediaType(); //alreadyset
        //av
        mediaObject.setMediaLandmark(media_landmark.getText().toString());
        //facinhg
        mediaObject.setMediaWidth(media_Width.getText().toString());
        mediaObject.setMediaHeight(media_height.getText().toString());
        mediaObject.setMediaLocality(media_locality.getText().toString());
        mediaObject.setMediaCity(media_city.getText().toString());
        mediaObject.setMediaState(media_state.getText().toString());
       mediaObject.setMediaAvailability(availabeFrom.getText().toString());
        Toast.makeText(activitym, ""+availabeFrom.getText(), Toast.LENGTH_SHORT).show();
        /*mediaObject.setMediaGoogleLatitude(); /// added in picker
        mediaObject.setMediaGoogleLongitude();*/
        mediaObject.setMediaPrice3(price3.getText().toString());
        mediaObject.setMediaPrice6(price6.getText().toString());
        mediaObject.setMediaPrice12(price1.getText().toString());

        // Intent to pick and image for upload
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);

      /*  CropImage.activity()
                .start(getContext(), this);*/
    }

    //////////////date picker
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getChildFragmentManager(), "datePicker");


    }

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
                        mediaObject.setMediaGoogleLatitude(String.valueOf(place.getLatLng().latitude));
                        mediaObject.setMediaGoogleLongitude(String.valueOf(place.getLatLng().longitude));
                        tvlong.setText("Longitude : "+mediaObject.getMediaGoogleLongitude());
                        tvlat.setText("Latitude : "+mediaObject.getMediaGoogleLatitude());
                    }
                }
                break;

         /*   case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    if (resultCode == RESULT_OK) {
                        Uri resultUri = result.getUri();
                    } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                        Exception error = result.getError();
                    }

                   // cropImageView.setImageUriAsync(uri);
                }
                break;
*/
            default:
                Toast.makeText(activitym, "no idea", Toast.LENGTH_SHORT).show();
        }
    }
    public  String imageToString(){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgByte, android.util.Base64.DEFAULT);



    }

    public void uploadImage(){
        String Image = imageToString();

        retrofit2.Call<Void> call = userService1.mediaUpload(
                vendorID,mediaObject.getMediaType(),mediaObject.getMediaLandmark()
        ,mediaObject.getMediaFacing(),mediaObject.getMediaWidth(),
                mediaObject.getMediaHeight(),mediaObject.getMediaLocality(),
                mediaObject.getMediaCity(),mediaObject.getMediaState(),
                mediaObject.getMediaAvailability(),mediaObject.getMediaPrice3(),
                mediaObject.getMediaPrice6(),mediaObject.getMediaPrice12(),Image,mediaObject.getMediaGoogleLongitude(),
                mediaObject.getMediaGoogleLatitude());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("YOLO")
                        .setTitle("DONE")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(activitym, "Failed bro"+t.getMessage(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Failed")
                        .setTitle("DONE")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "don", Toast.LENGTH_SHORT).show();
                                FragmentTransaction ft;
                                ft=getFragmentManager().beginTransaction();
                                ft.replace(R.id.screen_area, new AddMediaFragment());
                                ft.commit();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }
}

