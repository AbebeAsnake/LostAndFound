package me.abebe.demo.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "lost_items")
public class LostItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;
    @NotNull
   @Column(name = "description")
    private String descr;

    private String itemStatus;

    @CreationTimestamp
    @Column(name = "time_stamp")
    Timestamp createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private AppUser users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = true)
    private LostCategory lostCategories;

    //@Column(name = "image")
    private String image;

    public LostItems(String title, String desc, String image, String itemStatus, Timestamp createdAt) {
        this.title = title;
        this.descr = desc;
        this.image = image;
        this.itemStatus = itemStatus;
        this.createdAt = createdAt;
        this.users = users;
        this.lostCategories = lostCategories;
    }

    public LostItems(String title, String desc, String image, String itemStatus, Timestamp createdAt, AppUser users, LostCategory lostCategories) {
        this.title = title;
        this.descr = desc;
        this.image = image;
        this.itemStatus = itemStatus;
        this.createdAt = createdAt;
        this.users = users;
        this.lostCategories = lostCategories;
    }

    // @Column( name ="item_status")


    public LostItems() {
    }

    @Override
    public String toString() {
        return "LostItems{" +
                ", title='" + title + '\'' +
                ", desc='" + descr + '\'' +
                ", createdAt=" + createdAt +
                ", users=" + users +
                '}';
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return descr;
    }

    public void setDesc(String desc) {
        this.descr = desc;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public AppUser getUsers() {
        return users;
    }

    public void setUsers(AppUser users) {
        this.users = users;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LostCategory getLostCategories() {
        return lostCategories;
    }

    public void setLostCategories(LostCategory lostCategories) {
        this.lostCategories = lostCategories;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
}
