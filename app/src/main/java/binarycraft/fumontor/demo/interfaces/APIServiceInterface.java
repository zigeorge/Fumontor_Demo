package binarycraft.fumontor.demo.interfaces;

import binarycraft.fumontor.demo.responses.SignUpResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIServiceInterface {
    @FormUrlEncoded
    @POST("signup.php")
    Call<SignUpResponse> signUpUser(@Query("JSONParam") String jSonParam, @Field("image_url") String image_url, @Field("accesstoken") String link);
}
