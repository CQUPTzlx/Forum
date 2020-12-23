package leo2o.forum;

import java.util.Date;

public class Comment {

    private String username;
    private String content;
    private Date date;

    public Comment(String username, String content, Date date) {
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
