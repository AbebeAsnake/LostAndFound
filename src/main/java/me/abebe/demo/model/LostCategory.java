package me.abebe.demo.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lost_category")
public class LostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "category_name")
    private String categoryName;

    @CreationTimestamp
    @Column(name = "Time_stamp")
    Timestamp createdAt;

    @OneToMany(mappedBy = "lostCategories")
    private Set<LostItems> lostItems;

    public LostCategory(String categoryName, Timestamp createdAt, Set<LostItems> lostItems) {
        this.categoryName = categoryName;
        this.createdAt = createdAt;
        this.lostItems = lostItems;
    }

    public LostCategory() {
        this.lostItems = new HashSet<>();
    }

    public LostCategory(String categoryName) {
        this.categoryName = categoryName;
        this.createdAt = createdAt;
        this.lostItems = lostItems;
    }

    public void addLostItems(LostItems lost){
        lostItems.add(lost);
    }
    @Override
    public String toString() {
        return "LostCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", createdAt=" + createdAt +
                ", lostItems=" + lostItems +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Set<LostItems> getLostItems() {
        return lostItems;
    }

    public void setLostItems(Set<LostItems> lostItems) {
        this.lostItems = lostItems;
    }


}
