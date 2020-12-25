package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import leo2o.forum.databinding.ActivityInfoBinding;

public class InfoActivity extends AppCompatActivity {

    private ActivityInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.changeUsername.setOnClickListener(new ChangeUsername());
        binding.changePassword.setOnClickListener(new ChangePassword());
    }

    private class ChangeUsername implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(v.getContext(),);
//            startActivity(intent);
        }
    }

    private class ChangePassword implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(v.getContext(),);
//            startActivity(intent);
        }
    }
}