package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

//    @Autowired
//    public StudentService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        checkStudentExist(studentId);
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " not exist"));

        if(!name.isBlank() && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        if(!email.isBlank() && !Objects.equals(email, student.getEmail())) {
            if (studentRepository.findStudentByEmail(email).isPresent()) {
                throw new IllegalStateException("Email " + email + " taken");
            }
            student.setEmail(email);
        }

        // use @Transactional -> no need following line
        // studentRepository.save(student);
    }

    private void checkStudentExist(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("Student " + studentId + " not exist");
        }
    }
}