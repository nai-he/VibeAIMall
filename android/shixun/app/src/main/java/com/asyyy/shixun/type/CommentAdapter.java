package com.asyyy.shixun.type;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.asyyy.shixun.R;
import com.asyyy.shixun.home.Comment;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private static final String TAG = "CommentAdapter";
    private List<Comment> commentList;
    private OnCommentClickListener onCommentClickListener;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
        Log.d(TAG, "CommentAdapter 创建，评论数量: " + (commentList != null ? commentList.size() : 0));
    }

    // 添加点击监听器接口
    public interface OnCommentClickListener {
        void onCommentClick(Comment comment);
    }

    public void setOnCommentClickListener(OnCommentClickListener listener) {
        this.onCommentClickListener = listener;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "创建 CommentViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        if (commentList == null || commentList.isEmpty()) {
            Log.w(TAG, "评论列表为空");
            return;
        }

        Comment comment = commentList.get(position);
        Log.d(TAG, "绑定评论到位置 " + position + ": " + comment.getUserName());

        // 确保这些视图不为null
        if (holder.userName != null) {
            holder.userName.setText(comment.getUserName());
        }
        if (holder.content != null) {
            holder.content.setText(comment.getContent());
        }
        if (holder.ratingBar != null) {
            holder.ratingBar.setRating(comment.getRating());
        }
        if (holder.createTime != null) {
            holder.createTime.setText(comment.getCreateTime());
        }

        // 添加点击事件
        holder.itemView.setOnClickListener(v -> {
            if (onCommentClickListener != null) {
                onCommentClickListener.onCommentClick(comment);
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = commentList == null ? 0 : commentList.size();
        Log.d(TAG, "getItemCount: " + count);
        return count;
    }

    public void updateData(List<Comment> newCommentList) {
        this.commentList = newCommentList;
        Log.d(TAG, "更新数据，新评论数量: " + (newCommentList != null ? newCommentList.size() : 0));
        notifyDataSetChanged();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView content;
        RatingBar ratingBar;
        TextView createTime;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.comment_user_name);
            content = itemView.findViewById(R.id.comment_content);
            ratingBar = itemView.findViewById(R.id.comment_rating);
            createTime = itemView.findViewById(R.id.comment_time);

            // 检查视图是否成功找到
            if (userName == null) Log.e("CommentViewHolder", "userName 为 null");
            if (content == null) Log.e("CommentViewHolder", "content 为 null");
            if (ratingBar == null) Log.e("CommentViewHolder", "ratingBar 为 null");
            if (createTime == null) Log.e("CommentViewHolder", "createTime 为 null");
        }
    }
}