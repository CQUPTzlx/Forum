package leo2o.forum.utils.request;

import leo2o.forum.dto.Response;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ForumService {

    @FormUrlEncoded
    @POST("signin")
    Call<Response<String>> signin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("signup")
    Call<Response<String>> signup(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("post")
    Call<Response<String>> addPost(@Field("content") String content);
}
