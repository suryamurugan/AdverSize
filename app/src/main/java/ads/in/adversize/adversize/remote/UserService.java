package ads.in.adversize.adversize.remote;

import org.json.JSONArray;

import java.util.List;

import ads.in.adversize.adversize.Media;
import ads.in.adversize.adversize.model.MediaObject;
import ads.in.adversize.adversize.model.ResObj;
import ads.in.adversize.adversize.model.resp;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by suryamurugan on 28/3/18.
 */

public interface UserService {
   /* @GET("finalid?id={username}&&p={password}")
    Call<ResObj> login(@Path("username")String username, @Path("password")String password);
*/
@FormUrlEncoded
    @POST("mVendorLogin.php")
    Call<ResObj> login(@Field("id") String username,
                        @Field("p") String password);
/*
    @Multipart
    @POST("upload")
    Call<String> uploadImage(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("desc") RequestBody desc);
*/
    @FormUrlEncoded
    @POST("o.php")
    Call<resp> uploadImage(@Field("image") String image, @Field("name")String name);


   /*
    @GET("finalid.php")

    Call<ResObj> login(
            @Query("id") String username,
            @Query("p") String password);
*/
    @GET("vendorm.php")
    Call<List<MediaObject>> vendorm(
            @Query("id") String id);

    @GET("final.php")
    Call<List<Media>> singlemedia(
      @Query("id") String id
    );

    // API TO ADD DATA INTO DATABASE  ( media Upload .php)

    @FormUrlEncoded
    @POST("mediaupload.php")
    Call<resp> mediaUpload(@Field("vendorID")String vendorID,
                           @Field("mediaType")String mediaType,
                           @Field("landmark")String landmark,
                           @Field("facing")String facing,
                           @Field("width")String width,
                           @Field("height")String height,
                           @Field("street")String street,
                           @Field("city")String city,
                           @Field("state")String state,
                           @Field("available") String available,
                           @Field("price")String price,
                           @Field("price6")String price6,
                           @Field("price12")String price12,
                           @Field("imagedata")String imagedata);
/*
    $vendorID = $_POST['vendorID'];
    // $mediaType = $_POST['mediaType'];

//    $productQuery = mysqli_query($conn,"SELECT * FROM products WHERE productName='$mediaType'") or die(mysqli_error($conn));
    //  $productRow = mysqli_fetch_array($productQuery);
//

    $landmark = $_POST['landmark']; // MEDIA NAME

    $facing = $_POST['facing']; //MEDIA FACAING

    $width = $_POST['width']; //MEDIA WIDTH

    $height = $_POST['height']; // MEDIA hEIGHT

    $street = $_POST['street'];	// MEDIA STREET

    $city = $_POST['city']; // CITY

    $state = $_POST['state']; //STATE

    $available = $_POST['available']; //AVAILA

    $price = $_POST['price']; //PRICE

    $price6 = $_POST['price6'];

    $price12 = $_POST['price12'];

    $imagedata = $_POST['imagedata'];
    */
}
