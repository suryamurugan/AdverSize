package ads.in.adversize.adversize;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.model.resp;
import ads.in.adversize.adversize.remote.ApiUtils;
import ads.in.adversize.adversize.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by suryamurugan on 4/4/18.
 */

public class ProfileAccountFragment extends Fragment {


    private Button changepassword;

    UserLocalStore userLocalStore;
    UserService userService;
    User user;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userService = ApiUtils.getUserService();
        userLocalStore = new UserLocalStore(getContext());

       user = userLocalStore.getLoggedInUser();

        changepassword = view.findViewById(R.id.changePassword);

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

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Change Password");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user = currentpassword.getText().toString();
                        final String pass = newpassword.getText().toString();
                        String cpass = confirmnewpassword.getText().toString();

                        if(pass.equals(cpass)){
                            Toast.makeText(getContext(), "Username: " + user + " Email: " + pass+"\n"+cpass, Toast.LENGTH_SHORT).show();

                            retrofit2.Call<resp> call = userService.changepassword(userLocalStore.getLoggedInUser().useremail,user,pass);
                            call.enqueue(new Callback<resp>() {
                                @Override
                                public void onResponse(Call<resp> call, Response<resp> response) {
                                    Toast.makeText(getActivity(), "YAY BRO Password changed to "+pass, Toast.LENGTH_SHORT).show();

                                    userLocalStore.clearUserData();
                                    Intent intent = new Intent(getActivity(),Main2Activity.class);

                                    startActivity(intent);
                                    getActivity().finish();
                                }

                                @Override
                                public void onFailure(Call<resp> call, Throwable t) {
                                    Toast.makeText(getActivity(), "Go get a life"+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                        else {

                            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();


            }
        });

        Button logo = view.findViewById(R.id.log_out);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLocalStore.clearUserData();
                Intent intent = new Intent(getContext(),Main2Activity.class);
                getActivity().finish();
                startActivity(intent);



            }
        });



    }
}
