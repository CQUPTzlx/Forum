package leo2o.forum.utils.request;

import java.util.List;

import leo2o.forum.data.Comment;
import leo2o.forum.data.Topic;
import leo2o.forum.dto.Response;
import leo2o.forum.dto.UserDto;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ForumService {

    @FormUrlEncoded
    @POST("signin")
    Call<Response<String>> signin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("signup")
    Call<Response<String>> signup(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("post")
    Call<Response<Topic>> addPost(@Field("content") String content);

    @GET("post")
    Call<Response<List<Topic>>> getTopicList();

    @GET("post/{pid}")
    Call<Response<Topic>> getTopic(@Path("pid") int topicId);

    @GET("post/{pid}/comment")
    Call<Response<List<Comment>>> getCommentList(@Path("pid") int topicId);

    @FormUrlEncoded
    @POST("post/{pid}/comment")
    Call<Response<Comment>> comment(@Path("pid") int topicId, @Field("content") String content);

    @FormUrlEncoded
    @PUT("user/username")
    Call<Response<UserDto>> updateUsername(@Field("newUsername") String newUsername);

    @FormUrlEncoded
    @PUT("user/password")
    Call<Response<UserDto>> updatePassword(@Field("newPassword") String newPassword);

}
