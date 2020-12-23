package leo2o.forum.request;

import leo2o.forum.dto.Response;
import leo2o.forum.dto.UserDto;
import leo2o.forum.dto.UserForm;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ForumService {

    @POST("signin")
    Call<Response<String>> signin(@Body UserForm userForm);

    @POST("signup")
    Call<Response<String>> signup(@Body UserForm userForm);

}
