package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import leo2o.forum.R;
import leo2o.forum.adapter.CommentAdapter;
import leo2o.forum.data.Comment;
import leo2o.forum.data.Topic;
import leo2o.forum.databinding.ActivityPostBinding;
import leo2o.forum.dto.Response;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class CommentListActivity extends AppCompatActivity {

    ForumService service = ServiceFactory.getService(ForumService.class);

    private final List<Comment> commentList = new ArrayList<>();

    private int topicId;

    private CommentAdapter adapter;

    private ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        topicId = getIntent().getIntExtra("topicId", 0);
//        initComments();
        RecyclerView recyclerView = findViewById(R.id.comment_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CommentAdapter(commentList);
        recyclerView.setAdapter(adapter);
        loadTopic();
        loadComments();

        binding.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = binding.commentText.getText().toString();
                service.comment(topicId, content).enqueue(new Callback<Response<Comment>>() {
                    @Override
                    public void onResponse(Call<Response<Comment>> call, retrofit2.Response<Response<Comment>> response) {
                        if (response.isSuccessful()) {
                            Response<Comment> res = response.body();
                            if (res.isSuccess()) {
                                Toast.makeText(v.getContext(), "发表成功", Toast.LENGTH_SHORT).show();
                                binding.commentText.setText("");
                                commentList.add(res.getData());
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(v.getContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Comment>> call, Throwable t) {
                        Toast.makeText(v.getContext(), "网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loadTopic() {
        service.getTopic(topicId).enqueue(new Callback<Response<Topic>>() {
            @Override
            public void onResponse(Call<Response<Topic>> call, retrofit2.Response<Response<Topic>> response) {
                if (response.isSuccessful()) {
                    Response<Topic> res = response.body();
                    if(res.isSuccess()) {
                        Topic topic = res.getData();
                            binding.postUsername.setText(topic.getUsername());
                            binding.postContent.setText(topic.getContent());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<Topic>> call, Throwable t) {

            }
        });
    }

    private void loadComments() {
//        service.getTopicList().enqueue(new Callback<Response<>>)
        service.getCommentList(topicId).enqueue(new Callback<Response<List<Comment>>>() {
            @Override
            public void onResponse(Call<Response<List<Comment>>> call, retrofit2.Response<Response<List<Comment>>> response) {
                if (response.isSuccessful()) {
                    Response<List<Comment>> res = response.body();
                    if (res.isSuccess()) {
                        List<Comment> comments = res.getData();
                        commentList.addAll(comments);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<Comment>>> call, Throwable t) {

            }
        });
    }

//    private void initComments() {
//        for (int i = 0; i < 3; i++) {
//            Comment comment1 = new Comment("1", "111", new Date());
//            commentList.add(comment1);
//            Comment comment2 = new Comment("2", "222", new Date());
//            commentList.add(comment2);
//            Comment comment3 = new Comment("3", "333", new Date());
//            commentList.add(comment3);
//            Comment comment4 = new Comment("4", "444", new Date());
//            commentList.add(comment4);
//            Comment comment5 = new Comment("5", "555", new Date());
//            commentList.add(comment5);
//            Comment comment6 = new Comment("6", "666", new Date());
//            commentList.add(comment6);
//            Comment comment7 = new Comment("7", "777", new Date());
//            commentList.add(comment7);
//            Comment comment8 = new Comment("8", "888", new Date());
//            commentList.add(comment8);
//        }
//    }
}