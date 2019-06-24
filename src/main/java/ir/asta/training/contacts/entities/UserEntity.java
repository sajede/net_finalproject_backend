package ir.asta.training.contacts.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER_TB")
@NamedQuery(query = "Select e from UserEntity e where e.emailAddress = :emailAddress", name = "find user by email")
@NamedQuery(query = "Select e from UserEntity e where e.sessionId = :sessionId", name = "find user by sessionid")
public class UserEntity {


    Long id;
    String name;
    String family;
    String emailAddress;
    String password;
    String role;
    String sessionId;
    boolean approved;

    List<ReferrerEntity> toReferrerEntities = new ArrayList<>();
    List<ReferrerEntity> fromReferrerEntities = new ArrayList<>();


    public UserEntity(String name, String family, String emailAddress, String password, String role) {
        this.name = name;
        this.family = family;
        this.emailAddress = emailAddress;
        this.password = password;
        this.role = role;
    }

    public UserEntity() {
    }


    @Basic
    @Column(name="APPROVED")
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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
            mappedBy = "toUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<ReferrerEntity> getToReferrerEntities() {
        return toReferrerEntities;
    }

    public void setToReferrerEntities(List<ReferrerEntity> toReferrerEntities) {
        this.toReferrerEntities = toReferrerEntities;
    }

    @OneToMany(
            mappedBy = "fromUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<ReferrerEntity> getFromReferrerEntities() {
        return fromReferrerEntities;
    }

    public void setFromReferrerEntities(List<ReferrerEntity> fromReferrerEntities) {
        this.fromReferrerEntities = fromReferrerEntities;
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
                '}';
    }
}
