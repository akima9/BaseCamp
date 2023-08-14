package com.giyong.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue
    private int boardId;
    private String title;
    @Lob
    @Column(name = "content", columnDefinition="BLOB")
    private String content;
    private String writer;
    private int viewCount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
