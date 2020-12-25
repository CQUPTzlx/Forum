package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import leo2o.forum.databinding.ActivityChangePasswordBinding;
import leo2o.forum.dto.Response;
import leo2o.forum.dto.UserDto;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class ChangePasswordActivity extends AppCompatActivity {

    private ActivityChangePasswordBinding binding;

    private final ForumService service = ServiceFactory.getService(ForumService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitChangePasswordBtn.setOnClickListener(new ChangePasswordListener());
    }

    private class ChangePasswordListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String password = binding.newPassword.getText().toString();
            if (password.isEmpty()){
                Toast.makeText(MyApplication.getContext(), "密码不能为空！", Toast.LENGTH_LONG).show();
                return;
            }

            service.updatePassword(password).enqueue(new Callback<Response<UserDto>>() {
                @Override
                public void onResponse(Call<Response<UserDto>> call, retrofit2.Response<Response<UserDto>> response) {
                    if (response.isSuccessful()) {
                        Response<UserDto> res = response.body();
                        if (res.isSuccess()) {
                            Toast.makeText(v.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        Toast.makeText(v.getContext(), "修改失败", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Response<UserDto>> call, Throwable t) {
                    Toast.makeText(v.getContext(), "网络异常", Toast.LENGTH_LONG).show();
                }
            });


        }
    }
}