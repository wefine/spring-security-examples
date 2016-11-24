package org.wefine.spring.config.mvc;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.wefine.spring.model.UserProfile;
import org.wefine.spring.web.service.UserProfileService;

import javax.annotation.Resource;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

    @Resource
    private UserProfileService userProfileService;

    /*
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public UserProfile convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        UserProfile profile = userProfileService.findById(id);
        System.out.println("Profile : " + profile);
        return profile;
    }
     
    /*
     * Gets UserProfile by type
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    /*
    public UserProfile convert(Object element) {
        String type = (String)element;
        UserProfile profile= userProfileService.findByType(type);
        System.out.println("Profile ... : "+profile);
        return profile;
    }
    */

}