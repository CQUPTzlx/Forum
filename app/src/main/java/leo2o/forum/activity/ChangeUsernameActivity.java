package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import leo2o.forum.databinding.ActivityChangeUsernameBinding;
import leo2o.forum.dto.Response;
import leo2o.forum.dto.UserDto;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class ChangeUsernameActivity extends AppCompatActivity {

    private ActivityChangeUsernameBinding binding;

    private final ForumService service = ServiceFactory.getService(ForumService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeUsernameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitChangeUsernameBtn.setOnClickListener(new ChangeUsernameListener());

    }

    private class ChangeUsernameListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String username = binding.newUsername.getText().toString();
            if (username.isEmpty()){
                Toast.makeText(MyApplication.getContext(), "用户名不能为空！", Toast.LENGTH_LONG).show();
                return;
            }

            service.updateUsername(username).enqueue(new Callback<Response<UserDto>>() {
                @Override
                public void onResponse(Call<Response<UserDto>> call, retrofit2.Response<Response<UserDto>> response) {
                    if (response.isSuccessful()) {
                        Response<UserDto> res = response.body();
                        if (res.isSuccess()) {
                            SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("user", 0).edit();
                            editor.putString("username", res.getData().getUsername());
                            editor.apply();
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