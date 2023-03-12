package com.gl.dao;

import com.gl.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TeacherDAO {

    private final SessionFactory sessionFactory;

    public TeacherDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertTeacher(Teacher teacher) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(teacher);
        tx.commit();
        session.close();
    }

    public void updateTeacher(Teacher teacher) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(teacher);
        tx.commit();
        session.close();
    }

    public Teacher getTeacherById(int id) {
        Session session = sessionFactory.openSession();
        Teacher teacher = session.get(Teacher.class, id);
        session.close();
        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        Session session = sessionFactory.openSession();
        List<Teacher> teachers = session.createQuery("from Teacher", Teacher.class).getResultList();
        session.close();
        return teachers;
    }

    public void deleteTeacher(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Teacher teacher = session.get(Teacher.class, id);
        session.remove(teacher);
        tx.commit();
        session.close();
    }
}