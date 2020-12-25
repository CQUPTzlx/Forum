package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import leo2o.forum.databinding.ActivityChangeUsernameBinding;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;

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


        }
    }
}