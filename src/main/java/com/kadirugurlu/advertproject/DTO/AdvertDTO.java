package com.kadirugurlu.advertproject.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AdvertDTO {

    private Long userId;
    private String title;
    private String description;
    private double price;
    private String category;
    private List<Long> groupIds;
}
