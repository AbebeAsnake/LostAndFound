package me.abebe.demo.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name ="last_name")
    private String lastName;
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;
    @NotNull
    @Column(name ="password")
    private String password;
    @NotNull
    @Column(name = "username")
    private String username;


    @CreationTimestamp
    @Column(name ="time_stamp")
    Timestamp createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns=@JoinColumn(name = "user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<AppRole> roles;

    @OneToMany(mappedBy = "users")
    private Set<LostItems> lostItems;

    public AppUser() {
        this.roles = new HashSet<>();
        this.lostItems = new HashSet<>();
    }
    public void addLostItems(LostItems lost){
        lostItems.add(lost);
    }

    public void addRole(AppRole role)
    {
        this.roles.add(role);
    }

    public AppUser(String firstName, String lastName, String email, String image, String password, String username, Timestamp createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
        this.password = password;
        this.username = username;
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                ", roles=" + roles +
                ", lostItems=" + lostItems +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public Set<LostItems> getLostItems() {
        return lostItems;
    }

    public void setLostItems(Set<LostItems> lostItems) {
        this.lostItems = lostItems;
    }
}
