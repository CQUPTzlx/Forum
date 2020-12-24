package leo2o.forum.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import leo2o.forum.R;
import leo2o.forum.data.Topic;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private List<Topic> topicList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView topicUsername;
        TextView topicContent;
        TextView topicCntComment;
        TextView topicDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topicUsername = itemView.findViewById(R.id.topic_username);
            topicContent = itemView.findViewById(R.id.topic_content);
            topicCntComment = itemView.findViewById(R.id.topic_count_comment);
            topicDate = itemView.findViewById(R.id.topic_date);
        }
    }

    public TopicAdapter(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_topic, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic topic = topicList.get(position);
        holder.topicUsername.setText(topic.getUsername());
        holder.topicContent.setText(topic.getContent());
        holder.topicCntComment.setText(String.valueOf(topic.getCommentCount()));
        holder.topicDate.setText(topic.getUpdateDate().toString());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }
}
