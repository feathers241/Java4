package Entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Share")
public class Share {

    @Id
    @Column(length = 50)
    private String id;

    @ManyToOne
    @JoinColumn(name = "Userid")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "Videoid")
    private Video video;

    private String emails;

    @Temporal(TemporalType.DATE)
    private Date shareDate;

    public Share(String id, Users user, Video video, String emails, Date shareDate) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.emails = emails;
        this.shareDate = shareDate;
    }

    public Share() {}

    // ✅ Getter/Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }

    public String getEmails() { return emails; }
    public void setEmails(String emails) { this.emails = emails; }

    public Date getShareDate() { return shareDate; }
    public void setShareDate(Date shareDate) { this.shareDate = shareDate; }

    @Override
    public String toString() {
        return "Share{" +
                "id='" + id + '\'' +
                ", userId='" + user.getId() + '\'' +
                ", videoId='" + video.getId() + '\'' +
                ", emails='" + emails + '\'' +
                ", shareDate=" + shareDate +
                '}';
    }
}
