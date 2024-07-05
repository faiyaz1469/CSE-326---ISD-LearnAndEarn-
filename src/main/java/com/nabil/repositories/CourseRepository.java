package com.nabil.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nabil.models.Course;
import com.nabil.models.Assignment;
import com.nabil.models.Account;

public interface CourseRepository extends JpaRepository<Course, Long> {
     //Custom query
    @Query(value = "select * from course s where s.name like %:keyword% or s.subject like %:keyword% or s.first_name like %:keyword%", nativeQuery = true)
    List<Course> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from course s where s.email like %:keyword%", nativeQuery = true)
    List<Course> findByEmail(@Param("keyword") String keyword);

    @Query(value = "select * from assignment a join account e on (a.email = e.email)", nativeQuery = true)
    List<Assignment> findByEmailAssignment();
}
