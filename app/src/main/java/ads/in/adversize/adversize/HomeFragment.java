package ads.in.adversize.adversize;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;

import android.support.v4.view.MenuItemCompat;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeAdaptorMedia adaptorMedia;

 //   TextView idk,fullname,Company,city,Email,website,navname,mailtx;
    UserService userService;
    private EditText searchBox;
    final ArrayList<MediaObject> data=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recycler_view_home);
        adaptorMedia = new HomeAdaptorMedia(getContext(), data);
        recyclerView.setAdapter(adaptorMedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      //  recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        userService = ApiUtils.getUserService();

        ////////////// DATE




       ///////////////



       /* SearchView searchView = view.findViewById(R.id.seach_view);
        search(searchView);*/

        UserLocalStore userLocalStore = new UserLocalStore(getActivity());
       User user = userLocalStore.getLoggedInUser();
       String vid = String.valueOf(user.vedorid);




        /*Toast.makeText(getContext(), ""+vid, Toast.LENGTH_SHORT).show();*/
        retrofit2.Call<ArrayList<MediaObject>> call = userService.vendorm(String.valueOf("44"));
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

                   /* MediaObject fishData = new MediaObject();
                    fishData.setMediaWidth(response.body().get(i).getMediaWidth());
                    fishData.setMediaHeight(response.body().get(i).getMediaHeight());
                    fishData.setMediaName(response.body().get(i).getMediaName());
                    fishData.setMediaImgLocation(response.body().get(i).getMediaImgLocation());*/
                    data.add(i,response.body().get(i));
                  /*  data.add(fishData);*/
                    adaptorMedia.notifyDataSetChanged();

                }






            }

            @Override
            public void onFailure(Call<ArrayList<MediaObject>> call, Throwable t) {

                //Toast.makeText(getContext(), "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

    }
    ///////////////////////////////
    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

               // adaptorMedia.getFilter().filter(newText);
                return true;
            }
        });
    }

    /////////////


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        /*super.onCreateOptionsMenu(menu, inflater);*/
        inflater.inflate(R.menu.menu_main,menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        /*return true;*/
    }
}
