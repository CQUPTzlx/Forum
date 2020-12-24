package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import leo2o.forum.databinding.ActivityRegisterBinding;
import leo2o.forum.dto.Response;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    private final ForumService service = ServiceFactory.getService(ForumService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRegister.setOnClickListener(new RegisterListener());

        binding.registerBack.setOnClickListener((v) -> {
            finish();
        });

    }

    private class RegisterListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d("register", "click");
            String username = binding.registerUsername.getText().toString();
            String password = binding.registerPassword.getText().toString();
            String passwordConfirm = binding.registerPasswordConfirm.getText().toString();

            if (username.isEmpty()) {
                Toast.makeText(MyApplication.getContext(), "用户名不能为空！", Toast.LENGTH_LONG).show();
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(MyApplication.getContext(), "密码不能为空！", Toast.LENGTH_LONG).show();
                return;
            }

            if (password.equals(passwordConfirm)) {
                service.signup(username, password).enqueue(new Callback<Response<String>>() {
                    @Override
                    public void onResponse(Call<Response<String>> call, retrofit2.Response<Response<String>> response) {
                        if (response.isSuccessful()) {
                            Response<String> res = response.body();
                            if (res.getCode() == Response.Status.SUCCESS.value()) {
//                                Toast.makeText(MyApplication.getContext(), res.getData(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(v.getContext(),LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MyApplication.getContext(), res.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            //404 500
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<String>> call, Throwable t) {
                        Toast.makeText(MyApplication.getContext(), "网络异常！", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(MyApplication.getContext(), "两次密码不一致！", Toast.LENGTH_LONG).show();
            }
        }
    }
}