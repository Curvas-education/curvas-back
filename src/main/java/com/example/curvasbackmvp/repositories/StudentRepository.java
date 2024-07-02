package com.example.curvasbackmvp.repositories;

import com.example.curvasbackmvp.models.exam.Exam;
import com.example.curvasbackmvp.models.student.Student;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Page<Student> findAllByActiveTrue(Pageable page);

    @Query(value =
            "select e.id from exams e inner join group_exams ge ON (e.id = ge.exam_id) "
            +"inner join groups g ON ge.group_id = g.id "
            +"inner join groups_users gu on g.id = gu.group_id "
            +"inner join users u on gu.user_id = u.registration "
            +"where u.registration = :registration", nativeQuery = true)
    List<String> findStudentExams(@Param("registration") String registration);

    Optional<Student> findStudentByCpf(String cpf);
}
