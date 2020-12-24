package leo2o.forum.activity;

import android.content.Intent;
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

        binding.loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.fastRegister.setClickable(true);
        binding.fastRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext())
            }
        });
    }

    class LoginListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.d("login", "click");
            String username = binding.editUsername.getText().toString();
            String password = binding.editPassword.getText().toString();

            service.signin(username, password).enqueue(new Callback<Response<String>>() {
                @Override
                public void onResponse(@NotNull Call<Response<String>> call, @NotNull retrofit2.Response<Response<String>> response) {
                    if (response.isSuccessful()) {
                        Response<String> res = response.body();
                        if (res.getCode() == Response.Status.SUCCESS.value()) {
                            Intent toMainPage = new Intent(v.getContext(), TopicListActivity.class);
                            startActivity(toMainPage);
                        } else {
                            Toast.makeText(v.getContext(), res.getMessage(), Toast.LENGTH_LONG).show();
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
