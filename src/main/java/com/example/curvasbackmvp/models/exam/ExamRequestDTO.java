package com.example.curvasbackmvp.models.exam;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExamRequestDTO {
    private String name;
    private String description;
    private List<String> questions;
    private List<String> groups;
    private Integer points;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
