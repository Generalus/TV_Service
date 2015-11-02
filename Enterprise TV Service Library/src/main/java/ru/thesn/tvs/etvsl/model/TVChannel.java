package ru.thesn.tvs.etvsl.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHANNEL")
public class TVChannel {
    @Id
    @Column(name = "SOURCE_ID", nullable = false)
    private Long sourceID;

    @Column(name = "NAME")
    private String name;

    public Long getSourceID() {
        return sourceID;
    }

    public void setSourceID(Long sourceID) {
        this.sourceID = sourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
