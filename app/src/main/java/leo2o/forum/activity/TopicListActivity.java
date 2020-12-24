package leo2o.forum.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import leo2o.forum.R;
import leo2o.forum.adapter.TopicAdapter;
import leo2o.forum.data.Topic;
import leo2o.forum.dto.Response;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class TopicListActivity extends AppCompatActivity {

    ForumService service = ServiceFactory.getService(ForumService.class);

    private final List<Topic> topicList = new ArrayList<>();

    private TopicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        initTopics();
        RecyclerView recyclerView = findViewById(R.id.topic_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TopicAdapter(topicList);
        recyclerView.setAdapter(adapter);
        loadTopics();
    }

    private void loadTopics() {
        service.getTopicList().enqueue(new Callback<Response<List<Topic>>>() {
            @Override
            public void onResponse(Call<Response<List<Topic>>> call, retrofit2.Response<Response<List<Topic>>> response) {
                if (response.isSuccessful()) {
                    Response<List<Topic>> res = response.body();
                    if (res.isSuccess()) {
                        topicList.addAll(res.getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MyApplication.getContext(), res.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<List<Topic>>> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(), "网络异常", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initTopics() {
//        for (int i = 0; i < 2; i++) {
//            Topic topic1 = new Topic("1", "111", 1, new Date(), id, uid, createDate, updateDate, commentCount);
//            topicList.add(topic1);
//            Topic topic2 = new Topic("2", "222", 2, new Date(), id, uid, createDate, updateDate, commentCount);
//            topicList.add(topic2);
//            Topic topic3 = new Topic("3", "333", 3, new Date(), id, uid, createDate, updateDate, commentCount);
//            topicList.add(topic3);
//            Topic topic4 = new Topic("4", "444", 4, new Date(), id, uid, createDate, updateDate, commentCount);
//            topicList.add(topic4);
//            Topic topic5 = new Topic("5", "555", 5, new Date(), id, uid, createDate, updateDate, commentCount);
//            topicList.add(topic5);
//            Topic topic6 = new Topic("6", "666", 6, new Date(), id, uid, createDate, updateDate, commentCount);
//            topicList.add(topic6);
//        }
    }
}