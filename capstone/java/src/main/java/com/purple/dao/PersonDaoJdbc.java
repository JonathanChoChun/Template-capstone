package com.purple.dao;

import com.purple.model.Customer;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * JDBCCustomerDao
 */
@Component
public class PersonDaoJdbc implements PersonDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDaoJdbc(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Customer> searchAndSortCustomers(String search, String sort) {
        return null;
    }
}