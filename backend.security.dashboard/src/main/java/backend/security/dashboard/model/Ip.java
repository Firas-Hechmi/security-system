package backend.security.dashboard.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Ip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address ;

    private LocalDateTime date;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Ip(){}
    public Ip(String address ,LocalDateTime date, User user){
        this.address = address;
        this.user = user;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
