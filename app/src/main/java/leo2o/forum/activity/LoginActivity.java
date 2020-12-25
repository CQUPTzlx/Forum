package leo2o.forum.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import leo2o.forum.databinding.ActivityLoginBinding;
import leo2o.forum.dto.Response;
import leo2o.forum.dto.UserDto;
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

        binding.loginBack.setOnClickListener(v -> finish());

        binding.fastRegister.setClickable(true);
        binding.fastRegister.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(),RegisterActivity.class);
            startActivity(intent);
        });
    }

    class LoginListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d("login", "click");
            String username = binding.editUsername.getText().toString();
            String password = binding.editPassword.getText().toString();

            service.signin(username, password).enqueue(new Callback<Response<UserDto>>() {
                @Override
                public void onResponse(@NotNull Call<Response<UserDto>> call, @NotNull retrofit2.Response<Response<UserDto>> response) {
                    if (response.isSuccessful()) {
                        Response<UserDto> res = response.body();
                        if (res.getCode() == Response.Status.SUCCESS.value()) {
                            SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("user", 0).edit();
                            editor.putInt("uid", res.getData().getId());
                            editor.putString("username", res.getData().getUsername());
                            editor.apply();
                            finish();
                            Intent toMainPage = new Intent(v.getContext(), TopicListActivity.class);
                            toMainPage.putExtra("queryType", "all");
                            startActivity(toMainPage);
                        } else {
                            Toast.makeText(v.getContext(), res.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        //404 500
                    }
                }

                @Override
                public void onFailure(Call<Response<UserDto>> call, Throwable t) {
                    Toast.makeText(MyApplication.getContext(), "网络异常！", Toast.LENGTH_LONG).show();
                    Log.d("login", t.toString());
                }
            });
        }
    }
}
