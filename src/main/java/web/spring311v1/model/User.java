package web.spring311v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users", schema = "spring_security")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private int age;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "password_real")
    private String passwordReal;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    public User(String name, String lastname, int age, String login, String password) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public User(String name, String lastname, int age, String login, String password, Set<Role> roles) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User: id = " + id + ", name = " + name + " " + lastname + " " + age
                + ", login = " + login + ", password = " + passwordReal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
