package com.gl.dao;

import com.gl.entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AddressDAO {
    private final SessionFactory sessionFactory;

    public AddressDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertAddress(Address student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();
        session.close();
    }

    public void updateAddress(Address teacher) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(teacher);
        tx.commit();
        session.close();
    }

    public Address getAddressById(int id) {
        Session session = sessionFactory.openSession();
        Address student = session.get(Address.class, id);
        session.close();
        return student;
    }

    public List<Address> getAllAddresss() {
        Session session = sessionFactory.openSession();
        List<Address> students = session.createQuery("from Address", Address.class).getResultList();
        session.close();
        return students;
    }

    public void deleteAddress(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Address student = session.get(Address.class, id);
        session.remove(student);
        tx.commit();
        session.close();
    }
}
