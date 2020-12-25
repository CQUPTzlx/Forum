package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import leo2o.forum.databinding.ActivityChangePasswordBinding;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;

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



        }
    }
}