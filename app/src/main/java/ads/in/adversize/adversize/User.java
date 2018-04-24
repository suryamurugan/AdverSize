package ads.in.adversize.adversize;

/**
 * Created by suryamurugan on 8/4/18.
 */

public class User {





    String username, useremail, password;

    int vedorid;

    public User(String username, int vedorid, String useremail, String password) {
        this.username = username;
        this.vedorid = vedorid;
        this.useremail = useremail;
        this.password = password;


    }

    public User(String useremail, String password) {
        this("", -1, useremail, password);
    }



}
