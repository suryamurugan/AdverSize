package ads.in.adversize.adversize;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import ads.in.adversize.adversize.model.MediaUploadObject;
import ads.in.adversize.adversize.remote.UserService;

public class AddMediaDetails extends AppCompatActivity {
    MediaUploadObject mediaObject;
    ImageView imageView,mediadetailstick;
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

    String s1;

    Button mediadetailsd;
    ArrayAdapter<CharSequence> adapter;

    public Activity activitym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_media_details);
        mediaObject = new MediaUploadObject();


        mediadetailstick = findViewById(R.id.mediadetailsT);

        media_landmark = findViewById(R.id.media_landmark);
        media_facing = findViewById(R.id.media_facing_towards);
        media_Width = findViewById(R.id.media_width);
        media_height = findViewById(R.id.media_height);
        media_locality = findViewById(R.id.media_locality);
        media_city = findViewById(R.id.media_city);
        media_state = findViewById(R.id.media_state);
        availabeFrom = findViewById(R.id.avaibaleFrom);

        spinner = findViewById(R.id.planets_spinner);


        mediadetailsd = findViewById(R.id.mediadetailsdone);

        mediadetailsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mediaObject.setMediaAvailability(availabeFrom.getText().toString());
                mediaObject.setMediaLandmark(media_landmark.getText().toString());
                mediaObject.setMediaFacing(media_facing.getText().toString());
                mediaObject.setMediaWidth(media_Width.getText().toString());
                mediaObject.setMediaHeight(media_height.getText().toString());
                mediaObject.setMediaLocality(media_locality.getText().toString());
                mediaObject.setMediaCity(media_city.getText().toString());
                mediaObject.setMediaState(media_state.getText().toString());
                mediaObject.setMediaAvailability(availabeFrom.getText().toString());


                Gson gson = new Gson();
                String myJson = gson.toJson(mediaObject);






                Intent i = new Intent(AddMediaDetails.this,AddMediaPricing.class);
                i.putExtra("myjson", myJson);

                startActivity(i);
                mediadetailstick.setDrawingCacheBackgroundColor(Color.GREEN);




            }
        });

        availabeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        adapter= ArrayAdapter.createFromResource(AddMediaDetails.this,R.array.mediaTypeArray,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                //Showing selected spinner item
                if (adapterView.getSelectedItemPosition() == 0){
                    Toast.makeText(AddMediaDetails.this, "Please select Media Type", Toast.LENGTH_SHORT).show();
                }
                else{
                   mediaObject.setMediaType(item);

                    Toast.makeText(AddMediaDetails.this, ""+item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });




    }

    //////////////date picker
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");


                //...show(, "datePicker");


    }


    @Override
    public void finish() {
        super.finish();
       /* onLeaveThisActivity();*/
    }

    protected void onLeaveThisActivity() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }



    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent); onStartNewActivity();

    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

}
