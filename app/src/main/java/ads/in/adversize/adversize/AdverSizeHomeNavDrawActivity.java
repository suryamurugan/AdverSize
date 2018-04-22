package ads.in.adversize.adversize;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AdverSizeHomeNavDrawActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView username_profile,useremail_profile;

UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adver_size_home_nav_draw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        username_profile = headerView.findViewById(R.id.username_profile);
        useremail_profile = headerView.findViewById(R.id.usermailid_profile);


//useremail_profile = findViewById(R.id.username_profile);
//username_profile = findViewById(R.id.username_profile);

        userLocalStore = new UserLocalStore(getApplicationContext());
        User user = userLocalStore.getLoggedInUser();
        username_profile.setText(user.username);
        useremail_profile.setText(user.useremail);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState ==null ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.screen_area, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home_fragment);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.adver_size_home_nav_draw, menu);
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
            UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
            userLocalStore.clearUserData();
            Intent intent = new Intent(AdverSizeHomeNavDrawActivity.this,SplashActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();


        if (id == R.id.home_fragment) {
            fragment = new HomeFragment();
            // Handle the camera action
        } else if (id == R.id.add_media_fragment) {

            fragment = new AddMediaFragment();

        } else if (id == R.id.live_camp_fragment) {
            fragment = new LiveFragment();

        }
        //else if (id == R.id.map_search_fragment) {
          //  fragment = new MapSearchFragment();
       // }

        else if (id == R.id.map_search_fragment){

            fragment = new MapSearchFragment();
        }
        else if (id == R.id.profile_account) {
            fragment = new ProfileAccountFragment();

        } else if (id == R.id.contact_us) {

        }
        if (fragment!=null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.screen_area,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
