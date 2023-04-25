package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void getStudents() {
        // given
        // when
        underTest.getStudents();
        // then
        verify(studentRepository).findAll();
    }

    @Disabled
    @Test
    void addStudent() {
    }

    @Disabled
    @Test
    void deleteStudent() {
    }

    @Disabled
    @Test
    void updateStudent() {
    }
}