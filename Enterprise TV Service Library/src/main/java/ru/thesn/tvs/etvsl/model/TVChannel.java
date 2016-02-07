package ru.thesn.tvs.etvsl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CHANNEL")
public class TVChannel {
    @Id
    @Column(name = "SOURCE_ID", nullable = false)
    private Long sourceID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTENT_ID", nullable = false)
    private Long contentID;

    @JsonIgnore
    @Column(name = "STATUS")
    private String status;

    @JsonIgnore
    @ManyToMany(mappedBy = "channels", fetch = FetchType.EAGER)
    private Set<TVPackage> packages;

    @JsonIgnore
    @ManyToMany(mappedBy = "channels", fetch = FetchType.EAGER)
    private Set<Lineup> lineups;

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

    public Long getContentID() {
        return contentID;
    }

    public void setContentID(Long contentID) {
        this.contentID = contentID;
    }

    public Set<TVPackage> getPackages() {
        return packages;
    }

    public void setPackages(Set<TVPackage> packages) {
        this.packages = packages;
    }

    public Set<Lineup> getLineups() {
        return lineups;
    }

    public void setLineups(Set<Lineup> lineups) {
        this.lineups = lineups;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TVChannel channel = (TVChannel) o;

        return sourceID.equals(channel.sourceID);
    }

    @Override
    public int hashCode() {
        return sourceID.hashCode();
    }
}
