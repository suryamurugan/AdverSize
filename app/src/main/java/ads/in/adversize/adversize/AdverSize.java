package ads.in.adversize.adversize;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class AdverSize extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private RecyclerView recyclerView;
    private AdaptorMedia adaptorMedia;
    TextView idk,fullname,Company,city,Email,website,navname,mailtx;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fullname = findViewById(R.id.user_profile_name);
        Company = findViewById(R.id.Company);
        city = findViewById(R.id.city);
        Email = findViewById(R.id.Email);
        website = findViewById(R.id.website);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navname = navigationView.findViewById(R.id.navName);
        mailtx = navigationView.findViewById(R.id.mailtextView);


       // mailtx = findViewById(R.id.mailtextView);


        //navname.setText("Surya");
       // mailtx.setText("surya3542live@gmail.com");
        ///////////////////////
        final List<MediaObject> data=new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adaptorMedia = new AdaptorMedia(AdverSize.this, data);
        recyclerView.setAdapter(adaptorMedia);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
      //  recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ///////////////////////


        final SharedPreferences sp1=this.getSharedPreferences("Login", MODE_PRIVATE);


        String unm=sp1.getString("username", null);
        idk = findViewById(R.id.user_profile_name);
        idk.setText(unm);
        Toast.makeText(this, ""+unm, Toast.LENGTH_SHORT).show();
        //////////


                FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // for retrofit//


                userService = ApiUtils.getUserService();
// working
               /* retrofit2.Call<List<MediaObject>> call = userService.vendorm("17");


                call.enqueue(new Callback<List<MediaObject>>() {
                    @Override
                    public void onResponse(Call<List<MediaObject>> call, Response<List<MediaObject>> response) {
                      //  Toast.makeText(AdverSize.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();

                    //    prepareMovieData();

                        //for(int i=0 ; i< response.body().size();i++){
                        //MediaObject movie = new MediaObject();
                        //List<MediaObject> mediaObjects = response.body();
                        String firstName=sp1.getString("firstName", null);
                        String lastName=sp1.getString("lastName", null);
                        String Full = ""+firstName+" "+lastName;
                        fullname.setText(Full);

                     //  navname.setText(Full);
                        String company = sp1.getString("Company",null);
                        Company.setText(company);
                        String ciity = sp1.getString("city",null);
                        city.setText(ciity);
                        String Eemail = sp1.getString("Email",null);
                        Email.setText(Eemail);

                    //    mailtx.setText(Eemail);
                        String wwebsite = sp1.getString("website",null);
                        website.setText(wwebsite);



                          //  Toast.makeText(AdverSize.this, ""+mediaObjects.size(), Toast.LENGTH_SHORT).show();


                        for (int i=0;i<response.body().size();i++) {
                            MediaObject fishData = new MediaObject();
                            fishData.setMediaName(response.body().get(i).getMediaName());
                            fishData.setMediaImgLocation(response.body().get(i).getMediaImgLocation());

                            data.add(fishData);
                            adaptorMedia.notifyDataSetChanged();

                        }






                    }

                    @Override
                    public void onFailure(Call<List<MediaObject>> call, Throwable t) {

                        Toast.makeText(AdverSize.this, "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });*/
/*
                call.enqueue(new Callback<JSONArray>() {
                    @Override
                    public void onResponse(Call<JSONArray> call, Response<JSONArray> response) {

                        JSONArray jsonArray= response.body();

                        Toast.makeText(AdverSize.this, ""+jsonArray.length(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<JSONArray> call, Throwable t) {

                        Toast.makeText(AdverSize.this, "Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

*/
                //////
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

      //  NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home_fragment) {
            // Handle the camera action
        } else if (id == R.id.add_media_fragment) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.profile_account) {

        } else if (id == R.id.contact_us) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
