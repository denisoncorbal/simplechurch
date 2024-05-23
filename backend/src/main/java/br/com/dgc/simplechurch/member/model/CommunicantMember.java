package br.com.dgc.simplechurch.member.model;

import br.com.dgc.simplechurch.member.model.enums.MaritalStatus;
import br.com.dgc.simplechurch.member.model.enums.ReligiousOrigin;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("C")
public class CommunicantMember extends Member {

    @Enumerated(EnumType.STRING)
    private ReligiousOrigin religiousOrigin;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private String profession;
    private String fullAddress;
    private boolean knowRead;
    private boolean knowWrite;
    private boolean childBaptized;

    public CommunicantMember() {
    }

    public ReligiousOrigin getReligiousOrigin() {
        return religiousOrigin;
    }

    public void setReligiousOrigin(ReligiousOrigin religiousOrigin) {
        this.religiousOrigin = religiousOrigin;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public boolean isKnowRead() {
        return knowRead;
    }

    public void setKnowRead(boolean knowRead) {
        this.knowRead = knowRead;
    }

    public boolean isKnowWrite() {
        return knowWrite;
    }

    public void setKnowWrite(boolean knowWrite) {
        this.knowWrite = knowWrite;
    }

    public boolean isChildBaptized() {
        return childBaptized;
    }

    public void setChildBaptized(boolean childBaptized) {
        this.childBaptized = childBaptized;
    }

}
