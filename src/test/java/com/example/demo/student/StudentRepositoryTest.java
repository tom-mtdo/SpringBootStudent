package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    StudentRepository underTest;

//    @Test
//    void selectExistsEmail() {
//    }


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findStudentByEmailExist () {
        // given
        String email = "tomtom@gmail.com";
        Student student = new Student(
                "tom",
                email,
                LocalDate.of(2000, Month.JANUARY, 5)
        );
        underTest.save(student);

        // when
        Optional<Student> output = underTest.findStudentByEmail(email);

        // then
        assertThat(output.isPresent()).isTrue();
    }

    @Test
    void findStudentByEmailNotExist() {
        // given
        String email = "tomtom@gmail.com";

        // when
        Optional<Student> output = underTest.findStudentByEmail(email);

        // then
        assertThat(output.isPresent()).isFalse();
    }
}