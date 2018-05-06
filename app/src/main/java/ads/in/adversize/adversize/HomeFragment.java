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

    // RETROFIT Interface
    UserService userService;

    // RECYCLERVIEW SETUP
    private RecyclerView recyclerView;
    private HomeAdaptorMedia adaptorMedia;
    final ArrayList<MediaObject> data=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // RECYCLERVIEW SETUP
        recyclerView = view.findViewById(R.id.recycler_view_home);
        adaptorMedia = new HomeAdaptorMedia(getContext(), data);
        recyclerView.setAdapter(adaptorMedia);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userService = ApiUtils.getUserService(); // API SERVICE

        //  Get current user data from USERLOCALSTORE CLASS
        UserLocalStore userLocalStore = new UserLocalStore(getActivity());
        User user = userLocalStore.getLoggedInUser();
        // String vid = String.valueOf(user.vedorid);
        String vid = "44";


        // RETROFIT API CALL // vendorm.php returns JSON[{media},{media},......] for GET['id']
        retrofit2.Call<ArrayList<MediaObject>> call = userService.vendorm(String.valueOf("44"));
        call.enqueue(new Callback<ArrayList<MediaObject>>() {
            @Override
            public void onResponse(Call<ArrayList<MediaObject>> call, Response<ArrayList<MediaObject>> response) {
                for (int i=0;i<response.body().size();i++) {
                    data.add(i,response.body().get(i));
                    // this could be an issue
                    adaptorMedia.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MediaObject>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
