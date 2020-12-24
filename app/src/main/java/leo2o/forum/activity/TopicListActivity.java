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
import leo2o.forum.adapter.TopicAdapter;
import leo2o.forum.data.Topic;

public class TopicListActivity extends AppCompatActivity {

    private List<Topic> topicList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        initTopics();
        RecyclerView recyclerView = findViewById(R.id.topic_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        TopicAdapter adapter = new TopicAdapter(topicList);
        recyclerView.setAdapter(adapter);
    }

    private void initTopics() {
        for (int i = 0; i < 2; i++) {
            Topic topic1 = new Topic("1", "111", 1, new Date());
            topicList.add(topic1);
            Topic topic2 = new Topic("2", "222", 2, new Date());
            topicList.add(topic2);
            Topic topic3 = new Topic("3", "333", 3, new Date());
            topicList.add(topic3);
            Topic topic4 = new Topic("4", "444", 4, new Date());
            topicList.add(topic4);
            Topic topic5 = new Topic("5", "555", 5, new Date());
            topicList.add(topic5);
            Topic topic6 = new Topic("6", "666", 6, new Date());
            topicList.add(topic6);
        }
    }
}