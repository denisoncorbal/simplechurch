package br.com.dgc.simplechurch.member.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class NonCommunicantMember extends Member {
    private String fatherFullName;
    private boolean fatherProfessed;
    private String motherFullName;
    private boolean motherProfessed;

    public NonCommunicantMember() {
    }

    public String getFatherFullName() {
        return fatherFullName;
    }

    public void setFatherFullName(String fatherFullName) {
        this.fatherFullName = fatherFullName;
    }

    public boolean isFatherProfessed() {
        return fatherProfessed;
    }

    public void setFatherProfessed(boolean fatherProfessed) {
        this.fatherProfessed = fatherProfessed;
    }

    public String getMotherFullName() {
        return motherFullName;
    }

    public void setMotherFullName(String motherFullName) {
        this.motherFullName = motherFullName;
    }

    public boolean isMotherProfessed() {
        return motherProfessed;
    }

    public void setMotherProfessed(boolean motherProfessed) {
        this.motherProfessed = motherProfessed;
    }

}
