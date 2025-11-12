package Entity;

import java.util.Date;
import Entity.Video;

import javax.persistence.*;

@Entity
@Table(name = "Favorite")
public class Favorite {
	
		@Id
		@Column(length = 50)
	    private String id;

	    @ManyToOne
	    @JoinColumn(name = "Userid")
	    private Users user;

	    @ManyToOne
	    @JoinColumn(name = "Videoid")
	    private Video video;

	    @Temporal(TemporalType.DATE)
	    private Date likeDate;
	
	public Favorite(String a ,Users b, Video c, Date d) {
		this.id = a;
		this.user = b;
		this.video = c;
		this.likeDate = d;
	}
	
	public Favorite() {
		
	}
		
	@Override
	public String toString() {
	    return "Favorite{" +
	            "id=" + id +
	            ", userId=" + (user != null ? user.getId() : null) +
	            ", videoId=" + (video != null ? video.getId() : null) +
	            ", likeDate=" + likeDate +
	            '}';
	}


    // ===== Getter & Setter =====

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }
}

