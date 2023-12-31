package com.kadirugurlu.advertproject.DTO;

import lombok.Data;

@Data
public class CommentDTO {

    private Long advertId;
    private Long userId;
    private String title;
    private String comment;
}
