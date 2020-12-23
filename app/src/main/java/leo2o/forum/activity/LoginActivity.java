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
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        binding.btnLogin.setOnClickListener(new LoginListener());
    }

    class LoginListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String username = binding.editUsername.getText().toString();
            String password = binding.editPassword.getText().toString();
            UserForm userForm = new UserForm(username, password);

            service.signin(userForm).enqueue(new Callback<Response<String>>() {
                @Override
                public void onResponse(Call<Response<String>> call, retrofit2.Response<Response<String>> response) {

                }

                @Override
                public void onFailure(Call<Response<String>> call, Throwable t) {
                    Toast.makeText(MyApplication.getContext(),  "登录错误！", Toast.LENGTH_LONG).show();
                    Log.d("login", t.toString());
                }
            });
        }
    }
}
