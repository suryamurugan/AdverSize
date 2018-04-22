package ads.in.adversize.adversize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapSearchActivity extends AppBaseActivity {
    @BindView(R.id.toolbar)
    public Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolBar.setTitle(getResources().getString(R.string.tournament));
        setSupportActionBar(toolBar);

        DrawerUtil.getDrawer(this, toolBar);
    }
}


