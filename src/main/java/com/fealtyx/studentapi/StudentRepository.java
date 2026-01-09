package com.fealtyx.studentapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Student student) {
        String sql = "INSERT INTO students (id, name, email, gpa) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getGpa()
        );
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDouble("gpa")
                )
        );
    }

    public Student findById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) ->
                        new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getDouble("gpa")
                        )
        );
    }

    public int update(int id, Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, gpa = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                student.getName(),
                student.getEmail(),
                student.getGpa(),
                id
        );
    }

    public int delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
