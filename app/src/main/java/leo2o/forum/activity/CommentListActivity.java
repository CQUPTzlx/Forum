package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import leo2o.forum.R;
import leo2o.forum.adapter.CommentAdapter;
import leo2o.forum.data.Comment;
import leo2o.forum.data.Topic;
import leo2o.forum.dto.Response;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class CommentListActivity extends AppCompatActivity {

    ForumService service = ServiceFactory.getService(ForumService.class);

    private final List<Comment> commentList = new ArrayList<>();

    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initComments();
        RecyclerView recyclerView = findViewById(R.id.comment_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CommentAdapter(commentList);
        recyclerView.setAdapter(adapter);
        loadComments();
    }

    private void loadComments() {
//        service.getTopicList().enqueue(new Callback<Response<>>)
    }

    private void initComments() {
        for (int i = 0; i < 3; i++) {
            Comment comment1 = new Comment("1", "111", new Date());
            commentList.add(comment1);
            Comment comment2 = new Comment("2", "222", new Date());
            commentList.add(comment2);
            Comment comment3 = new Comment("3", "333", new Date());
            commentList.add(comment3);
            Comment comment4 = new Comment("4", "444", new Date());
            commentList.add(comment4);
            Comment comment5 = new Comment("5", "555", new Date());
            commentList.add(comment5);
            Comment comment6 = new Comment("6", "666", new Date());
            commentList.add(comment6);
            Comment comment7 = new Comment("7", "777", new Date());
            commentList.add(comment7);
            Comment comment8 = new Comment("8", "888", new Date());
            commentList.add(comment8);
        }
    }
}