package leo2o.forum.utils.request;

import leo2o.forum.dto.Response;
import leo2o.forum.dto.UserForm;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ForumService {

    @POST("signin")
    Call<Response<String>> signin(@Body UserForm userForm);

    @POST("signup")
    Call<Response<String>> signup(@Body UserForm userForm);

}
