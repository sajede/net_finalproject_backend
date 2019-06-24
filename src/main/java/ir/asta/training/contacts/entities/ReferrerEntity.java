package ir.asta.training.contacts.entities;


import javax.persistence.*;

@Entity
@Table(name="REFERRER")
public class ReferrerEntity {

    Long id;
    String comment;
    CaseEntity caseEntity;
    UserEntity fromUser;
    UserEntity toUser;
    int referrerNumber;

    public ReferrerEntity() {
    }

    public ReferrerEntity(int referrerNumber,String comment, CaseEntity caseEntity, UserEntity fromUser, UserEntity toUser) {
        this.referrerNumber=referrerNumber;
        this.comment = comment;
        this.caseEntity = caseEntity;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    @Basic
    @Column(name="REFERRER_NUMBER")
    public int getReferrerNumber() {
        return referrerNumber;
    }

    public void setReferrerNumber(int referrerNumber) {
        this.referrerNumber = referrerNumber;
    }

    @Basic
    @Column(name="COMMENT")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Id
    @Column(name = "REFERRER_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    public CaseEntity getCaseEntity() {
        return caseEntity;
    }

    public void setCaseEntity(CaseEntity caseEntity) {
        this.caseEntity = caseEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_USER_TB_ID")
    public UserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserEntity fromUser) {
        this.fromUser = fromUser;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_USER_TB_ID")
    public UserEntity getToUser() {
        return toUser;
    }

    public void setToUser(UserEntity toUser) {
        this.toUser = toUser;
    }
}
