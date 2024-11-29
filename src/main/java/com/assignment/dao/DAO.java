package com.assignment.dao;

import java.util.List;

public interface DAO<e> {
    List<e> findAll();
    e find(String id);
    void create(e e);
    void update(e e);
    void delete(String id);
}
