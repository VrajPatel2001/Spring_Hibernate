package com.spring_hibernate.learn.java.dao;

import com.spring_hibernate.learn.java.dataSource.DataSource;
import com.spring_hibernate.learn.java.domain.Employee;
import com.spring_hibernate.learn.java.domain.Phone;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import javax.persistence.Query;
import java.util.List;

@Service
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> getAllEmployee() {
        Session session = null;
        Transaction transaction = null;
        List<Employee> employees = null;
        try
        {
          session = DataSource.getSessionFactory().openSession();
          transaction = session.beginTransaction();
            employees = session.createQuery("From Employee",Employee.class).getResultList();
            transaction.commit();
        }
        catch (HibernateException e)
        {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }
        finally {
           if(session!=null)
            session.close();
        }
        return employees;
    }

    @Override
    public void insertOne(Employee e) {

        Transaction transaction = null;
        try (Session session = DataSource.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(e);
            transaction.commit();
        } catch (Exception e1) {
            if (transaction != null) transaction.rollback();
            e1.printStackTrace();
        }

    }

    @Override
    public Employee getOneEmployee(int id) {
        Transaction transaction = null;
        Employee employee = null;
        try (Session session = DataSource.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = session.createQuery("From Employee WHERE e_id=:id",Employee.class).setParameter("id",id).getSingleResult();
            transaction.commit();
        } catch (Exception e1) {
            if (transaction != null) transaction.rollback();
            e1.printStackTrace();
        }

        return employee;
    }

    @Override
    public void deleteById(int id) {
        Transaction transaction = null;
        Employee employee = null;
        try (Session session = DataSource.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = session.createQuery("From Employee WHERE e_id=:id",Employee.class).setParameter("id",id).getSingleResult();
            session.delete(employee);
            transaction.commit();
        } catch (Exception e1) {
            if (transaction != null) transaction.rollback();
            e1.printStackTrace();
        }

    }

    @Override
    public void updateById(int id, String name) {
        Transaction transaction = null;
        Employee employee = null;
        try (Session session = DataSource.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = session.createQuery("From Employee WHERE e_id=:id",Employee.class).setParameter("id",id).getSingleResult();
            employee.setName(name);
            session.update(employee);
            transaction.commit();
        } catch (Exception e1) {
            if (transaction != null) transaction.rollback();
            e1.printStackTrace();
        }
    }
}
