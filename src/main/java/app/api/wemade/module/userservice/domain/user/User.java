package app.api.wemade.module.userservice.domain.user;


import app.api.wemade.domain.DomainValidationException;
import app.api.wemade.module.userservice.domain.UserDomainValidationMessage;
import app.api.wemade.module.userservice.domain.role.Role;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "`user`")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    private boolean activated;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<>();

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    private LocalDateTime lastLoggedIn;

    public void addRole(Role role) {
        if (this.roles.stream().anyMatch(r -> r.getId() == role.getId())) {
            throw new DomainValidationException(UserDomainValidationMessage.ROLE_ALREADY_EXIST);
        }

        this.roles.add(role);
    }

    public void login() {
        this.setLastLoggedIn(LocalDateTime.now());
    }

    public void deleteUser() {
        this.setUserStatus(UserStatus.DELETED);
    }


    private User(String username, String password, String nickname) {

        this.setUsername(username);
        this.setPassword(password);
        this.setNickname(nickname);
        this.setActivated(true);
        this.setUserStatus(UserStatus.JOINED);
    }

    public static User create(String username, String password, String nickname){
        return new User(username, password, nickname);
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setUsername(String userId) {
        this.username = userId;
    }

    private void setNickname(String name) {
        this.nickname = name;
    }

    private void setCreated(LocalDateTime created) {
        this.created = created;
    }

    private void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    private void setLastLoggedIn(LocalDateTime lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    private void setActivated(boolean activated) {
        this.activated = activated;
    }

    private void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

}
