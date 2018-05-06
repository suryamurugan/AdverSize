package ads.in.adversize.adversize;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ads.in.adversize.adversize.model.ResObj;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

boolean STATE=false;

    private Button login;
    private Button reg;
    private EditText usernameedit;
    private EditText passwordedit;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Hiding ActionBar
        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();

        // Userservice aka API //
        userService = ApiUtils.getUserService();

        // Referencing to UI component//
        usernameedit =findViewById(R.id.username);
        passwordedit = findViewById(R.id.password);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.reg);

        // Login Button Listner //
        login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String useremail = usernameedit.getText().toString();
                    String password = passwordedit.getText().toString();
                    // validate form //
                    if(validateLogin(useremail,password)){
                        doLogin(useremail,password);
                    }
                }
            });

        // Register Button Listner //
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                retrofit2.Call<ResObj> call = userService.login(
                        "surya3542live@gmail.com", "openopen");
                call.enqueue(new Callback<ResObj>() {
                    @Override
                    public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                        if(response.isSuccessful()){
                            ResObj resObj = response.body();
                            if(resObj.getMessage().equals("true")) {
                     Toast.makeText(Main2Activity.this,
                             "Correct"+resObj.getVendorID()+resObj.getEmail()+resObj.getPhoneNumber()
                             , Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Main2Activity.this
                                        , "Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResObj> call, Throwable t) {
                        Toast.makeText(Main2Activity.this,
                                t.getMessage()+"screwed", Toast.LENGTH_SHORT).show();
                    }
                });*/

                Intent intent= new Intent(Main2Activity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
            // VALIDATION for Login //
    private  boolean validateLogin(String useremail, String password){

        if(useremail ==null || useremail.trim().length() ==0 ){
            Toast.makeText(this, "useremail is required", Toast.LENGTH_SHORT).show();
            return false;

        }
        if(password ==null || password.trim().length() ==0 ){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

    private  void doLogin(final String useremail, final String password){

        retrofit2.Call<ResObj> call = userService.login(useremail, password);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(retrofit2.Call<ResObj> call, Response<ResObj> response) {

                if(response.isSuccessful()){
                    ResObj resObj = response.body();
                    if(resObj.getMessage().equals("true")) {
                        // login start main


                        Toast.makeText(Main2Activity.this, "Correct"+resObj.getVendorID()+resObj.getEmail()+resObj.getPhoneNumber(), Toast.LENGTH_SHORT).show();
//
                        ///userLocalStore.storeUserData(returnedUser);
                        User user = new User(useremail, password);
                        String vendorid  = resObj.getVendorID();
                        user.username = resObj.getFirstName()+" "+resObj.getLastName();
                        user.vedorid=  Integer.parseInt(vendorid);
                        Toast.makeText(Main2Activity.this, ""+vendorid, Toast.LENGTH_SHORT).show();
                        UserLocalStore userLocalStore = new UserLocalStore(getApplicationContext());
                        userLocalStore.storeUserData(user);

                        userLocalStore.setUserLoggedIn(true);
                       // Intent i = new Intent(Main2Activity.this,AdverSizeHomeNavDrawActivity.class);

                        /*Intent i = new Intent(Main2Activity.this,BottomNavActivity.class);*/
                        Intent i = new Intent(Main2Activity.this,Main7Activity.class);
                        startActivity(i);
                        finish();
                  }

               else{

                        Toast.makeText(Main2Activity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    }
                }


                else {
                    Toast.makeText(Main2Activity.this, "Error! Please try again", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResObj> call, Throwable t) {
                Toast.makeText(Main2Activity.this, t.getMessage()+"screwed", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
