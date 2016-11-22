package org.wefine.spring.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wefine.spring.dao.UserDao;
import org.wefine.spring.model.User;

import javax.annotation.Resource;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao dao;

    public User findById(int id) {
        return dao.findById(id);
    }

    public User findBySso(String sso) {
        return dao.findBySSO(sso);
    }

}