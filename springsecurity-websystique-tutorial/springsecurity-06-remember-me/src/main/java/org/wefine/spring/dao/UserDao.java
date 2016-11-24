package org.wefine.spring.dao;


import org.wefine.spring.model.User;

public interface UserDao {
    void save(User user);

    User findById(int id);

    User findBySSO(String sso);

}