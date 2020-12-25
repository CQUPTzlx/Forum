package leo2o.forum.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import leo2o.forum.R;
import leo2o.forum.adapter.TopicAdapter;
import leo2o.forum.data.Topic;
import leo2o.forum.dto.Response;
import leo2o.forum.utils.ActivityController;
import leo2o.forum.utils.MyApplication;
import leo2o.forum.utils.request.ForumService;
import leo2o.forum.utils.request.ServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;

public class TopicListActivity extends AppCompatActivity {

    ForumService service = ServiceFactory.getService(ForumService.class);

    private static final List<Topic> topicList = new ArrayList<>();

    private static TopicAdapter adapter;

    private SwipeRefreshLayout refreshLayout;

    private String queryType;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_user) {
            if ("collect".equals(queryType)) {
                finish();
            }
            //跳转到UserInfo
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);

        ActivityController.addActivity(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        initTopics();
        RecyclerView recyclerView = findViewById(R.id.topic_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TopicAdapter(topicList);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        queryType = intent.getStringExtra("queryType");

        loadTopics();

        FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PostAddActivity.class);
                startActivity(intent);
            }
        });

        refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTopics();
            }
        });
    }

    public static void insertTopic(Topic topic) {
        topicList.add(0, topic);
        adapter.notifyDataSetChanged();
    }

    public void loadTopics() {
        switch (queryType) {
            case "collect":
                service.getCollectTopics().enqueue(callback);
                break;
            case "all":
                service.getTopicList().enqueue(callback);
                break;
            default:
                break;
        }
    }

    Callback<Response<List<Topic>>> callback = new Callback<Response<List<Topic>>>() {
        @Override
        public void onResponse(Call<Response<List<Topic>>> call, retrofit2.Response<Response<List<Topic>>> response) {
            if (response.isSuccessful()) {
                Response<List<Topic>> res = response.body();
                if (res.isSuccess()) {
                    res.getData().sort((t1, t2) -> t2.updateDate.compareTo(t1.updateDate));
                    topicList.clear();
                    topicList.addAll(res.getData());
                    adapter.notifyDataSetChanged();
                    refreshLayout.setRefreshing(false);
                } else {
                    Toast.makeText(MyApplication.getContext(), res.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<List<Topic>>> call, Throwable t) {
            Toast.makeText(MyApplication.getContext(), "网络异常", Toast.LENGTH_LONG).show();
        }
    };

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