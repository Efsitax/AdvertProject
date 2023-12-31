package com.kadirugurlu.advertproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "adverts")
public class Advert {

    public Advert() {
        this.groups = new ArrayList<>();
        this.advertField = new ArrayList<>();
        this.advertComments = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AdvertField> advertField;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AdvertComment> advertComments;

    @ManyToMany
    @JoinTable(
            name = "group_adverts",
            joinColumns = @JoinColumn(name = "advert_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;
}
