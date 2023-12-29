package com.kadirugurlu.advertproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "adverts")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AdvertField> advertField;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AdvertComment> advertComments;
}
