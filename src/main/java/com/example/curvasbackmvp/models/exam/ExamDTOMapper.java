package com.example.curvasbackmvp.models.exam;

import com.example.curvasbackmvp.models.group.GroupDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExamDTOMapper implements Function<Exam, ExamDTO> {

    private final ModelMapper modelMapper;

    public ExamDTOMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ExamDTO apply(Exam exam) {
        return new ExamDTO(exam.getGroups().stream().map(GroupDTO::new).toList(), exam.getName(), exam.getTeacher().getName(), exam.getEndTime(), exam.getPoints());
    }
}
