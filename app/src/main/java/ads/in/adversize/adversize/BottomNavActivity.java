package ads.in.adversize.adversize;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import ads.in.adversize.adversize.fragment.*;
import ads.in.adversize.adversize.fragment.HomeFragment;

public class BottomNavActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.disableShiftMode(navigation);


        /*if (savedInstanceState ==null ) {*/
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ads.in.adversize.adversize.HomeFragment()).commit();
            navigation.getMenu().getItem(0).setChecked(true);
        /*}*/


        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {

                case R.id.home:
                    Toast.makeText(BottomNavActivity.this, "done", Toast.LENGTH_SHORT).show();
                    selectedFragment =  new ads.in.adversize.adversize.HomeFragment();
                    break;
                  /*  mTextMessage.setText(R.string.title_home);*/
                // return true;
                case R.id.livecamp:
                    selectedFragment = new LiveFragment();
                    break;
                   /* mTextMessage.setText(R.string.title_dashboard);
                    return true;*/
                case R.id.addmed:
                    selectedFragment = new AddMediaFragment();
                    break;

                case R.id.map_ser:
                    selectedFragment = new MapSearchFragment();
                    break;
                case R.id.userpro:
                    selectedFragment = new ProfileAccountFragment();
                    break;
                default:
                    selectedFragment = new HomeFragment();
                   /* mTextMessage.setText(R.string.title_notifications);
                    return true;*/
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };


}
