package leo2o.forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import leo2o.forum.adapter.CommentAdapter;
import leo2o.forum.data.Comment;

public class CommentList extends AppCompatActivity {

    private List<Comment> commentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        initComments();
        RecyclerView recyclerView = findViewById(R.id.comment_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentAdapter adapter = new CommentAdapter(commentList);
        recyclerView.setAdapter(adapter);
    }

    private void initComments() {
        for (int i =0;i<3;i++){
            Comment comment1 = new Comment("1","111",new Date());
            commentList.add(comment1);
            Comment comment2 = new Comment("2","222",new Date());
            commentList.add(comment2);
            Comment comment3 = new Comment("3","333",new Date());
            commentList.add(comment3);
            Comment comment4 = new Comment("4","444",new Date());
            commentList.add(comment4);
            Comment comment5 = new Comment("5","555",new Date());
            commentList.add(comment5);
            Comment comment6 = new Comment("6","666",new Date());
            commentList.add(comment6);
            Comment comment7 = new Comment("7","777",new Date());
            commentList.add(comment7);
            Comment comment8 = new Comment("8","888",new Date());
            commentList.add(comment8);
        }
    }
}