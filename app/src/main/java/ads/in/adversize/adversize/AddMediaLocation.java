package ads.in.adversize.adversize;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.model.MediaUploadObject;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMediaLocation extends AppCompatActivity {

    UserService userService1;
    String string;
    Gson gson = new Gson();
    Button finall;
    MediaUploadObject mediaObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_media_location);
//mediaObject = new MediaObject();

        userService1 = ApiUtils.getUserService();

        Bundle extras = getIntent().getExtras();
        string= extras.getString("myjson");
    mediaObject = gson.fromJson(string, MediaUploadObject.class);
        Toast.makeText(this, ""+mediaObject, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ""+mediaObject.getMediaLandmark(), Toast.LENGTH_SHORT).show();
        finall = findViewById(R.id.finall);

        ///////////////////////
        final int vendorID= 44;
        final String Image="";
        ////////////////

        finall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit2.Call<Void> call = userService1.mediaUpload(
                        String.valueOf(vendorID),mediaObject.getMediaType(),mediaObject.getMediaLandmark()
                        ,mediaObject.getMediaFacing(),mediaObject.getMediaWidth(),
                        mediaObject.getMediaHeight(),mediaObject.getMediaLocality(),
                        mediaObject.getMediaCity(),mediaObject.getMediaState(),
                        mediaObject.getMediaAvailability(),mediaObject.getMediaPrice3(),
                        mediaObject.getMediaPrice6(),mediaObject.getMediaPrice12(),Image,mediaObject.getMediaGoogleLongitude(),
                        mediaObject.getMediaGoogleLatitude());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddMediaLocation.this);
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
                        Toast.makeText(AddMediaLocation.this, "Failed bro"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddMediaLocation.this);
                        builder.setMessage("Failed")
                                .setTitle("DONE")
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(AddMediaLocation.this, "don", Toast.LENGTH_SHORT).show();
                                        FragmentTransaction ft;
                                       /* ft=getFragmentManager().beginTransaction();
                                        ft.replace(R.id.screen_area, new AddMediaFragment());
                                        ft.commit();*/
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                    }
                });

            }
        });

    }
}
