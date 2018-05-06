package ads.in.adversize.adversize;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ads.in.adversize.adversize.model.resp;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ActionBar actionBar;
    TextView maintitle;
    ConstraintLayout.LayoutParams layoutParams;

    private Button changepassword;

    UserLocalStore userLocalStore;
    UserService userService;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        actionBar= getSupportActionBar();


        Typeface tef  = Typeface.createFromAsset(getAssets(),"cabin_semibold.ttf");


        // Hiding ActionBa
        maintitle = new TextView(getApplicationContext());
        layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        maintitle.setLayoutParams(layoutParams);
        maintitle.setText("AdverSize");
        maintitle.setGravity(Gravity.CENTER);
        maintitle.setTextSize(22);
        maintitle.setPadding(10,2, 5,2);
        maintitle.setTextColor(Color.parseColor("#171717"));
        maintitle.setTypeface(tef);
       actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(maintitle);

        userService = ApiUtils.getUserService();
        userLocalStore = new UserLocalStore(ProfileActivity.this);

        user = userLocalStore.getLoggedInUser();

        changepassword = findViewById(R.id.changePassword);

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
                final EditText currentpassword = alertLayout.findViewById(R.id.currentpassword);
                final EditText newpassword = alertLayout.findViewById(R.id.newpassword);
                final EditText confirmnewpassword = alertLayout.findViewById(R.id.confirmnewpassword);


                final CheckBox cbToggle = alertLayout.findViewById(R.id.cb_show_pass);

                cbToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // to encode password in dots
                            newpassword.setTransformationMethod(null);
                            currentpassword.setTransformationMethod(null);
                            confirmnewpassword.setTransformationMethod(null);

                        } else {
                            // to display the password in normal text

                            newpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            currentpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            confirmnewpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                    }
                });

                AlertDialog.Builder alert = new AlertDialog.Builder(ProfileActivity.this);
                alert.setTitle("Change Password");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProfileActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user = currentpassword.getText().toString();
                        final String pass = newpassword.getText().toString();
                        String cpass = confirmnewpassword.getText().toString();

                        if(pass.equals(cpass)){
                            Toast.makeText(ProfileActivity.this, "Username: " + user + " Email: " + pass+"\n"+cpass, Toast.LENGTH_SHORT).show();

                            retrofit2.Call<resp> call = userService.changepassword(userLocalStore.getLoggedInUser().useremail,user,pass);
                            call.enqueue(new Callback<resp>() {
                                @Override
                                public void onResponse(Call<resp> call, Response<resp> response) {
                                    Toast.makeText(ProfileActivity.this, "YAY BRO Password changed to "+pass, Toast.LENGTH_SHORT).show();

                                    userLocalStore.clearUserData();
                                    Intent intent = new Intent(ProfileActivity.this,Main2Activity.class);

                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<resp> call, Throwable t) {
                                    Toast.makeText(ProfileActivity.this, "Go get a life"+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                        else {

                            Toast.makeText(ProfileActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();


            }
        });

        Button logo = findViewById(R.id.log_out);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLocalStore.clearUserData();
                Intent intent = new Intent(ProfileActivity.this,Main2Activity.class);
                finish();
                startActivity(intent);



            }
        });

    }
}
