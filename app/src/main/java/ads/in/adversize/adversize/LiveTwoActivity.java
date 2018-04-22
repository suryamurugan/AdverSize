package ads.in.adversize.adversize;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveTwoActivity extends AppCompatActivity {

    TextView landmarkname,mediasize,mediacost;
    ImageButton  mediaimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_two);

        Bundle extras = getIntent().getExtras();
        String string= extras.getString("myjson");
        //Toast.makeText(this, ""+string, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        MediaObject mediaObject =gson.fromJson(string,MediaObject.class);

        ImageButton imageButton= findViewById(R.id.mediaImg);
        landmarkname= findViewById(R.id.landmarkName);
        mediasize = findViewById(R.id.mediaSize);
        mediacost = findViewById(R.id.mediaCost);

        landmarkname.setText(mediaObject.getMediaName());
        mediasize.setText(""+mediaObject.getMediaWidth()+" * "+mediaObject.getMediaHeight());
        mediacost.setText(mediaObject.getMediaTotalPrice6());
        Toast.makeText(this, ""+mediaObject.getMediaID(), Toast.LENGTH_SHORT).show();
        Glide.with(LiveTwoActivity.this).load("https://adversize.in/" +mediaObject.getMediaImgLocation()).into(imageButton);

    }
}
