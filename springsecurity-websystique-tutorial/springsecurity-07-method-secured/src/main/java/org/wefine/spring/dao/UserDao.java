package org.wefine.spring.dao;


import org.wefine.spring.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);

    User findById(int id);

    User findBySSO(String sso);

    void deleteUser(int id);

    List findAll();
}