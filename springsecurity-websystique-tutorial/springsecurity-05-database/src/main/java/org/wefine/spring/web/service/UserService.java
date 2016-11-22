package org.wefine.spring.web.service;


import org.wefine.spring.model.User;

public interface UserService {
 
    User findById(int id);
     
    User findBySso(String sso);
     
}