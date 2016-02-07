package ru.thesn.tvs.cims.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "OFFERING_ID", nullable = false)
    private Long offeringID;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "LOGIN", nullable = false)
    private Account account;

    public Long getOfferingID() {
        return offeringID;
    }

    public void setOfferingID(Long offeringID) {
        this.offeringID = offeringID;
    }

    public Account getAccount() {
        return account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
