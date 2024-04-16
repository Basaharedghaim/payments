package com.example.payments.services;

import jakarta.persistence.Access;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private EntityManager entityManager;
    public boolean isCustomerIdExist(Long id) {
        Query query = entityManager.createQuery("SELECT COUNT(c) FROM Customer c WHERE c.id = :id");
        query.setParameter("id", id);
        System.out.println("Query: " + query.unwrap(org.hibernate.query.Query.class).getQueryString());
        Long count = (Long) query.getSingleResult();
        System.out.println("Customer count with ID " + id + ": " + count);
        return count > 0;
    }

}
