package ads.in.adversize.adversize;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import ads.in.adversize.adversize.model.MediaObject;

public class AddMediaPricing extends AppCompatActivity {

    Button mediapricingd;

    EditText price3, price6, price1;

    String string;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_media_pricing);

        Bundle extras = getIntent().getExtras();
        string= extras.getString("myjson");
        final MediaObject mediaObject = gson.fromJson(string, MediaObject.class);

        price6 = findViewById(R.id.price6);
        price1 = findViewById(R.id.price1);
        price3 = findViewById(R.id.price3);




        Toast.makeText(this, ""+string, Toast.LENGTH_SHORT).show();
        mediapricingd= findViewById(R.id.mediapricingdone);
        mediapricingd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaObject.setMediaPrice(price3.getText().toString());
                mediaObject.setMediaPrice6(price6.getText().toString());
                mediaObject.setMediaPrice12(price1.getText().toString());

                Gson gson = new Gson();
                String myJson = gson.toJson(mediaObject);

                Intent intent= new Intent(AddMediaPricing.this, AddMediaLocation.class);
                intent.putExtra("myjson", myJson);
                startActivity(intent);
            }
        });

    }


    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
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
