package leo2o.forum.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import leo2o.forum.data.Topic;
import leo2o.forum.databinding.ActivityAddPostBinding;
import leo2o.forum.dto.Response;
import leo2o.forum.utils.ActivityController;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class PostAddActivity extends AppCompatActivity {

    private ActivityAddPostBinding binding;

    private final ForumService service = ServiceFactory.getService(ForumService.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityController.addActivity(this);

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = binding.editPost.getText().toString();
                service.addPost(content).enqueue(new Callback<Response<Topic>>() {
                    @Override
                    public void onResponse(Call<Response<Topic>> call, retrofit2.Response<Response<Topic>> response) {
                        if (response.isSuccessful()) {
                            Response<Topic> res = response.body();
                            if (res.isSuccess()) {
                                Toast.makeText(v.getContext(), "发表成功", Toast.LENGTH_SHORT).show();
                                TopicListActivity.insertTopic(res.getData());
                                finish();
                            } else {
                                Toast.makeText(v.getContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Topic>> call, Throwable t) {
                        Log.d("addpost", t.toString());
                        Toast.makeText(MyApplication.getContext(), "网络异常！", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
