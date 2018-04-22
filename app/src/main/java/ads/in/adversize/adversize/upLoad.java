package ads.in.adversize.adversize;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import ads.in.adversize.adversize.model.ResObj;
import ads.in.adversize.adversize.model.resp;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class upLoad extends AppCompatActivity {

    ImageView imageView;
    Button selectButton, UploadButton;
    private static final int IMG_REQUEST =777;
    Bitmap bitmap;
    UserService userService1;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load);
        userService1 = ApiUtils.getUserService();

        selectButton = findViewById(R.id.select);
        UploadButton = findViewById(R.id.upload);
        imageView = findViewById(R.id.image);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectImage();
            }
        });

        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();

            }
        });




    }

    public  void  selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==IMG_REQUEST && resultCode == RESULT_OK && data!= null){

            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }



    public  String imageToString(){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgByte, android.util.Base64.DEFAULT);



    }

    public void uploadImage(){

        text = findViewById(R.id.text);

        String Image = imageToString();

        retrofit2.Call<resp> call = userService1.uploadImage(Image,"olaola");

        call.enqueue(new Callback<resp>() {
            @Override
            public void onResponse(Call<resp> call, Response<resp> response) {

                resp resp = response.body();
                Toast.makeText(upLoad.this, ""+resp.getResponse(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<resp> call, Throwable t) {

            }


        });


//text.setText(Image);
        Toast.makeText(this, ""+Image.charAt(0), Toast.LENGTH_SHORT).show();


    }
}
