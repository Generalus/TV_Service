package ru.thesn.tvs.etvsl.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "TV_PACKAGE")
public class TVPackage {
    @Id
    @Column(name = "OFFERING_ID", nullable = false)
    private Long offeringID;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="PACKAGE-LINEUP",
            joinColumns=@JoinColumn(name="PACKAGE"),
            inverseJoinColumns=@JoinColumn(name="LINEUP"))
    private Set<Lineup> lineups;

    @ManyToMany(mappedBy = "packages", fetch = FetchType.EAGER)
    private Set<TVChannel> channels;

    public Set<Lineup> getLineups() {
        return lineups;
    }

    public void setLineups(Set<Lineup> lineups) {
        this.lineups = lineups;
    }

    public Set<TVChannel> getChannels() {
        return channels;
    }

    public void setChannels(Set<TVChannel> channels) {
        this.channels = channels;
    }

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
