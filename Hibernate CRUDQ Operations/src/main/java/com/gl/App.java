package com.gl;

import com.gl.config.HibernateConfig;
import com.gl.dao.TeacherDAO;
import com.gl.entity.Teacher;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SessionFactory factory = HibernateConfig.getSessionFactory();
        TeacherDAO teacherDAO = new TeacherDAO(factory);
//        CRUD Operations

//        Create
        System.out.println("*".repeat(5) + "Create Operation" + "*".repeat(5));
        Teacher teacher = new Teacher("John", "Doe", "jdoe@abc.com");
        teacherDAO.insertTeacher(teacher);
        System.out.println("Teacher record added successfully!");
        System.out.println("All Teachers list after create operation:");
        List<Teacher> teacherList = teacherDAO.getAllTeachers();
        for (Teacher t : teacherList) {
            System.out.println(t);
        }
        System.out.println();
//        Read
        System.out.println("*".repeat(5) + "Read Operation" + "*".repeat(5));

        System.out.println("Get the details of all Teachers:");
        teacherList = teacherDAO.getAllTeachers();
        int storeIDOfFirstTeacher = teacherList.get(0).getId();
        for (Teacher t : teacherList) {
            System.out.println(t);
        }
        System.out.println();
        System.out.println("Get Teacher by ID:" + storeIDOfFirstTeacher);
        System.out.println(teacherDAO.getTeacherById(storeIDOfFirstTeacher));
        System.out.println();
//        Update
        System.out.println("*".repeat(5) + "Update Operation" + "*".repeat(5));
        System.out.println("Teacher details before Update:");
        System.out.println(teacherDAO.getTeacherById(storeIDOfFirstTeacher));
        Teacher teacher1 = teacherDAO.getTeacherById(storeIDOfFirstTeacher);
        teacher1.setFirstName("Jane");
        teacherDAO.updateTeacher(teacher1);
        System.out.println("Teacher details after Update:");
        System.out.println(teacherDAO.getTeacherById(storeIDOfFirstTeacher));
        System.out.println();
//        Delete
        System.out.println("*".repeat(5) + "Delete Operation" + "*".repeat(5));

        System.out.println("All Teachers before deletion:");
        teacherList = teacherDAO.getAllTeachers();
        storeIDOfFirstTeacher = teacherList.get(0).getId();
        for (Teacher t : teacherList) {
            System.out.println(t);
        }
        System.out.println("Deleting teacher with ID:" + storeIDOfFirstTeacher);
        teacherDAO.deleteTeacher(storeIDOfFirstTeacher);
        System.out.println("All Teachers after deletion:");
        teacherList = teacherDAO.getAllTeachers();
        for (Teacher t : teacherList) {
            System.out.println(t);
        }


    }
}
