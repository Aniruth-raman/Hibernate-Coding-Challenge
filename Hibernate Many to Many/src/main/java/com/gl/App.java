package com.gl;

import com.gl.config.HibernateConfig;
import com.gl.entity.Employee;
import com.gl.entity.Project;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

//        Create
        System.out.println("*".repeat(5) + "Create Operation" + "*".repeat(5));
        Employee employee1 = new Employee("John Doe");
        Employee employee2 = new Employee("Jane Doe");
        Project project1 = new Project("Project A");
        Project project2 = new Project("Project B");

// Assign projects to employees
        employee1.getProjects().add(project1);
        employee1.getProjects().add(project2);
        employee2.getProjects().add(project1);

// Save employees and projects to the database
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(employee1);
        session.persist(employee2);
        session.persist(project1);
        session.persist(project2);
        tx.commit();
        session.close();
        System.out.println("Project and Employees are created successfully!");
        System.out.println();


//        Calling Employee using Project ID
        System.out.println("*".repeat(5) + "Calling Employee using Project ID" + "*".repeat(5));
        int projectId = 1; // Example project ID
        System.out.println("For Project ID" + projectId + " the employees assigned are:");
        session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT e FROM Employee e JOIN e.projects p WHERE p.id = :projectId", Employee.class);
        query.setParameter("projectId", projectId);
        List<Employee> employees = query.getResultList();
        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }
        session.close();

        System.out.println("*".repeat(5) + "Calling Project using Employee Name" + "*".repeat(5));

        String employeeName = "Jane Doe"; // Example employee name
        System.out.println("For Employee Name " + employeeName + " the projects assigned are:");

        session = sessionFactory.openSession();
        query = session.createQuery("SELECT p FROM Project p JOIN p.employees e WHERE e.name = :employeeName", Project.class);
        query.setParameter("employeeName", employeeName);
        List<Project> projects = query.getResultList();
        for (Project project : projects) {
            System.out.println(project.getName());
        }
        session.close();

    }

}
