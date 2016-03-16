package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by MacLap on 3/16/16.
 */

@Entity
@Table(name = "files")
public class AnonFile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String originalFilename;

    @Column(nullable=false)
    String comment;

    @Column(nullable = false)
    boolean isKept = false;

    public AnonFile() {
    }

    public AnonFile(String filename, String originalFilename, String comment, boolean isKept) {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.comment = comment;
        this.isKept = isKept;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isKept() {
        return isKept;
    }

    public void setKept(boolean kept) {
        isKept = kept;
    }
}
