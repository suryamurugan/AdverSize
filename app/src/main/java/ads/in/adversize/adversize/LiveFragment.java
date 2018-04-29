package ads.in.adversize.adversize;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class LiveFragment extends Fragment {
    private RecyclerView recyclerView;
    private LiveCOneAdaptorMedia adaptorMedia;
    //   TextView idk,fullname,Company,city,Email,website,navname,mailtx;
    UserService userService;

    UserLocalStore userLocalStore;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.live_camp_fragment,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final List<MediaObject> data=new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_live);
        adaptorMedia = new LiveCOneAdaptorMedia(getContext(), data);
        recyclerView.setAdapter(adaptorMedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),1));
        //  recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        userService = ApiUtils.getUserService();
        userLocalStore = new UserLocalStore(getContext());

        retrofit2.Call<ArrayList<MediaObject>> call = userService.vendorm(String.valueOf(userLocalStore.getLoggedInUser().vedorid));
        call.enqueue(new Callback<ArrayList<MediaObject>>() {
            @Override
            public void onResponse(Call<ArrayList<MediaObject>> call, Response<ArrayList<MediaObject>> response) {
                //  Toast.makeText(AdverSize.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();

                //    prepareMovieData();

                //for(int i=0 ; i< response.body().size();i++){
                //MediaObject movie = new MediaObject();
                //List<MediaObject> mediaObjects = response.body();
                //String firstName=sp1.getString("firstName", null);
                //String lastName=sp1.getString("lastName", null);
                //String Full = ""+firstName+" "+lastName;
                //fullname.setText(Full);

                //  navname.setText(Full);

               /* String company = sp1.getString("Company",null);
                Company.setText(company);
                String ciity = sp1.getString("city",null);
                city.setText(ciity);
                String Eemail = sp1.getString("Email",null);
                Email.setText(Eemail);

                //    mailtx.setText(Eemail);
                String wwebsite = sp1.getString("website",null);
                website.setText(wwebsite);
*/


                //  Toast.makeText(AdverSize.this, ""+mediaObjects.size(), Toast.LENGTH_SHORT).show();


                for (int i=0;i<response.body().size();i++) {
                    MediaObject fishData = new MediaObject();
                    fishData.setMediaName(response.body().get(i).getMediaName());
                    fishData.setMediaImgLocation(response.body().get(i).getMediaImgLocation());
                    fishData.setMediaID(response.body().get(i).getMediaID());
                    fishData.setMediaHeight(response.body().get(i).getMediaHeight());
                    fishData.setMediaWidth(response.body().get(i).getMediaWidth());
                    data.add(fishData);
                    adaptorMedia.notifyDataSetChanged();

                }






            }

            @Override
            public void onFailure(Call<ArrayList<MediaObject>> call, Throwable t) {

                //Toast.makeText(getContext(), "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }
}
