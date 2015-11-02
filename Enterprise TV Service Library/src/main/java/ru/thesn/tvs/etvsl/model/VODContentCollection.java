package ru.thesn.tvs.etvsl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Никита on 01.11.2015.
 */

@Entity
@Table(name = "CONTENT")
public class VODContentCollection {
    @Id
    @Column(name = "CONTENT_ID", nullable = false)
    private Long contentID;

    @Column(name = "NAME")
    private String name;

    public Long getContentID() {
        return contentID;
    }

    public void setContentID(Long contentID) {
        this.contentID = contentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
