package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import leo2o.forum.adapter.TopicAdapter;
import leo2o.forum.data.Topic;
import leo2o.forum.databinding.ActivityInfoBinding;
import leo2o.forum.dto.Response;
import leo2o.forum.utils.ActivityController;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class InfoActivity extends AppCompatActivity {

    private ActivityInfoBinding binding;

    private final ForumService service = ServiceFactory.getService(ForumService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityController.addActivity(this);

        loadUserInfo();

        binding.changeUsername.setOnClickListener(new ChangeUsername());
        binding.changePassword.setOnClickListener(new ChangePassword());
        binding.logoutBtn.setOnClickListener(new LogoutListener());

        binding.queryCollect.setOnClickListener(new QueryCollectListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserInfo();
    }

    private class QueryCollectListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), TopicListActivity.class);
            intent.putExtra("queryType", "collect");
            startActivity(intent);
        }
    }

    private void loadUserInfo() {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("user", 0);
        binding.infoUsername.setText(sp.getString("username", "请先登录"));
    }

    private class ChangeUsername implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ChangeUsernameActivity.class);
            startActivity(intent);
        }
    }

    private class ChangePassword implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ChangePasswordActivity.class);
            startActivity(intent);
        }
    }

    private class LogoutListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            service.logout().enqueue(new Callback<Response<String>>() {
                @Override
                public void onResponse(Call<Response<String>> call, retrofit2.Response<Response<String>> response) {
                    SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("user", 0).edit();
                    editor.remove("uid");
                    editor.remove("username");
                    editor.apply();
                    ActivityController.finishAll();
                    Toast.makeText(v.getContext(), "注销成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Response<String>> call, Throwable t) {
                    Toast.makeText(v.getContext(), "网络异常", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}