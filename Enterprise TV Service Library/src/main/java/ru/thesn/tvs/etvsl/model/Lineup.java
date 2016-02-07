package ru.thesn.tvs.etvsl.model;




import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.nio.channels.Channel;
import java.util.Set;

@Entity
@Table(name = "LINEUP")
public class Lineup {
    @Id
    @Column(name = "AREA_ID", nullable = false)
    private Long areaID;

    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "lineups", fetch = FetchType.EAGER)
    private Set<TVPackage> packages;


    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="LINEUP_CHANNEL",
            joinColumns=@JoinColumn(name="LINEUP"),
            inverseJoinColumns=@JoinColumn(name="CHANNEL"))
    private Set<TVChannel> channels;

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

    public Set<TVChannel> getChannels() {
        return channels;
    }

    public void setChannels(Set<TVChannel> channels) {
        this.channels = channels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lineup lineup = (Lineup) o;

        return areaID.equals(lineup.areaID);
    }

    @Override
    public int hashCode() {
        return areaID.hashCode();
    }
}
