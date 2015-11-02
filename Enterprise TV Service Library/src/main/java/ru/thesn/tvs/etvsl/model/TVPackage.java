package ru.thesn.tvs.etvsl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Никита on 01.11.2015.
 */

@Entity
@Table(name = "TV_PACKAGE")
public class TVPackage {
    @Id
    @Column(name = "OFFERING_ID", nullable = false)
    private Long offeringID;

    @Column(name = "NAME")
    private String name;

    public Long getOfferingID() {
        return offeringID;
    }

    public void setOfferingID(Long offeringID) {
        this.offeringID = offeringID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
