package leo2o.forum.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import leo2o.forum.R;
import leo2o.forum.databinding.ActivityLoginBinding;
import leo2o.forum.dto.Response;
import leo2o.forum.dto.UserForm;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private final ForumService service = ServiceFactory.getService(ForumService.class);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new LoginListener());
    }

    class LoginListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d("login", "click");
            String username = binding.editUsername.getText().toString();
            String password = binding.editPassword.getText().toString();
            UserForm userForm = new UserForm(username, password);

            service.signin(userForm).enqueue(new Callback<Response<String>>() {
                @Override
                public void onResponse(Call<Response<String>> call, retrofit2.Response<Response<String>> response) {
                    if (response.isSuccessful()) {
                        Response<String> res = response.body();
                        if (res.getCode() == Response.Status.SUCCESS.value()) {
                            Toast.makeText(MyApplication.getContext(), res.getData(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MyApplication.getContext(), res.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        //404 500
                    }
                }

                @Override
                public void onFailure(Call<Response<String>> call, Throwable t) {
                    Toast.makeText(MyApplication.getContext(),  "网络异常！", Toast.LENGTH_LONG).show();
                    Log.d("login", t.toString());
                }
            });
        }
    }
}
