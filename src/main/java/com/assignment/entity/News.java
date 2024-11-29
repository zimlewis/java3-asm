package com.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {
    String id;
    String title;
    String content;
    String image;
    Date postedDate;
    String author;
    Integer viewCount;
    String categoryId;
    Boolean home;
}
