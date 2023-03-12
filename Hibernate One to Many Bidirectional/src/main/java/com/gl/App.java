package com.gl;

import com.gl.config.HibernateConfig;
import com.gl.entity.Course;
import com.gl.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        System.out.println("*".repeat(5) + "Create Course and Student" + "*".repeat(5));
// create student
        Student student = new Student();
        student.setName("John Doe");

// create courses and add them to the student
        Course course1 = new Course();
        course1.setName("Mathematics");
        course1.setStudent(student);
        student.getCourses().add(course1);

        Course course2 = new Course();
        course2.setName("Science");
        course2.setStudent(student);
        student.getCourses().add(course2);

// save the student
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();
        session.close();
        System.out.println("Course and student saved successfully");
        int courseId = 1;
        System.out.println("*".repeat(5) + "Drop Course having ID " + courseId + "*".repeat(5));
        session = sessionFactory.openSession();
        // get the course to delete
        tx = session.beginTransaction();
        Course course = session.get(Course.class, courseId);

// set the course's student to null
//        course.setStudent(null);

// delete the course
        session.remove(course);
        tx.commit();
        session.close();
        System.out.println("Course with CourseId " + courseId + " dropped successfully!");
    }
}
