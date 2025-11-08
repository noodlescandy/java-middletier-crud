package com.example.demo.service;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    // list of students instead of optional b/c empty list is more appropriate than null.
    public List<Student> getAllStudents() { // interactions with api should not have internals exposed.
        return studentRepository.findAll();
    }

    // get student by id wrapped in optional to handle not found case.get student by id wrapped in optional to handle not found case.
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // if an id is provided in the student object, it should be ignored to avoid conflicts with the database.
    public Student createStudent(Student student) {
        student.setId(null); // Ensure the ID is null so that a new entity is created
        return studentRepository.save(student);
    }

    // update and return student wrapped in optional to handle not found case.
    public Optional<Student> updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id)
            .map(student -> {
            student.setName(studentDetails.getName());
            Student updatedStudent = studentRepository.save(student);
            return updatedStudent;
        });
    }

    // attempts to delete student, returns false if not found.
    public boolean deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }
}
