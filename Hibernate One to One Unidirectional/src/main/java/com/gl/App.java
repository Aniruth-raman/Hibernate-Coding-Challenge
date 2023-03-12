package com.gl;

import com.gl.config.HibernateConfig;
import com.gl.dao.StudentDAO;
import com.gl.entity.Address;
import com.gl.entity.Student;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        StudentDAO studentDAO = new StudentDAO(sessionFactory);

//        Create
        System.out.println("*".repeat(5) + "Create Operation" + "*".repeat(5));
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("Anytown");
        address.setState("CA");
        address.setZip("12345");

        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("johndoe@example.com");
        student.setAddress(address);
        studentDAO.insertStudent(student);

        System.out.println("Student record added successfully!");
        System.out.println("All Students list after create operation:");
        List<Student> studentList = studentDAO.getAllStudents();
        for (Student s : studentList) {
            System.out.println(s);
        }
        System.out.println();
//        Delete
        System.out.println("*".repeat(5) + "Delete Operation" + "*".repeat(5));

        System.out.println("All Students before deletion:");
        studentList = studentDAO.getAllStudents();
        int storeIDOfFirstStudent = studentList.get(0).getId();
        for (Student s : studentList) {
            System.out.println(s);
        }
        System.out.println("Deleting student with ID:" + storeIDOfFirstStudent);
        studentDAO.deleteStudent(storeIDOfFirstStudent);
        System.out.println("All Students after deletion:");
        studentList = studentDAO.getAllStudents();
        for (Student s : studentList) {
            System.out.println(s);
        }
    }
}
