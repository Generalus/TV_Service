package ru.thesn.tvs.etvsl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LINEUP")
public class Lineup {
    @Id
    @Column(name = "AREA_ID", nullable = false)
    private Long areaID;

    @Column(name = "NAME")
    private String name;

    public Long getAreaID() {
        return areaID;
    }

    public void setAreaID(Long areaID) {
        this.areaID = areaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
