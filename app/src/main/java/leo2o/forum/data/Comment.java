package leo2o.forum.data;

import java.util.Date;

import leo2o.forum.dto.UserDto;

public class Comment {

    public final int id;
    public final int pid;
    public final UserDto user;
    public final String content;
    public final Date date;

    public Comment(int id, int pid, UserDto user, String content, Date date) {

        this.id = id;
        this.pid = pid;
        this.user = user;
        this.content = content;
        this.date = date;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
