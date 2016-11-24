package org.wefine.spring.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wefine.spring.dao.UserProfileDao;
import org.wefine.spring.model.UserProfile;

import javax.annotation.Resource;
import java.util.List;


@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    @Resource
    UserProfileDao dao;

    public List<UserProfile> findAll() {
        return dao.findAll();
    }

    public UserProfile findByType(String type) {
        return dao.findByType(type);
    }

    public UserProfile findById(int id) {
        return dao.findById(id);
    }
}