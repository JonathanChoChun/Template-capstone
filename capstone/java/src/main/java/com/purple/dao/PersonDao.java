package com.purple.dao;

import com.purple.model.Customer;
import java.util.List;

/**
 * CustomerDao
 */
public interface PersonDao {
    List<Customer> searchAndSortCustomers(String search, String sort);
}