package hu.gerviba.webschop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @Column(unique = true)
    private String uid;
    
    @Column
    private String name;
    
    @Column
    private String email;
    
    @Column
    private String room;
    
    public UserEntity() {}

    public UserEntity(String uid, String name, String email) {
        super();
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRoom() {
        return room;
    }
    
    private static final long serialVersionUID = 796312955720547481L;
    
}
