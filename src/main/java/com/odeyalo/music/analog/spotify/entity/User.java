package com.odeyalo.music.analog.spotify.entity;

import com.odeyalo.music.analog.spotify.constants.ImageConstants;
import com.odeyalo.music.analog.spotify.entity.enums.Role;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;
    @Column(nullable = false)
    protected String name;
    @Column(unique = true, nullable = false)
    protected String email;
    @Column(length = 3000)
    protected String password;
    @Column(nullable = false, updatable = false)
    protected LocalDate accountCreatedTime;
    @Column(length = 1000)
    protected String image = ImageConstants.DEFAULT_USER_AVATAR_URL;
    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Role.class)
    protected Set<Role> roles;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_subscriptions")
    protected Set<Artist> subscriptions;
    public User() {
    }

    private User(String name, String email, String password, LocalDate accountCreatedTime, String image, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountCreatedTime = accountCreatedTime;
        this.image = image;
        this.roles = roles;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getAccountCreatedTime() {
        return accountCreatedTime;
    }

    public void setAccountCreatedTime(LocalDate accountCreatedTime) {
        this.accountCreatedTime = accountCreatedTime;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Artist> getSubscriptions() {
        return subscriptions;
    }

    public void addSubscription(Artist artist) {
        this.subscriptions.add(artist);
    }
    public void removeSubscription(Artist artist) {
        this.subscriptions.remove(artist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && name.equals(user.name) && email.equals(user.email) && password.equals(user.password) && accountCreatedTime.equals(user.accountCreatedTime) && image.equals(user.image) && roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, accountCreatedTime, image, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accountCreatedTime=" + accountCreatedTime +
                ", roles=" + roles +
                '}';
    }
    public static UserBuilder getUserBuilder() {

        return new UserBuilder();
    }
    public static class UserBuilder {
        private String email;
        private String name;
        private String password;
        private LocalDate accountCreatedTime;
        private String image;
        private Set<Role> roles;

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setAccountCreatedTime(LocalDate accountCreatedTime) {
            this.accountCreatedTime = accountCreatedTime;
            return this;
        }

        public UserBuilder setImage(String image) {
            this.image = image;
            return this;
        }

        public UserBuilder setRoles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }
        public User build() {
            return new User(this.name, this.email, this.password, this.accountCreatedTime, this.image, this.roles);
        }
    }
}
