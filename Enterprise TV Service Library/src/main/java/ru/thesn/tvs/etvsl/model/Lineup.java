package ru.thesn.tvs.etvsl.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LINEUP")
public class Lineup {
    @Id
    @Column(name = "AREA_ID", nullable = false)
    private Long areaID;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "lineups", fetch = FetchType.EAGER)
    private Set<TVPackage> packages;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="LINEUP-CHANNEL",
            joinColumns=@JoinColumn(name="LINEUP"),
            inverseJoinColumns=@JoinColumn(name="CHANNEL"))
    private Set<Lineup> channels;

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

    public Set<TVPackage> getPackages() {
        return packages;
    }

    public void setPackages(Set<TVPackage> packages) {
        this.packages = packages;
    }

    public Set<Lineup> getChannels() {
        return channels;
    }

    public void setChannels(Set<Lineup> channels) {
        this.channels = channels;
    }
}
