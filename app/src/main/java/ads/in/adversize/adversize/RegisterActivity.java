package ads.in.adversize.adversize;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    CheckBox iagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iagree = findViewById(R.id.iagree);
       // iagree.setBackgroundColor(getResources().getColor(R.color.colorAccent));
       // iagree.setOnClickListener(first_radio_listener);
       // iagree.toggle();

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();
    }


}
