package ads.in.adversize.adversize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main5Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdaptorMedia adaptorMedia;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        final List<MediaObject> data=new ArrayList<>();



        //  for(int i=0;i<jsonArray.length();i++){
        // JSONObject json_data = jsonArray.getJSONObject(i);
       // MediaObject fishData = new MediaObject();

       // fishData.setMediaName("yolo");
       // MediaObject fishData1 = new MediaObject();

      //  fishData1.setMediaName("yolo");

        // fishData.mediaTotalPrice= json_data.getString("mediaTotalPrice");
        //  fishData.mediaRating = json_data.getString("mediaRating");
        // fishData.catName= json_data.getString("cat_name");
        // fishData.sizeName= json_data.getString("size_name");
        // fishData.price= json_data.getInt("price");
      ///  data.add(fishData);
       // data.add(fishData1);
        // Setup and Handover data to recyclerview
        recyclerView = findViewById(R.id.recycler_view);
        adaptorMedia = new AdaptorMedia(Main5Activity.this, data);
        recyclerView.setAdapter(adaptorMedia);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));




        // for retrofit//


        userService = ApiUtils.getUserService();

     /*   retrofit2.Call<List<MediaObject>> call = userService.vendorm("44");

        call.enqueue(new Callback<List<MediaObject>>() {
            @Override
            public void onResponse(Call<List<MediaObject>> call, Response<List<MediaObject>> response) {



                for (int i=0;i<response.body().size();i++) {
                    MediaObject fishData = new MediaObject();
                    fishData.setMediaName(response.body().get(i).getMediaName());
                    fishData.setMediaImgLocation(response.body().get(i).getMediaImgLocation());

                    data.add(fishData);
                    adaptorMedia.notifyDataSetChanged();

                }
                for (int i=0;i<100;i++) {
                    MediaObject fishData = new MediaObject();
                    fishData.setMediaName(response.body().get(0).getMediaName());
                    fishData.setMediaImgLocation(response.body().get(0).getMediaImgLocation());

                    data.add(fishData);
                    adaptorMedia.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<List<MediaObject>> call, Throwable t) {

                Toast.makeText(Main5Activity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });



*/





    }
}
