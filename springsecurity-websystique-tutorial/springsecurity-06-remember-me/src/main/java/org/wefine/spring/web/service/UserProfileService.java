package org.wefine.spring.web.service;

import org.wefine.spring.model.UserProfile;

import java.util.List;


public interface UserProfileService {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}