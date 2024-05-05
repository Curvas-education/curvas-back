package com.example.curvasbackmvp.models.group;

public record GroupDTO(String id, String name, String description, String image){
    public GroupDTO(Group group) {
        this(group.getId(), group.getName(), group.getDescription(), group.getImage());
    }
}
