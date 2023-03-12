package com.gl;

import com.gl.config.HibernateConfig;
import com.gl.dao.AddressDAO;
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
        address.setStudent(student);
        studentDAO.insertStudent(student);


        System.out.println("Student record added successfully!");
        System.out.println("All Students list after create operation:");
        List<Student> studentList = studentDAO.getAllStudents();
        for (Student s : studentList) {
            System.out.println(s);
        }
        System.out.println();
//        Get Student by Address
        System.out.println("*".repeat(5) + "Fetch Student by Address" + "*".repeat(5));
        AddressDAO addressDAO = new AddressDAO(sessionFactory);
        List<Address> addressList = addressDAO.getAllAddresss();
        for (Address ad : addressList) {
            System.out.println(ad);
        }

//        Delete
        System.out.println("*".repeat(5) + "Delete Operation" + "*".repeat(5));

        System.out.println("All Students before deletion:");
        studentList = studentDAO.getAllStudents();
//        int storeIDOfFirstStudent = studentList.get(0).getId();
        for (Student s : studentList) {
            System.out.println(s);
        }
        System.out.println("All Addresses before deletion:");
        addressList = addressDAO.getAllAddresss();
//        int storeIDOfFirstStudent = studentList.get(0).getId();
        for (Address ad : addressList) {
            System.out.println(ad);
        }
        int storeIDOfFirstAddress = addressList.get(0).getId();
        System.out.println("Deleting Address with ID:" + storeIDOfFirstAddress);
        addressDAO.deleteAddress(storeIDOfFirstAddress);
        System.out.println("All Students after deletion:");
        studentList = studentDAO.getAllStudents();
        for (Student s : studentList) {
            System.out.println(s);
        }
    }
}
