package ads.in.adversize.adversize;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by suryamurugan on 8/4/18.
 */

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("username", user.username);
        userLocalDatabaseEditor.putString("useremail", user.useremail);
        userLocalDatabaseEditor.putString("password", user.password);
        userLocalDatabaseEditor.putInt("vendorid", user.vedorid);
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String name = userLocalDatabase.getString("username", "");
        String username = userLocalDatabase.getString("useremail", "");
        String password = userLocalDatabase.getString("password", "");
        int vendorid = userLocalDatabase.getInt("vendorid", -1);

        User user = new User(name, vendorid, username, password);
        return user;
    }
}
