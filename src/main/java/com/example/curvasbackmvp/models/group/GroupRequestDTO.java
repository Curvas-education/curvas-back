package com.example.curvasbackmvp.models.group;

import lombok.Data;

import java.util.List;

@Data
public class GroupRequestDTO {
    private String name;
    private String description;
    private String image;
    private List<String> users;
}
