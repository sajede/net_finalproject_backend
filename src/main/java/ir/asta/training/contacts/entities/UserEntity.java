package ir.asta.training.contacts.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER_TB")
public class UserEntity {


    Long id;
    String name;
    String family;
    String emailAddress;
    String password;
    String role;
    String sessionId;

    List<ReferrerEntity> referrerEntities = new ArrayList<>();


    public UserEntity( String name, String family, String emailAddress, String password, String role) {
        this.name = name;
        this.family = family;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }

    public UserEntity() {
    }


    @Basic
    @Column(name="SESSION_ID")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @OneToMany(
            mappedBy = "USER_TB",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<ReferrerEntity> getReferrerEntities() {
        return referrerEntities;
    }

    public void setReferrerEntities(List<ReferrerEntity> referrerEntities) {
        this.referrerEntities = referrerEntities;
    }

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name="NAME_")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name="FAMILY")
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    @Basic
    @Column(name="EMAIL_ADDRESS")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Basic
    @Column(name="PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name="ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", referrerEntities=" + referrerEntities +
                '}';
    }
}
