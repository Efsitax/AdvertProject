package com.kadirugurlu.advertproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "advert_comments")
public class AdvertComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "advert_id", referencedColumnName = "id")
    @JsonBackReference
    private Advert advert;

    @Column(length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String comment;
}
