package leo2o.forum.data;

import java.util.Date;

public class Topic {

    public final int id;
    public final int uid;
    public final String username;
    public final Date createDate;
    public final Date updateDate;
    public final String content;
    public final int commentCount;

    public Topic(String username, String content, int cnt, Date date, int id, int uid, Date createDate, Date updateDate, int commentCount) {
        this.username = username;
        this.content = content;
        this.id = id;
        this.uid = uid;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.commentCount = commentCount;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
