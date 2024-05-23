package br.com.dgc.simplechurch.member.model;

import java.time.LocalDate;
import java.util.UUID;

import br.com.dgc.simplechurch.church.model.Church;
import br.com.dgc.simplechurch.member.model.enums.GenderEnum;
import br.com.dgc.simplechurch.member.model.enums.ReceptionMode;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type", discriminatorType = DiscriminatorType.CHAR)
@Table(name = "TB_MEMBER")
@DiscriminatorValue("M")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;

    private String lastName;

    private String fullName;
    private LocalDate birthDate;
    private String placeBirth;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String celebrantFullName;
    private LocalDate receptionDate;
    private String receptionPlace;
    @Enumerated(EnumType.STRING)
    private ReceptionMode receptionMode;

    @ManyToOne
    @JoinColumn(name = "church_id")
    private Church church;

    public Member() {
    }

    public Church getChurch() {
        return church;
    }

    public void setChurch(Church church) {
        this.church = church;
    }

    @PrePersist
    @PreUpdate
    public void setFirstAndLastName() {
        String[] split = this.fullName.split(" ");
        this.firstName = split[0];
        this.lastName = split[(split.length - 1)];
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getCelebrantFullName() {
        return celebrantFullName;
    }

    public void setCelebrantFullName(String celebrantFullName) {
        this.celebrantFullName = celebrantFullName;
    }

    public LocalDate getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(LocalDate receptionDate) {
        this.receptionDate = receptionDate;
    }

    public String getReceptionPlace() {
        return receptionPlace;
    }

    public void setReceptionPlace(String receptionPlace) {
        this.receptionPlace = receptionPlace;
    }

    public ReceptionMode getReceptionMode() {
        return receptionMode;
    }

    public void setReceptionMode(ReceptionMode receptionMode) {
        this.receptionMode = receptionMode;
    }

}
