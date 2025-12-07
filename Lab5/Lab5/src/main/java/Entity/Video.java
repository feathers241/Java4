package Entity;

import javax.persistence.*;

@Entity
@Table(name = "Video")
public class Video {

    @Id
    @Column(length = 50)
    private String id;

    private String title;
    private String poster;
    private int views;
    private String description;
    private boolean active;

    public Video(String id, String title, String poster, int views, String description, boolean active) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.views = views;
        this.description = description;
        this.active = active;
    }

    public Video() {}

    // ✅ Getter/Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", views=" + views +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
