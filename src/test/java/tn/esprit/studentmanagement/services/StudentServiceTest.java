package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.studentmanagement.entities.Student;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private IStudentService studentService;

    @Test
    void testSaveAndGetStudent() {
        // 1. Create a student
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
        student.setDateOfBirth(LocalDate.of(2000, 1, 1));

        // 2. Save the student
        Student savedStudent = studentService.saveStudent(student);
        Assertions.assertNotNull(savedStudent.getIdStudent(), "Student ID should not be null after saving");

        // 3. Retrieve all students
        List<Student> students = studentService.getAllStudents();
        Assertions.assertFalse(students.isEmpty(), "Student list should not be empty");
        Assertions.assertEquals("John", students.get(0).getFirstName(), "First name should match");
        
        System.out.println("✅ Test Passed: Student " + savedStudent.getFirstName() + " was saved and retrieved successfully from H2 database.");
    }
}
