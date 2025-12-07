package Entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "History")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // IDENTITY(1,1) SQL Server
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "VideoId")
    private Video video;

    @Temporal(TemporalType.TIMESTAMP)
    private Date viewDate;

    public String toString() {
    	return id + " ,"+user.getId() + " ,"+video.getId() + " ,"+viewDate;
    }
    
    public History() {
        this.viewDate = new Date();  // tự tạo thời gian xem khi persist
    }

    public History(Users user, Video video) {
        this.user = user;
        this.video = video;
        this.viewDate = new Date();
    }

    // Getter - Setter
    public Integer getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }
}
