package ads.in.adversize.adversize.remote;

import retrofit2.Retrofit;

/**
 * Created by suryamurugan on 28/3/18.
 */

public class ApiUtils {

    //public static final String BASE_URL= "https://adversize.in/";
    public static final String BASE_URL= "http://suryamurugan.co.nf/";
    public static UserService getUserService(){

        return RetrofitClient.getClient(BASE_URL).create(UserService.class);

    }
}
