package me.abebe.demo.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lost_items")
public class LostItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String desc;

    @Column(name = "image")
    private String image;

    @Column( name ="item_status")
    private String itemStatus;

    @CreationTimestamp
    @Column(name = "time_stamp")
    Timestamp createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppUser> users;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<LostCategory> lostCategories;

    public LostItems() {
        this.users = new HashSet<>();
        this.lostCategories = new HashSet<>();

    }

    public void addCategory(LostCategory category)
    {
        this.lostCategories.add(category);
    }
    public void addUser(AppUser user)
    {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "LostItems{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
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
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<LostCategory> getLostCategories() {
        return lostCategories;
    }

    public void setLostCategories(Set<LostCategory> lostCategories) {
        this.lostCategories = lostCategories;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
}
