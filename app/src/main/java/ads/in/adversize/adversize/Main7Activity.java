package ads.in.adversize.adversize;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class Main7Activity extends AppCompatActivity {

    private TextView mTextMessage;
    ActionBar actionBar;
    TextView maintitle;
    ConstraintLayout.LayoutParams layoutParams;

    MenuItem ii;


   /* private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#171717")));



        Typeface tf  = Typeface.createFromAsset(getAssets(),"cabin_semibold.ttf");


       /* mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/
        BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);

   //     bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
        bnve.setTextVisibility(true);
        bnve.setTypeface(tf);
        //bnve.setPadding(4,4,4,4);
       // bnve.setIconSize(50,50);
        bnve.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

      /*  // set icon size
        int iconSize = 30;
        bnve.setIconSize(iconSize, iconSize);
        // set item height
        bnve.setItemHeight(BottomNavigationViewEx.dp2px(this, iconSize + 16));*/




///////////////////////////////////////////////////





        Typeface tef  = Typeface.createFromAsset(getAssets(),"cabin_semibold.ttf");


        // Hiding ActionBa
        maintitle = new TextView(getApplicationContext());
        layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        maintitle.setLayoutParams(layoutParams);
        maintitle.setText("AdverSize");
        maintitle.setGravity(Gravity.CENTER);
        maintitle.setTextSize(22);
        maintitle.setPadding(100,2,5,2);
        maintitle.setTextColor(Color.parseColor("#ffffff"));
        maintitle.setTypeface(tef);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(maintitle);






        /////////





        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ads.in.adversize.adversize.HomeFragment()).commit();
        bnve.getMenu().getItem(0).setChecked(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (id == R.id.action_name) {
            Toast.makeText(this, "Clicked BRo", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(Main7Activity.this,ProfileActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {

                case R.id.home:
                  //  Toast.makeText(Main7Activity.this, "done", Toast.LENGTH_SHORT).show();
                    selectedFragment =  new ads.in.adversize.adversize.HomeFragment();

                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                    break;
                  /*  mTextMessage.setText(R.string.title_home);*/
                // return true;
                case R.id.livecamp:
                    selectedFragment = new LiveFragment();

                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                    break;
                   /* mTextMessage.setText(R.string.title_dashboard);
                    return true;*/
                case R.id.addmed:
                  /*  selectedFragment = new AddMediaFragment();*/
                    selectedFragment = new HomeFragment();
                    Intent intent= new Intent(Main7Activity.this,AddMediaDetails.class);
                    startActivity(intent);
                    break;

                case R.id.map_ser:
                    selectedFragment = new MapSearchFragment();

                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                    break;
                case R.id.userpro:
                   // selectedFragment = new ProfileAccountFragment();
                    break;
                default:
                    selectedFragment = new HomeFragment();

                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                   /* mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }

            return true;
        }
    };



}
