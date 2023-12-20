package yull.todoblog.common.thread;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import yull.todoblog.comment.domain.Comment;
import yull.todoblog.channel.domain.Channel;
import java.util.Set;

@Entity
public class Thread {

    private String message;

    @ManyToOne
    private Channel channel;

    @OneToMany(mappedBy = "thread")
    private Set<Comment> comments;
    @Id
    private Long id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
