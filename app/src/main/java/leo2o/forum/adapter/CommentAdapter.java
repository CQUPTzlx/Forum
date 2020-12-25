package leo2o.forum.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import leo2o.forum.data.Comment;
import leo2o.forum.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comment> commentList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView commentUsername;
        TextView commentContent;
        TextView commentDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentUsername = itemView.findViewById(R.id.comment_username);
            commentContent = itemView.findViewById(R.id.comment_content);
            commentDate = itemView.findViewById(R.id.comment_date);
        }
    }

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
//        if (holder.getAdapterPosition() == 0) {
//            holder.itemView.setBackground(null);
//        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.commentUsername.setText(comment.getUsername());
        holder.commentContent.setText(comment.getContent());
        holder.commentDate.setText(comment.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

}
