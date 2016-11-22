package org.wefine.spring.dao;


import org.wefine.spring.model.User;

public interface UserDao {
 
    User findById(int id);
     
    User findBySSO(String sso);
     
}