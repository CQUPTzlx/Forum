package leo2o.forum.data;

import java.util.Date;

public class Topic {

    private String username;
    private String content;
    private int cnt;
    private Date date;

    public Topic(String username, String content, int cnt, Date date) {
        this.username = username;
        this.content = content;
        this.cnt = cnt;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public int getCnt() {
        return cnt;
    }

    public Date getDate() {
        return date;
    }
}
