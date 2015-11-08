package ru.thesn.tvs.etvsl.model;


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

    @ManyToMany(mappedBy = "channels", fetch = FetchType.EAGER)
    private Set<TVPackage> packages;

    @ManyToMany(mappedBy = "channels", fetch = FetchType.EAGER)
    private Set<TVPackage> lineups;

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

    public Set<TVPackage> getLineups() {
        return lineups;
    }

    public void setLineups(Set<TVPackage> lineups) {
        this.lineups = lineups;
    }
}
