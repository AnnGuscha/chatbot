package by.gstu.bot.learning.domain;

import by.gstu.bot.learning.manager.Role;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="User")
public class User implements Serializable{
    private static final long serialVersionUID=0;

    @Id
    @Column(name="Id")
    @GeneratedValue
    private Integer id;

    @Column(name="Login")
    private String login;

    @Column(name="Password")
    private String password;

    @Column(name="Role")
    private Role role;

    public User() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
