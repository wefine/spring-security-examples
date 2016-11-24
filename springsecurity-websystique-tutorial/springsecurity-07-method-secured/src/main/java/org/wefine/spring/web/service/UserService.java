package org.wefine.spring.web.service;


import org.springframework.security.access.prepost.PreAuthorize;
import org.wefine.spring.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findById(int id);

    User findBySso(String sso);

    List<User> findAll();

    @PreAuthorize("hasRole('ADMIN')")
    void updateUser(User user);

    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    void deleteUser(int id);
}