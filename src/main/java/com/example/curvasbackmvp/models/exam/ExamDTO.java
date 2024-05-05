package com.example.curvasbackmvp.models.exam;

import com.example.curvasbackmvp.models.group.Group;
import com.example.curvasbackmvp.models.group.GroupDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ExamDTO(List<GroupDTO> groups, String name, String author, LocalDateTime endDate, Integer points) {
}
